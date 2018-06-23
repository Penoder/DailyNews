package com.penoder.dailynews.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * 竖直方向的滑动事件不再向下分发
 */
public class DispatchLinearLayout extends LinearLayout {

    private boolean dispatch = false;

    /**
     * 记录用户按在屏幕上时的坐标（downX, downY）
     * 以及离开屏幕上时的坐标（upX, upY）
     */
    private float downX;
    private float downY;

    /**
     * 设备能识别到的最小滑动距离
     */
    private int minSlidePace;

    public DispatchLinearLayout(Context context) {
        super(context);
        minSlidePace = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public DispatchLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        minSlidePace = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (dispatch) {
            return super.dispatchTouchEvent(ev);
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downX = ev.getX();
            downY = ev.getY();
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            float upX = ev.getX();
            float upY = ev.getY();

            // y 轴移动的距离大于 x 轴的距离
            if (Math.abs(upX - downY) > minSlidePace && Math.abs(upY - downY) > Math.abs(upX - downX) && !dispatch) {
                return false;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setDispatch(boolean dispatch) {
        this.dispatch = dispatch;
    }
}
