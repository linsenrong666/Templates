package com.linsr.common.router.url;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午6:00
 */
public class MainModule {

    private static final String ROOT = "/main/";
    private static final String ACTIVITY = ROOT + "activity/";
    private static final String FRAGMENT = ROOT + "fragment/";

    public interface Activity {
        String MAIN = ACTIVITY + "main";
        String SCAN_CODE = ACTIVITY + "scan_code";
        String PRODUCT_DETAILS = ACTIVITY + "product_details";
    }

    public interface Fragment {
        String HOME = FRAGMENT + "home";
        String CATEGORY = FRAGMENT + "category";
        String FIND = FRAGMENT + "find";
        String CART = FRAGMENT + "cart";
        String ME = FRAGMENT + "me";

    }
}
