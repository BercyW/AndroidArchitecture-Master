package com.example.c052735.simpleexamplemvvm.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c052735.simpleexamplemvvm.R;
import com.example.c052735.simpleexamplemvvm.databinding.ProductItemBinding;
import com.example.c052735.simpleexamplemvvm.model.Product;

import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    List<? extends Product> mProductList;
    @Nullable
    private final ProductClickCallback mProductClickCallback;

    public ProductAdapter(@Nullable ProductClickCallback clickCallback) {
        mProductClickCallback = clickCallback;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.product_item,parent,false);
        binding.setCallback(mProductClickCallback);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.binding.setProduct(mProductList.get(position));
        holder.binding.excutePendingBindings();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        final ProductItemBinding binding;

        ProductViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
