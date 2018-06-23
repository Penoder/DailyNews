package com.penoder.dailynews.ui.splash;

import com.penoder.dailynews.R;
import com.penoder.dailynews.databinding.ActivityLoginBinding;
import com.penoder.dailynews.databinding.ActivityRegisterBinding;
import com.penoder.dailynews.ui.basic.BaseActivity;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_register;
    }

    public ReplyCommand onLoginCommand = new ReplyCommand(this::finish);
}
