package com.penoder.dailynews.ui;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;

import com.penoder.dailynews.MyApplication;
import com.penoder.dailynews.R;
import com.penoder.dailynews.adapter.CommonFragmentAdapter;
import com.penoder.dailynews.config.SPKeyWord;
import com.penoder.dailynews.databinding.ActivityMainBinding;
import com.penoder.dailynews.ui.basic.BaseActivity;
import com.penoder.dailynews.ui.basic.BaseFragment;
import com.penoder.dailynews.ui.bookmark.MarkFragment;
import com.penoder.dailynews.ui.bookreview.ReviewFragment;
import com.penoder.dailynews.ui.bookshelf.ShelfFragment;
import com.penoder.dailynews.ui.bookstore.StoreFragment;
import com.penoder.dailynews.ui.splash.LoginActivity;
import com.penoder.dailynews.utils.Util;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.utils.SPUtils;
import com.penoder.mylibrary.utils.ScreenUtils;
import com.penoder.mylibrary.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Penoder
 * @date 18-4-20.
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    public ObservableField<String> userName = new ObservableField<>();

    public ObservableField<String> personSign = new ObservableField<>();

    public ObservableField<String> headUrl = new ObservableField<>();

    /**
     * 双击退出
     */
    private static Boolean isExit = false;

    private Timer tExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
        setDrawerLayout();
        String headIcon = (String) SPUtils.get(mContext, SPKeyWord.HEAD_ICON, "");
        if (TextUtils.isEmpty(headIcon)) {
            headIcon = Util.getRandomIcon();
            SPUtils.put(mContext, SPKeyWord.HEAD_ICON, headIcon);
        }
        headUrl.set(headIcon);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    private void initFragment() {
        BaseFragment shelfFragment = new ShelfFragment();
        BaseFragment storeFragment = new StoreFragment();
        BaseFragment reviewFragment = new ReviewFragment();
        BaseFragment markFragment = new MarkFragment();

        getBinding().pagerMain.setAdapter(new CommonFragmentAdapter(getSupportFragmentManager(),
                shelfFragment, storeFragment, reviewFragment, markFragment));
        getBinding().pagerMain.setOffscreenPageLimit(3);
        getBinding().pagerMain.setCurrentItem(0);
        getBinding().titleMain.setRightTitle(R.string.edit);
    }

    private void setDrawerLayout() {
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                getBinding().drawerLayout, null, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        getBinding().drawerLayout.addDrawerListener(mDrawerToggle);
        int width = (int) (0.75 * ScreenUtils.getScreenWidth(this));
        // 设置侧滑菜单 ListView_Slide
        ViewGroup.LayoutParams params = getBinding().scrollSlide.getLayoutParams();
        params.width = width;
        getBinding().scrollSlide.setLayoutParams(params);
        // 设置背景图高度
        ViewGroup.LayoutParams imgBgParams = getBinding().imgPersonBg.getLayoutParams();
        imgBgParams.height = (int) (0.618 * width);
        getBinding().imgPersonBg.setLayoutParams(imgBgParams);
    }

    /**
     * 标题栏左侧按钮点击事件
     */
    public ReplyCommand onLeftIconCommand = new ReplyCommand(() -> getBinding().drawerLayout.openDrawer(Gravity.START));

    /**
     * 标题栏右侧标题按钮点击事件
     */
    public ReplyCommand onRightTitleCommand = new ReplyCommand(() -> {

    });

    /**
     * 头像点击事件
     */
    public ReplyCommand onAvatarCommand = new ReplyCommand(() -> {
        if (MyApplication.getInstance().isLogin()) {
            ToastUtil.showShortToast(mContext, "头像点击事件");
        } else {
            LoginActivity.startAction(this);
        }
    });

    /**
     * 背景图像点击事件
     */
    public ReplyCommand onPersonBgCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "背景图像点击事件");
    });

    /**
     * 书架菜单点击按钮
     */
    public ReplyCommand onBookShelfCommand = new ReplyCommand(() -> {
        // false 表示页卡切换时不要滑动动画
        getBinding().pagerMain.setCurrentItem(0, false);
        getBinding().titleMain.setLeftIcon(R.drawable.icon_book_shelf_white);
        getBinding().titleMain.setTitle(R.string.book_shelf);
        getBinding().titleMain.setRightTitle(R.string.edit);
        getBinding().drawerLayout.closeDrawers();
    });

    /**
     * 书城菜单点击按钮
     */
    public ReplyCommand onBookStoreCommand = new ReplyCommand(() -> {
        getBinding().pagerMain.setCurrentItem(1, false);
        getBinding().titleMain.setLeftIcon(R.drawable.icon_book_store_white);
        getBinding().titleMain.setTitle(R.string.book_store);
        getBinding().titleMain.setRightTitle("");
        getBinding().drawerLayout.closeDrawers();
    });

    /**
     * 书评菜单点击按钮
     */
    public ReplyCommand onBookReviewCommand = new ReplyCommand(() -> {
        getBinding().pagerMain.setCurrentItem(2, false);
        getBinding().titleMain.setLeftIcon(R.drawable.icon_book_review_white);
        getBinding().titleMain.setTitle(R.string.book_review);
        getBinding().titleMain.setRightTitle("");
        getBinding().drawerLayout.closeDrawers();
    });

    /**
     * 书签菜单点击按钮
     */
    public ReplyCommand onBookMarkCommand = new ReplyCommand(() -> {
        getBinding().pagerMain.setCurrentItem(3, false);
        getBinding().titleMain.setLeftIcon(R.drawable.icon_book_mark_white);
        getBinding().titleMain.setTitle(R.string.book_mark);
        getBinding().titleMain.setRightTitle("");
        getBinding().drawerLayout.closeDrawers();
    });

    @Override
    public void onBackPressed() {
        if (getBinding().drawerLayout.isDrawerOpen(Gravity.START))
            getBinding().drawerLayout.closeDrawers();
        else
            doubleClickExit();
    }

    /**
     * 双击退出
     */
    private void doubleClickExit() {
        if (!isExit) {
            isExit = true; // 准备退出
            ToastUtil.showShortToast(this, "再按一次退出");
            if (tExit == null) {
                tExit = new Timer();
            }
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 1000); // 如果1秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            overridePendingTransition(0, 0);
            System.exit(0);
        }
    }
}
