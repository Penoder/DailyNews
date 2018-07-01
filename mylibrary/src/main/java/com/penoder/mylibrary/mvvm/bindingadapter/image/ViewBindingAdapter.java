package com.penoder.mylibrary.mvvm.bindingadapter.image;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.utils.ImgLoadUtil;

/**
 * @author kelin
 * @date 16-3-24
 */
public final class ViewBindingAdapter {

    @BindingAdapter(value = {"uri", "placeholderImageRes", "request_width", "request_height", "onSuccessCommand", "onFailureCommand"}, requireAll = false)
    public static void loadImage(final ImageView imageView, String uri,
                                 @DrawableRes int placeholderImageRes,
                                 int width, int height,
                                 final ReplyCommand<GlideDrawable> onSuccessCommand,
                                 final ReplyCommand<Target<GlideDrawable>> onFailureCommand) {
        // 给 ImageView 打 Tag 需要 Request 对象，否则异常 java.lang.IllegalArgumentException: You must not call setTag() on a view Glide is targeting
        imageView.setImageResource(placeholderImageRes);
        RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                if (onFailureCommand != null) {
                    onFailureCommand.execute(target);
                }
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (onSuccessCommand != null) {
                    onSuccessCommand.execute(resource);
                }
                return false;
            }
        };

        if (width > 0 && height > 0) {
            ImgLoadUtil.loadImg(uri, placeholderImageRes, imageView, width, height, requestListener);
        } else {
            ImgLoadUtil.loadImg(uri, placeholderImageRes, imageView, requestListener);
        }
    }
}

