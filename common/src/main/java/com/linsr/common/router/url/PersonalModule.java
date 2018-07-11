package com.linsr.common.router.url;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午6:00
 */
public class PersonalModule {

    private static final String ROOT = "/personal/";

    public interface Activity {
        String ACTIVITY = ROOT + "activity/";
    }

    public interface Fragment {
        String FRAGMENT = ROOT + "fragment/";

        String ME = FRAGMENT + "me";
    }
}
