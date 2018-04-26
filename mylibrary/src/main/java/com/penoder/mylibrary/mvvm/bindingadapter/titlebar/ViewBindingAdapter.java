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

    @BindingAdapter({"titleClickCommand", "titleDoubleClickCommand"})
    public static void titleCommand(CustomTitle titleView, final ReplyCommand titleClickCommand, final ReplyCommand titleDoubleClickCommand) {
        titleView.setOnTitleClickListener(new CustomTitle.OnTitleClickListener() {
            @Override
            public void onTitleClick(View view) {
                if (titleClickCommand != null) {
                    titleClickCommand.execute();
                }
            }

            @Override
            public void onTitleDoubleClick(View view) {
                if (titleDoubleClickCommand != null) {
                    titleDoubleClickCommand.execute();
                }
            }
        });
    }

    @BindingAdapter({"leftIconClickCommand"})
    public static void leftIconClickCommand(CustomTitle titleView, final ReplyCommand leftIconClickCommand) {
        titleView.setOnLeftIconClickListener(view -> {
            if (leftIconClickCommand != null) {
                leftIconClickCommand.execute();
            }
        });
    }

    @BindingAdapter({"rightIconClickCommand"})
    public static void rightIconClickCommand(CustomTitle titleView, final ReplyCommand rightIconClickCommand) {
        titleView.setOnRightIconClickListener(view -> {
            if (rightIconClickCommand != null) {
                rightIconClickCommand.execute();
            }
        });
    }

    @BindingAdapter({"rightTitleClickCommand"})
    public static void rightTitleClickCommand(CustomTitle titleView, final ReplyCommand rightTitleClickCommand) {
        titleView.setOnRightTitleClickListener(view -> {
            if (rightTitleClickCommand != null) {
                rightTitleClickCommand.execute();
            }
        });
    }
}
