package com.penoder.dailynews.ui;

import android.app.Application;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.ViewGroup;

import com.penoder.dailynews.R;
import com.penoder.dailynews.adapter.CommonFragmentAdapter;
import com.penoder.dailynews.databinding.ActivityMainBinding;
import com.penoder.dailynews.ui.basic.BaseFragment;
import com.penoder.dailynews.ui.basic.BaseFragmentActivity;
import com.penoder.dailynews.ui.bookmark.MarkFragment;
import com.penoder.dailynews.ui.bookreview.ReviewFragment;
import com.penoder.dailynews.ui.bookshelf.ShelfFragment;
import com.penoder.dailynews.ui.bookstore.StoreFragment;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.utils.ScreenUtils;
import com.penoder.mylibrary.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Penoder
 * @date 18-4-20.
 */
public class MainActivity extends BaseFragmentActivity {

    private ActivityMainBinding mainBinding;

    /**
     * 用户名
     */
    public ObservableField<String> userName = new ObservableField<>();

    /**
     * 个人签名
     */
    public ObservableField<String> personSign = new ObservableField<>();

    private Context mContext;

    /**
     * 双击退出
     */
    private static Boolean isExit = false;

    private Timer tExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setViewModel(this);
        mContext = this;
        initFragment();
        setDrawerLayout();
    }

    @Override
    public void initData() {

    }

    private void initFragment() {
        BaseFragment shelfFragment = new ShelfFragment();
        BaseFragment storeFragment = new StoreFragment();
        BaseFragment reviewFragment = new ReviewFragment();
        BaseFragment markFragment = new MarkFragment();

        mainBinding.pagerMain.setAdapter(new CommonFragmentAdapter(getSupportFragmentManager(),
                shelfFragment, storeFragment, reviewFragment, markFragment));
        mainBinding.pagerMain.setOffscreenPageLimit(3);
        mainBinding.pagerMain.setCurrentItem(0);
        mainBinding.titleMain.setMenuTitle(R.string.edit);
    }

    private void setDrawerLayout() {
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mainBinding.drawerLayout,
                R.drawable.icon_menu, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mainBinding.drawerLayout.setDrawerListener(mDrawerToggle);

        int width = (int) (0.75 * ScreenUtils.getScreenWidth(this));

        // 设置侧滑菜单 ListView_Slide
        ViewGroup.LayoutParams params = mainBinding.scrollSlide.getLayoutParams();
        params.width = width;
        mainBinding.scrollSlide.setLayoutParams(params);

        // 设置背景图高度
        ViewGroup.LayoutParams imgBgParams = mainBinding.imgPersonBg.getLayoutParams();
        imgBgParams.height = (int) (0.618 * width);
        mainBinding.imgPersonBg.setLayoutParams(imgBgParams);
    }

    /**
     * 标题栏左侧按钮点击事件
     */
    public ReplyCommand onLeftIconCommand = new ReplyCommand(() -> mainBinding.drawerLayout.openDrawer(Gravity.START));

    /**
     * 标题栏右侧标题按钮点击事件
     */
    public ReplyCommand onRightTitleCommand = new ReplyCommand(() -> {

    });

    /**
     * 头像点击事件
     */
    public ReplyCommand onAvatarCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "头像点击事件");
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
        mainBinding.pagerMain.setCurrentItem(0, false);
        mainBinding.titleMain.setLeftIcon(R.drawable.icon_book_shelf_white);
        mainBinding.titleMain.setTitle(R.string.book_shelf);
        mainBinding.titleMain.setMenuTitle(R.string.edit);
        mainBinding.drawerLayout.closeDrawers();
    });

    /**
     * 书城菜单点击按钮
     */
    public ReplyCommand onBookStoreCommand = new ReplyCommand(() -> {
        mainBinding.pagerMain.setCurrentItem(1, false);
        mainBinding.titleMain.setLeftIcon(R.drawable.icon_book_store_white);
        mainBinding.titleMain.setTitle(R.string.book_store);
        mainBinding.titleMain.setMenuTitle("");
        mainBinding.drawerLayout.closeDrawers();
    });

    /**
     * 书评菜单点击按钮
     */
    public ReplyCommand onBookReviewCommand = new ReplyCommand(() -> {
        mainBinding.pagerMain.setCurrentItem(2, false);
        mainBinding.titleMain.setLeftIcon(R.drawable.icon_book_review_white);
        mainBinding.titleMain.setTitle(R.string.book_review);
        mainBinding.titleMain.setMenuTitle("");
        mainBinding.drawerLayout.closeDrawers();
    });

    /**
     * 书签菜单点击按钮
     */
    public ReplyCommand onBookMarkCommand = new ReplyCommand(() -> {
        mainBinding.pagerMain.setCurrentItem(3, false);
        mainBinding.titleMain.setLeftIcon(R.drawable.icon_book_mark_white);
        mainBinding.titleMain.setTitle(R.string.book_mark);
        mainBinding.titleMain.setMenuTitle("");
        mainBinding.drawerLayout.closeDrawers();
    });

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {

        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public class MyClass extends Application {

        @Override
        public void onLowMemory() {
            super.onLowMemory();
        }
    }

    @Override
    public void onBackPressed() {
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
            }, 1500); // 如果1.5秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            overridePendingTransition(0, 0);
            System.exit(0);
        }
    }
}
