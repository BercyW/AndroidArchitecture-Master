package com.example.c052735.simpleexamplemvvm.ui;


import android.arch.lifecycle.Lifecycle;
import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c052735.simpleexamplemvvm.R;
import com.example.c052735.simpleexamplemvvm.databinding.ListFragmentBinding;
import com.example.c052735.simpleexamplemvvm.model.Product;

public class ProductListFragment extends Fragment{

    private ListFragmentBinding mBinding;
    public static final String TAG = "ProductListViewModel";

    private ProductAdapter mProductAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment,container,false);
        mProductAdapter = new ProductAdapter(mProductClickCallback);
        mBinding.productsList.setAdapter(mProductAdapter);
        return mBinding.getRoot();
    }



    private final ProductClickCallback mProductClickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {
            if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(product);
            }
        }
    };




}
