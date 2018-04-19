package com.penoder.mylibrary.mvvm.bindingadapter.titlebar;

import android.databinding.BindingAdapter;
import android.view.View;

import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.view.CustomTitle;

/**
 * @author Penoder
 * @date 18-4-19.
 */

public class ViewBindingAdapter {

    @BindingAdapter({"titleClickCommand"})
    public static void titleClickCommand(CustomTitle titleView, final ReplyCommand titleClickCommand) {
        titleView.setOnTitleClickListener(new CustomTitle.OnTitleClickListener() {
            @Override
            public void onTitleClick(View view) {
                if (titleClickCommand != null) {
                    titleClickCommand.execute();
                }
            }

            @Override
            public void onTitleDoubleClick(View view) {

            }

            @Override
            public boolean onLeftIconClick(View view) {
                return false;
            }

            @Override
            public void onRightIconClick(View view) {

            }
        });
    }

    @BindingAdapter({"titleDoubleClickCommand"})
    public static void titleDoubleClickCommand(CustomTitle titleView, final ReplyCommand titleDoubleClickCommand) {
        titleView.setOnTitleClickListener(new CustomTitle.OnTitleClickListener() {
            @Override
            public void onTitleClick(View view) {
            }

            @Override
            public void onTitleDoubleClick(View view) {
                if (titleDoubleClickCommand != null) {
                    titleDoubleClickCommand.execute();
                }
            }

            @Override
            public boolean onLeftIconClick(View view) {
                return false;
            }

            @Override
            public void onRightIconClick(View view) {

            }
        });
    }

    @BindingAdapter({"leftClickCommand"})
    public static void leftClickCommand(CustomTitle titleView, final ReplyCommand leftClickCommand) {
        titleView.setOnTitleClickListener(new CustomTitle.OnTitleClickListener() {
            @Override
            public void onTitleClick(View view) {
            }

            @Override
            public void onTitleDoubleClick(View view) {

            }

            @Override
            public boolean onLeftIconClick(View view) {
                if (leftClickCommand != null) {
                    leftClickCommand.execute();
                }
                return true;
            }

            @Override
            public void onRightIconClick(View view) {

            }
        });
    }

    @BindingAdapter({"rightClickCommand"})
    public static void rightClickCommand(CustomTitle titleView, final ReplyCommand rightClickCommand) {
        titleView.setOnTitleClickListener(new CustomTitle.OnTitleClickListener() {
            @Override
            public void onTitleClick(View view) {
            }

            @Override
            public void onTitleDoubleClick(View view) {

            }

            @Override
            public boolean onLeftIconClick(View view) {
                return false;
            }

            @Override
            public void onRightIconClick(View view) {
                if (rightClickCommand != null) {
                    rightClickCommand.execute();
                }
            }
        });
    }
}
