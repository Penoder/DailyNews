package com.penoder.dailynews.adapter;

import java.util.List;

/**
 * 添加 HeaderView 和 FooterView 的通用适配器
 *
 * @author Penoder
 * @date 18-5-5.
 */

public class CommonHFAdapter<T> extends CommonRecycleAdapter {

    public CommonHFAdapter(List datas, int layoutId) {
        super(datas, layoutId);
    }

    @Override
    public void onConvertView(Object o, ViewHolder holder, int position) {

    }
}
