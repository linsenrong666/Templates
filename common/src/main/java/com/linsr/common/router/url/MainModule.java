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
        String SCAN_CODE = ACTIVITY + "scanCode";
        String PRODUCT_DETAILS = ACTIVITY + "productDetails";
        String CHILD_CATEGORY = ACTIVITY + "childCategory";
    }

    public interface Fragment {
        String HOME = FRAGMENT + "home";
        String HOME_GOODS = FRAGMENT + "homeGoods";
        String CATEGORY = FRAGMENT + "category";
        String MALL = FRAGMENT + "mall";
        String CART = FRAGMENT + "cart";
        String ME = FRAGMENT + "me";
        String CHILD_CATEGORY = FRAGMENT + "childCategory";
    }
}
