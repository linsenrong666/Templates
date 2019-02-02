package com.linsr.main.utils;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;

/**
 * Description
 *
 * @author Linsr 2019/1/25 上午12:01
 */
public class PriceUtils {

    public static String format(String money) {
        return "￥" + money;
    }

    public static CharSequence origStyle(String price, String originalPrice) {
        if (TextUtils.isEmpty(price)) {
            return "";
        }
        String _price = "￥" + price;
        String space = "   ";
        String _originalPrice = "￥" + originalPrice;
        String content = _price + space + _originalPrice;

        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        ssb.setSpan(new StyleSpan(Typeface.BOLD), 0, _price.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(0xffFB6182), 0, _price.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        ssb.setSpan(new StrikethroughSpan(), _price.length() + space.length(), content.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(new RelativeSizeSpan(0.6f), _price.length() + space.length(), content.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(0xff8F8F8F), _price.length() + space.length(), content.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return ssb;
    }
}
