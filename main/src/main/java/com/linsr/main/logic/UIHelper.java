package com.linsr.main.logic;

import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.model.CartListPojo;

import java.util.List;

import static com.linsr.common.router.url.MainModule.Activity.ChildCategoryParams.ENTER_POSITION;
import static com.linsr.common.router.url.MainModule.Activity.ChildCategoryParams.FIRST_CATEGORY_ID;
import static com.linsr.common.router.url.MainModule.Activity.ChildCategoryParams.SECOND_CATEGORY_ID;

/**
 * Description
 *
 * @author Linsr 2019/2/2 下午5:07
 */
public class UIHelper implements MainModule.Activity {

    public static void toMainActivity(int page) {
        Params params = new Params();
        params.add(MainModule.Activity.MainParams.SELECT_PAGE, page);
        Router.startActivity(MainModule.Activity.MAIN, params);
    }

    public static void toCartPage() {
        toMainActivity(MainModule.Activity.MainParams.CART_PAGE);
    }

    public static void toChildCategoryActivity(int position, String fid, String sid) {
        Params params = new Params();
        params.add(ENTER_POSITION, position);
        params.add(FIRST_CATEGORY_ID, fid);
        params.add(SECOND_CATEGORY_ID, sid);
        Router.startActivity(MainModule.Activity.CHILD_CATEGORY, params);
    }

    public static void toBalanceActivity(List<CartListPojo.GoodsListBean.ListBean> list, double amount) {
        Params params = new Params();
        params.add(BalanceParams.ACT_BALANCE_GOODS_LIST, list);
        params.add(BalanceParams.ACT_BALANCE_AMOUNT, amount);
        Router.startActivity(MainModule.Activity.BALANCE, params);
    }

}
