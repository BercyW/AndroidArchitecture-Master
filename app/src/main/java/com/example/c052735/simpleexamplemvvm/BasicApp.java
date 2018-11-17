package com.example.c052735.simpleexamplemvvm;

import android.app.Application;

public class BasicApp extends Application{
    private AppExecutors mAppExecutors;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
    }




}
