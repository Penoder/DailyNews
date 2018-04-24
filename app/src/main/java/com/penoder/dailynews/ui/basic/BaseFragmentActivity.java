package com.penoder.dailynews.ui.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.penoder.mylibrary.utils.ToastUtil;

/**
 * @author Penoder
 * @date 18-4-22.
 */
public class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        ToastUtil.cancelToast();
        super.onDestroy();
    }
}
