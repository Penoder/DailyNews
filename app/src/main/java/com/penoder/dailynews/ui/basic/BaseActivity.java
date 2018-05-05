package com.penoder.dailynews.ui.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.penoder.mylibrary.utils.ToastUtil;

/**
 * @author Penoder
 * @date 18-4-22.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private long lastClickTime = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initData();
    }

    public abstract void initData();

    @Override
    protected void onDestroy() {
        ToastUtil.cancelToast();
        super.onDestroy();
    }

    private boolean isFastClick() {
        return isFastClick(200);
    }

    /**
     * 避免快速点击
     *
     * @param ms 两次点击之间需要的时间差
     * @return if return true, isFastClick, can't allow to click
     */
    private boolean isFastClick(int ms) {
        if (System.currentTimeMillis() - lastClickTime < ms) {
            return true;
        }
        lastClickTime = System.currentTimeMillis();
        return false;
    }

    private void startActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    private void startActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void startActivityForResult(Class cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    private void startActivityForResult(Class cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }
}
