package com.example.c052735.simpleexamplemvvm;

import android.app.Application;

import com.example.c052735.simpleexamplemvvm.db.AppDatabase;

/**
 * Android application class, used for accessing singletons.
 */
public class BasicApp extends Application{
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this,mAppExecutors);
    }

}
