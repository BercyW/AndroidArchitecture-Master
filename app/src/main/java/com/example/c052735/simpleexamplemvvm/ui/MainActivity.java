package com.example.c052735.simpleexamplemvvm.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c052735.simpleexamplemvvm.R;
import com.example.c052735.simpleexamplemvvm.model.Product;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        //add product if it is first time created
        if(savedInstanceState == null) {
            ProductListFragment fragment = new ProductListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,fragment,ProductListFragment.TAG).commit();
        }
    }
    //shows the product detail fragment
    public void show(Product product){

    }



}
