package com.penoder.dailynews.ui.splash;

import android.content.Intent;
import android.databinding.ObservableField;
import android.os.CountDownTimer;

import com.penoder.dailynews.R;
import com.penoder.dailynews.databinding.ActivitySplashBinding;
import com.penoder.dailynews.ui.MainActivity;
import com.penoder.dailynews.ui.basic.BaseActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    public ObservableField<String> splashImgUrl = new ObservableField<>();

    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        super.initData();
        startTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    private void startTimer() {
        if (splashTimer == null) {
            splashTimer = new SplashTimer(3000, 1000);
        }
        splashTimer.start();
    }

    private void cancelTimer() {
        if (splashTimer != null) {
            splashTimer.cancel();
            splashTimer = null;
        }
    }

    private SplashTimer splashTimer;

    private class SplashTimer extends CountDownTimer {

        SplashTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            startActivity(new Intent(mContext, MainActivity.class));
            cancelTimer();
            finish();
        }
    }
}
