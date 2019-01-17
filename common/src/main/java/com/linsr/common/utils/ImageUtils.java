package com.linsr.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.linsr.common.utils.glide.GlideCircleTransform;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午3:55
 */
public class ImageUtils {

    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    public static void loadCircle(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).bitmapTransform(new GlideCircleTransform(context)).into(imageView);
    }


}
