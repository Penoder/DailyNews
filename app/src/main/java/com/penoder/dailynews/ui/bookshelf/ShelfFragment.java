package com.penoder.dailynews.ui.bookshelf;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.penoder.dailynews.R;
import com.penoder.dailynews.ui.basic.BaseFragment;

/**
 * 书架
 *
 * @author Penoder
 * @date 18-4-25.
 */
public class ShelfFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shelf, container, false);
        return view;
    }
}
