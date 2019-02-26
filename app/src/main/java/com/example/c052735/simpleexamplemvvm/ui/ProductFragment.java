package com.example.c052735.simpleexamplemvvm.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c052735.simpleexamplemvvm.R;
import com.example.c052735.simpleexamplemvvm.databinding.ProductFragmentBinding;
import com.example.c052735.simpleexamplemvvm.db.entity.CommentEntity;
import com.example.c052735.simpleexamplemvvm.db.entity.ProductEntity;
import com.example.c052735.simpleexamplemvvm.model.Comment;
import com.example.c052735.simpleexamplemvvm.viewmodel.ProductViewModel;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    private static final String KEY_PRODUCT_ID = "product_id";

    private ProductFragmentBinding mBinding;
    private CommentAdapter mCommentAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.product_fragment,container,false);

        //create and set the adapter for the recyclerview
        mCommentAdapter = new CommentAdapter(mCommentClickCallback);
        mBinding.commentsList.setAdapter(mCommentAdapter);
        return mBinding.getRoot();

    }
    private final CommentClickCallback mCommentClickCallback = new CommentClickCallback() {
        @Override
        public void onClick(Comment comment) {
            //no-op
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProductViewModel.Factory factory = new ProductViewModel.Factory(Objects.requireNonNull(getActivity()).getApplication(),
                Objects.requireNonNull(getArguments()).getInt(KEY_PRODUCT_ID));
        final ProductViewModel model = ViewModelProviders.of(this,factory).get(ProductViewModel.class);

        mBinding.setProductViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final ProductViewModel model) {
        //observe product data
        model.getObservableProduct().observe(this, new Observer<ProductEntity>() {
            @Override
            public void onChanged(@Nullable ProductEntity productEntity) {
                model.setProduct(productEntity);
            }
        });

        //observe comments
        model.getComments().observe(this, new Observer<List<CommentEntity>>() {
            @Override
            public void onChanged(@Nullable List<CommentEntity> commentEntities) {
                if(commentEntities !=null) {
                    mBinding.setIsLoading(false);
                    mCommentAdapter.setCommentList(commentEntities);
                }else {
                    mBinding.setIsLoading(true);
                }
            }
        });
    }

    // creates product fragment for specific product ID;
    public static ProductFragment forProduct(int productId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID,productId);
        fragment.setArguments(args);
        return fragment;
    }

}
