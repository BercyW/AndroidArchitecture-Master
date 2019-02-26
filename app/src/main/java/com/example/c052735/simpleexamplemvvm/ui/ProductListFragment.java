package com.example.c052735.simpleexamplemvvm.ui;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c052735.simpleexamplemvvm.R;
import com.example.c052735.simpleexamplemvvm.databinding.ListFragmentBinding;
import com.example.c052735.simpleexamplemvvm.db.entity.ProductEntity;
import com.example.c052735.simpleexamplemvvm.model.Product;
import com.example.c052735.simpleexamplemvvm.viewmodel.ProductListViewModel;

import java.util.List;
import java.util.Objects;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProductListViewModel viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        subscribeUi(viewModel);
    }

    private void subscribeUi(ProductListViewModel viewModel) {
        //update the list when the data changes
        viewModel.getProduct().observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> mProduct) {
                if(mProduct!=null) {
                    mBinding.setIsLoading(false);
                    mProductAdapter.setProductList(mProduct);
                }else {
                    mBinding.setIsLoading(true);
                }
                //espresso does not know how to wait for data binding's loop so we execute changes
                //sync
                mBinding.executePendingBindings();
            }
        });
    }


    private final ProductClickCallback mProductClickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {
            if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) Objects.requireNonNull(getActivity())).show(product);
            }
        }
    };




}
