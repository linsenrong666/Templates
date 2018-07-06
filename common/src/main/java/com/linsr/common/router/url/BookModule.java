package com.linsr.common.router.url;

/**
 * Description
 *
 * @author Linsr 2018/6/29 下午2:34
 */
public interface BookModule {

    String ROOT = "/books/";

    interface Activity {
        String MAIN = ROOT + "main";
    }

    interface Fragment {
        String TEST1 = ROOT+"test1";
    }
}
