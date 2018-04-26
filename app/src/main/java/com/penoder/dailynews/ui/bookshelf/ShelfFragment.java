package com.penoder.dailynews.ui.bookshelf;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.penoder.dailynews.R;
import com.penoder.dailynews.databinding.FragmentShelfBinding;
import com.penoder.dailynews.ui.basic.BaseFragment;

/**
 * 书架
 *
 * @author Penoder
 * @date 18-4-25.
 */
public class ShelfFragment extends BaseFragment {

    private FragmentShelfBinding shelfBinding;

    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        shelfBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shelf, container, false);
        shelfBinding.executePendingBindings();
        shelfBinding.setViewModel(this);
        return shelfBinding.getRoot();
    }
}
