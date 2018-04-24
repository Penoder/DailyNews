package com.penoder.mylibrary.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.penoder.mylibrary.R;
import com.penoder.mylibrary.utils.DensityUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Penoder
 * @date 18-4-19.
 */
public class CustomTitle extends LinearLayout {

    private Context mContext;

    /**
     * 标题
     */
    private TextView txtTitle;

    /**
     * 左侧按钮（一般返回键）
     */
    private ImageView imgLeftIcon;

    /**
     * 左标题
     */
    private TextView txtLeftTitle;

    /**
     * 右侧菜单按钮
     */
    private ImageView imgMenuIcon;

    private boolean isDoubleClick;

    private Timer tExit;

    public OnTitleClickListener onTitleClickListener;

    public CustomTitle(Context context) {
        this(context, null);
    }

    public CustomTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_title_bar, null);
        txtTitle = (TextView) view.findViewById(R.id.txt_title);
        imgLeftIcon = (ImageView) view.findViewById(R.id.img_leftIcon);
        txtLeftTitle = (TextView) view.findViewById(R.id.txt_leftTitle);
        imgMenuIcon = (ImageView) view.findViewById(R.id.img_menuIcon);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTitle);
            for (int i = 0; i < typedArray.getIndexCount(); i++) {
                int styleable = typedArray.getIndex(i);
                if (styleable == R.styleable.CustomTitle_leftIcon) { // 左图标
                    int leftIcon = typedArray.getResourceId(R.styleable.CustomTitle_leftIcon, 0);
                    imgLeftIcon.setImageResource(leftIcon);
                } else if (styleable == R.styleable.CustomTitle_leftTitle) {  // 左标题
                    String leftTitle = (String) typedArray.getText(R.styleable.CustomTitle_leftTitle);
                    txtLeftTitle.setText(leftTitle);
                } else if (styleable == R.styleable.CustomTitle_titleTxt) { // 标题
                    String mTitle = (String) typedArray.getText(R.styleable.CustomTitle_titleTxt);
                    txtTitle.setText(mTitle);
                } else if (styleable == R.styleable.CustomTitle_rightIcon) { // 右菜单图标，与文字不可同时存在
                    int rightIcon = typedArray.getResourceId(R.styleable.CustomTitle_rightIcon, 0);
                    imgMenuIcon.setImageResource(rightIcon);
                } else if (styleable == R.styleable.CustomTitle_textColor) {
                    int textColor = typedArray.getColor(R.styleable.CustomTitle_textColor, 0);
                    txtLeftTitle.setTextColor(textColor);
                    txtTitle.setTextColor(textColor);
                } else if (styleable == R.styleable.CustomTitle_leftTitleSize) {
                    float leftTitleSize = typedArray.getDimension(R.styleable.CustomTitle_leftTitleSize, 0);
                    leftTitleSize = DensityUtils.dp2sp(mContext, leftTitleSize);
                    txtTitle.setTextSize(leftTitleSize);
                } else if (styleable == R.styleable.CustomTitle_titleSize) {
                    float titleSize = typedArray.getDimension(R.styleable.CustomTitle_titleSize, 0);
                    titleSize = DensityUtils.dp2sp(mContext, titleSize);
                    txtLeftTitle.setTextSize(titleSize);
                }
            }
            typedArray.recycle();
        }
        addView(view);

        // 左边图标点击事件，如果代码中不重写，直接结束该Activity
        imgLeftIcon.setOnClickListener(v -> {
            if (onTitleClickListener != null) {
                if (!onTitleClickListener.onLeftIconClick(v)) {
                    ((Activity) mContext).finish();
                } else {
                    onTitleClickListener.onLeftIconClick(v);
                }
            } else {
                ((Activity) mContext).finish();
            }
        });

        imgMenuIcon.setOnClickListener(v -> {
            if (onTitleClickListener != null)
                onTitleClickListener.onRightIconClick(v);
        });

        txtTitle.setOnClickListener(v -> {
            if (onTitleClickListener != null) {
                if (!isDoubleClick) {
                    isDoubleClick = true;
                    if (tExit == null)
                        tExit = new Timer();
                    tExit.schedule(new TimerTask() {
                        @Override
                        public void run() {     // 这是在子线程当中，子线程设置单击双击事件的话，不能够更新UI啊，否则异常
                            isDoubleClick = false;
                            post(() -> {
                                onTitleClickListener.onTitleClick(view);     // 单击事件
                            });
                        }
                    }, 1000); // 如果1秒钟内没有点击两次，则启动定时器取消掉刚才执行的任务
                } else {
                    if (tExit != null)
                        tExit.cancel();
                    onTitleClickListener.onTitleDoubleClick(view);     // 双击事件
                }
            }
        });
    }

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    public interface OnTitleClickListener {

        /**
         * 标题点击事件
         */
        void onTitleClick(View view);

        /**
         * 标题双击事件
         */
        void onTitleDoubleClick(View view);

        /**
         * 左边图标点击事件
         */
        boolean onLeftIconClick(View view);

        /**
         * 右边图标点击事件
         */
        void onRightIconClick(View view);
    }

    // 设置左标题
    public void setleftTitle(String leftTitle) {
        txtLeftTitle.setText(leftTitle);
    }

    // 设置标题
    public void setTitle(String title) {
        txtTitle.setText(title);
    }

    // 设置左图标
    public void setLeftIcon(int resID) {
        imgLeftIcon.setImageResource(resID);
    }

    // 设置右图标
    public void setRightIcon(int resID) {
        imgMenuIcon.setImageResource(resID);
    }
}
