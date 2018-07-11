package com.linsr.common.router.url;

/**
 * Description
 *
 * @author Linsr 2018/6/29 下午2:34
 */
public class BookModule {

    private static final String ROOT = "/books/";

    public interface Activity {
        String ACTIVITY = ROOT + "activity/";

        String MAIN = ACTIVITY + "main";
    }

    public interface Fragment {
        String FRAGMENT = ROOT + "fragment/";

        String TEST1 = FRAGMENT + "test1";
    }
}
