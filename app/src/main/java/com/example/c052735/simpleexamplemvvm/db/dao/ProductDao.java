package com.example.c052735.simpleexamplemvvm.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.c052735.simpleexamplemvvm.db.entity.ProductEntity;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductEntity> productId);

    @Query("select * from products where id = :productId")
    LiveData<ProductEntity> loadProduct(int productId);

    @Query("select * from products where id = :productId")
    ProductEntity loadProductSync(int productId);

}
