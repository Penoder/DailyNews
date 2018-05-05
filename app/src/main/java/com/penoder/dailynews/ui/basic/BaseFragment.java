package com.penoder.dailynews.ui.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @author Penoder
 * @date 18-4-22.
 */
public abstract class BaseFragment extends Fragment {

    private long lastClickTime = -1;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    public abstract void initView();

    public abstract void initData();

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
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    private void startActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void startActivityForResult(Class cls, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        startActivityForResult(intent, requestCode);
    }

    private void startActivityForResult(Class cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }
}