package com.example.c052735.simpleexamplemvvm.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.example.c052735.simpleexamplemvvm.AppExecutors;
import com.example.c052735.simpleexamplemvvm.db.converter.DataConverter;
import com.example.c052735.simpleexamplemvvm.db.dao.CommentDao;
import com.example.c052735.simpleexamplemvvm.db.dao.ProductDao;
import com.example.c052735.simpleexamplemvvm.db.entity.CommentEntity;
import com.example.c052735.simpleexamplemvvm.db.entity.ProductEntity;
import com.example.c052735.simpleexamplemvvm.model.Comment;
import com.example.c052735.simpleexamplemvvm.model.Product;

import java.util.List;

@Database(entities = {ProductEntity.class,CommentEntity.class}, version = 1)
@TypeConverters(DataConverter.class)
public abstract class AppDatabase extends RoomDatabase{


    public static final String DATABASE_NAME = "basic-sample-db" ;
    private static AppDatabase sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public abstract ProductDao productDao();
    public abstract CommentDao commentDao();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if(sInstance == null) {
            synchronized (AppDatabase.class) {
                if(sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(),executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * build the database. only sets up the database configuration and creates a new instance of the database.
     * the sqlite database is only created when its accessed for the first time.
     * @return
     */
    private static AppDatabase buildDatabase(final Context appContext, final AppExecutors executors) {
        return Room.databaseBuilder(appContext,AppDatabase.class,DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            //add a delay to simulate a long-running operation
                            addDelay();
                            //Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext,executors);
                            List<ProductEntity> products = DataGenerator.generateProducts();
                            List<CommentEntity> comments = DataGenerator.generateCommentsForProducts(products);
                            insertData(database,products,comments);


                        });
                    }
                }).build();
    }

    private void updateDatabaseCreated(final Context context) {
        if(context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final AppDatabase database,final List<ProductEntity> products, final List<CommentEntity> comments) {
        database.runInTransaction(() -> {
            database.productDao().insertAll(products);
            database.commentDao().insertAll(comments);
        });
    }


    private static void addDelay() {
        try {
            Thread.sleep(4000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
