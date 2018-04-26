package com.penoder.dailynews.ui.bookmark;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.penoder.dailynews.R;
import com.penoder.dailynews.databinding.FragmentMarkBinding;
import com.penoder.dailynews.ui.basic.BaseFragment;

/**
 * @author Penoder
 * @date 18-4-26.
 */
public class MarkFragment extends BaseFragment {

    private FragmentMarkBinding markBinding;

    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        markBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mark, container, false);
        markBinding.executePendingBindings();
        markBinding.setViewModel(this);
        return markBinding.getRoot();
    }
}
