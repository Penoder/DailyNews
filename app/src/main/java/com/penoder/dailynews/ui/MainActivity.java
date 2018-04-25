package com.penoder.dailynews.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.ViewGroup;

import com.penoder.dailynews.R;
import com.penoder.dailynews.adapter.CommonFragmentAdapter;
import com.penoder.dailynews.databinding.ActivityMainBinding;
import com.penoder.dailynews.ui.basic.BaseFragment;
import com.penoder.dailynews.ui.basic.BaseFragmentActivity;
import com.penoder.dailynews.ui.bookshelf.ShelfFragment;
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

    private BaseFragment shelfFragment;

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

    private void initFragment() {
        shelfFragment = new ShelfFragment();

        mainBinding.pagerMain.setAdapter(new CommonFragmentAdapter(getSupportFragmentManager(), shelfFragment));
        mainBinding.pagerMain.setOffscreenPageLimit(1);
        mainBinding.pagerMain.setCurrentItem(0);
    }

    private void setDrawerLayout() {
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mainBinding.drawerLayout,
                R.drawable.icon_menu, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mainBinding.drawerLayout.setDrawerListener(mDrawerToggle);

        int width = 4 * ScreenUtils.getScreenWidth(this) / 5;

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
    public ReplyCommand onLeftCommand = new ReplyCommand(() -> mainBinding.drawerLayout.openDrawer(Gravity.START));

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
