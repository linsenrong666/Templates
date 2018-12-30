package com.linsr.common.router;

import android.content.Intent;

import java.util.ArrayList;

/**
 * Description
 *
 * @author Linsr 2018/12/30 下午3:57
 */
public class Flags extends ArrayList<Integer> {

    public static Flags clearTop() {
        Flags flags = new Flags();
        flags.add(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return flags;
    }
}
