package com.penoder.dailynews.ui.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.penoder.mylibrary.utils.ToastUtil;

/**
 * @author Penoder
 * @date 18-4-22.
 */
public class BaseActivity extends AppCompatActivity {

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