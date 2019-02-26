package com.example.c052735.simpleexamplemvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.c052735.simpleexamplemvvm.db.AppDatabase;
import com.example.c052735.simpleexamplemvvm.db.entity.CommentEntity;
import com.example.c052735.simpleexamplemvvm.db.entity.ProductEntity;

import java.util.List;

/**
 * Repository handling the work with products and comments.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private MediatorLiveData<List<ProductEntity>> mObservableProducts;
    private final AppDatabase mDatabase;

    private DataRepository(final AppDatabase database){
        mDatabase = database;
        mObservableProducts = new MediatorLiveData<>();

        mObservableProducts.addSource(mDatabase.productDao().loadAllProducts(),
                productEntities -> {
                    if(mDatabase.getDatabaseCreated().getValue() !=null) {
                        mObservableProducts.postValue(productEntities);
                    }
                });



    }

    public static DataRepository getInstance(final AppDatabase database) {
        if(sInstance == null) {
            synchronized (DataRepository.class) {
                if(sInstance==null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }


    /**
     * get the list of products from the database and get notified when the data changes.
     * @return
     */
    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<ProductEntity> loadProduct(final int productId) {
        return mDatabase.productDao().loadProduct(productId);
    }

    public LiveData<List<CommentEntity>> loadComments(final int productId) {
        return mDatabase.commentDao().loadComments(productId);
    }

}
