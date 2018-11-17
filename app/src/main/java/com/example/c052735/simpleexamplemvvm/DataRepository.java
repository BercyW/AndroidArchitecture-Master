package com.example.c052735.simpleexamplemvvm;

import android.arch.lifecycle.MediatorLiveData;

import com.example.c052735.simpleexamplemvvm.db.AppDatabase;
import com.example.c052735.simpleexamplemvvm.db.entity.ProductEntity;

public class DataRepository {

    private static DataRepository sInstance;

    private MediatorLiveData<ProductEntity> mObservableProducts;


    private DataRepository(){

    }

}
