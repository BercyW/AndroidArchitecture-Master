package com.example.c052735.simpleexamplemvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.c052735.simpleexamplemvvm.BasicApp;
import com.example.c052735.simpleexamplemvvm.db.entity.ProductEntity;

import java.util.List;

public class ProductListViewModel extends AndroidViewModel {

    //MediatorLivedata can observe other LiveData objects and react on their emissions
    private final MediatorLiveData<List<ProductEntity>> mObservableProducts;

    public ProductListViewModel(@NonNull Application application) {
        super(application);
        mObservableProducts = new MediatorLiveData<>();
        //set by default null, until we get data from the database;
        mObservableProducts.setValue(null);

        LiveData<List<ProductEntity>> products = ((BasicApp) application).getRepository().getProducts();

        //observe the changes of the products from the database and forward them
        mObservableProducts.addSource(products,mObservableProducts::setValue);
    }

    /**
     * Expose the LiveData products to query so the UI can observe it.
     */
    public LiveData<List<ProductEntity>> getProduct(){
        return mObservableProducts;
    }


}
