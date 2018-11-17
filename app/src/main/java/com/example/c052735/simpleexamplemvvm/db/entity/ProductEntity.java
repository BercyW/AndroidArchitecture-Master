package com.example.c052735.simpleexamplemvvm.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.c052735.simpleexamplemvvm.model.Product;

@Entity(tableName = "products")
public class ProductEntity implements Product{

    @PrimaryKey
    private  int id;
    private String name;
    private  String description;
    private  int price;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    public ProductEntity(int id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public ProductEntity(Product product) {
        this.id= product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public ProductEntity() {

    }
}
