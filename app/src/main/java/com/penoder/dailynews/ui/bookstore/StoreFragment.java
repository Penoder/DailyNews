package com.penoder.dailynews.ui.bookstore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.penoder.dailynews.R;
import com.penoder.dailynews.ui.basic.BaseFragment;


/**
 * 书城
 *
 * @author Penoder
 * @date 18-4-25.
 */
public class StoreFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
