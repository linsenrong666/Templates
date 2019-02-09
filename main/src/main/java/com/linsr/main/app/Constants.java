package com.linsr.main.app;

/**
 * Description
 *
 * @author Linsr 2018/8/29 下午4:47
 */
public interface Constants {

    interface FloorType {
        int STORE_INFO = 1;//网店信息
        int BANNER = 2;//广告图
        int AD_ONE_DATA = 3;//首页推荐广告1
        int AD_TWO_DATA = 4;//首页推荐广告2
        int AD_THREE_DATA = 5;//首页推荐广告3
        int RECOMMEND_FOR_YOU = 6;//为你推荐
        int YIMA_STREET = 7;//姨妈街
        int ICON = 8;//icon

        int MENU = 92;//菜单
        int RECOMMEND_GOODS = 93;//推荐商品
        int SHOP_WINDOW = 94;//橱窗
        int ACTIVITY_ENTER = 95;//活动入口
        int FLASH_SALE = 96;//限时抢购
        int DAILY_NEW = 97;//每日上新
    }

    //value对应的页面顺序，不能随意改动
    interface OrderStatus {
        int ALL_ORDER = 0;
        int WAIT_PAY = 1;
        int WAIT_SEND = 2;
        int WAIT_RECEIVE = 3;
        int WAIT_COMMENT = 4;
    }

    interface Event {
        String UPDATE_CART_LIST = "update_cart_list";
        String UPDATE_CART_BADGE = "update_cart_badge";
    }

}
