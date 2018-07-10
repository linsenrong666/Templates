package com.linsr.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午3:55
 */
public class ImageUtils {

    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}
