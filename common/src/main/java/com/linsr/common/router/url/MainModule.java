package com.linsr.common.router.url;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午6:00
 */
public class MainModule {

    private static final String ROOT = "/main/";

    public interface Activity {
        String ACTIVITY = ROOT + "activity/";

        String MAIN = ROOT + "main";
    }

    public interface Fragment {
        String FRAGMENT = ROOT + "fragment/";

        String HOME = FRAGMENT + "home";
        String CATEGORY = FRAGMENT + "category";

    }
}
