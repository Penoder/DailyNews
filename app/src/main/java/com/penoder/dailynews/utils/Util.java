package com.penoder.dailynews.utils;

import android.annotation.SuppressLint;

import com.penoder.dailynews.config.BaseApi;

public class Util {

    @SuppressLint("DefaultLocale")
    public static String getRandomIcon() {
        return BaseApi.BASE_URL + BaseApi.HEAD_ICON + String.format("%02d", (int) (Math.random() * 355 + 1)) + ".png";
    }

}
