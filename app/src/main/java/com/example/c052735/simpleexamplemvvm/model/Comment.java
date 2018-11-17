package com.example.c052735.simpleexamplemvvm.model;




import java.util.Date;

public interface Comment {
    int getId();
    int getProductId();
    String getText();
    Date getPostedAt();
}
