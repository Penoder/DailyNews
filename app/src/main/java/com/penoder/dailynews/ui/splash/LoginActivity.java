package com.penoder.dailynews.ui.splash;

import android.app.Activity;
import android.content.Intent;

import com.penoder.dailynews.R;
import com.penoder.dailynews.databinding.ActivityLoginBinding;
import com.penoder.dailynews.ui.basic.BaseActivity;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private final int REQUEST_CODE_REGISTER = 1;
    private final int REQUEST_CODE_FORGET = 2;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    public static void startAction(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    public ReplyCommand onRegisterCommand = new ReplyCommand(() -> {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, REQUEST_CODE_REGISTER);
    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_REGISTER:
                    break;
                case REQUEST_CODE_FORGET:
                    break;
                default:
                    break;
            }
        }
    }
}
