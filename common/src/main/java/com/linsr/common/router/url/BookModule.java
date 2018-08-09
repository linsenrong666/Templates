package com.linsr.common.router.url;

/**
 * Description
 *
 * @author Linsr 2018/6/29 下午2:34
 */
public class BookModule {

    private static final String ROOT = "/books/";
    private static final String ACTIVITY = ROOT + "activity/";
    private static final String FRAGMENT = ROOT + "fragment/";

    public interface Activity {
        String MAIN = ACTIVITY + "main";
    }

    public interface Fragment {
        String TEST1 = FRAGMENT + "test1";
    }
}
