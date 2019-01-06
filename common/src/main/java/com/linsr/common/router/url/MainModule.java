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

        interface ProductDetailsParams {
            String GOODS_ID = "goods_id";
        }

        String CHILD_CATEGORY = ACTIVITY + "childCategory";

        interface ChildCategoryParams {
            String FIRST_CATEGORY_ID = "first_category_id";
            String SECOND_CATEGORY_ID = "second_category_id";
            String ENTER_POSITION = "enter_position";
        }

        String BALANCE = ACTIVITY + "balance";
        String ADDRESS_LIST = ACTIVITY + "addressList";
        String ADD_ADDRESS = ACTIVITY + "addAddress";

        String SEARCH = ACTIVITY + "search";
        String SEARCH_RESULT = ACTIVITY + "searchResult";

        interface SearchResultParams {
            String KEYWORD = "keyword";
        }

        String PAY_RESULT = ACTIVITY + "payResult";

        String ORDER_MAIN = ACTIVITY + "orderMain";

        interface OrderMainParams {
            String ORDER_STATUS = "order_status";
        }

        String COMMENT = ACTIVITY + "comment";
        String COMMENT_RESULT = ACTIVITY + "commentResult";
        String AFTER_SALES = ACTIVITY + "afterSales";
        String AFTER_SALES_DETAILS = ACTIVITY + "afterSalesDetails";
        String FLASH_SALE = ACTIVITY + "flashSale";
        String DAILY_NEW = ACTIVITY + "dailyNew";
        String AUCTION_DETAILS = ACTIVITY + "auctionDetails";
        String FOLLOW_SHOP = ACTIVITY + "followShop";
        String MY_COLLECT = ACTIVITY + "myCollect";
    }

    public interface Fragment {
        String HOME = FRAGMENT + "home";
        String RECOMMEND_GOODS = FRAGMENT + "recommendGoods";
        String CATEGORY = FRAGMENT + "category";
        String MALL_CONTAINER = FRAGMENT + "mallContainer";
        String CART = FRAGMENT + "cart";
        String ME = FRAGMENT + "me";

        String CHILD_CATEGORY = FRAGMENT + "childCategory";

        interface ChildCategoryParams {
            String FID = "fid";
            String SID = "sid";
        }

        String CATEGORY_DETAILS = FRAGMENT + "categoryDetails";

        interface CategoryDetailsParams {
            String CATEGORY_BEAN = "category_bean";
        }

        String ORDER_LIST = FRAGMENT + "orderList";

        interface OrderListParams {
            String ORDER_LIST_STATUS = "order_list_status";
        }

        String MALL_HOME = FRAGMENT + "mallHome";
        String FLASH_SALE = FRAGMENT + "flashSale";
        String AUCTION = FRAGMENT + "auction";
        String GROUP_SHOPPING = FRAGMENT + "groupShopping";
    }
}
