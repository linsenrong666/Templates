package com.linsr.main.logic;

import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;

/**
 * Description
 *
 * @author Linsr 2019/2/2 下午5:07
 */
public class UIHelper {

    public static void toMainActivity(int page) {
        Params params = new Params();
        params.add(MainModule.Activity.MainParams.SELECT_PAGE, page);
        Router.startActivity(MainModule.Activity.MAIN, params);
    }

}
