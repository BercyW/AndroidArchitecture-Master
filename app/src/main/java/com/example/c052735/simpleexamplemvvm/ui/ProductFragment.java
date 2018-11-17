package com.example.c052735.simpleexamplemvvm.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c052735.simpleexamplemvvm.R;
import com.example.c052735.simpleexamplemvvm.databinding.ProductFragmentBinding;
import com.example.c052735.simpleexamplemvvm.model.Comment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    private ProductFragmentBinding mBinding;
    private CommentAdapter mCommentAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.product_fragment,container,false);
        mCommentAdapter = new CommentAdapter(mCommentClickCallback);
        mBinding.commentsList.setAdapter(mCommentAdapter);
        return mBinding.getRoot();

    }
    private final CommentClickCallback mCommentClickCallback = new CommentClickCallback() {
        @Override
        public void onClick(Comment comment) {

        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
