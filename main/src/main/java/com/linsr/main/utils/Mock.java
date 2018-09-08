package com.linsr.main.utils;

import com.linsr.main.R;
import com.linsr.main.adapters.cart.TreePojo;
import com.linsr.main.app.Constants;
import com.linsr.main.model.AddressPojo;
import com.linsr.main.model.CartGoodsPojo;
import com.linsr.main.model.CartShopPojo;
import com.linsr.main.model.CategoryMenuPojo;
import com.linsr.main.model.HomePojo;
import com.linsr.main.model.MenuItemPojo;
import com.linsr.main.model.OrderPojo;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.model.ShopWindowPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:43
 */
public class Mock {

    public static <T> List<T> getList(int size, Class<T> c) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            try {
                T obj = c.newInstance();
                list.add(obj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<ShopWindowPojo> getShopWindowList(int size) {
        List<ShopWindowPojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ShopWindowPojo pojo = new ShopWindowPojo();
            pojo.setText("item:" + i);
            list.add(pojo);
        }
        return list;
    }

    public static List<MenuItemPojo> getMenuItemList(int size) {
        List<MenuItemPojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            MenuItemPojo pojo = new MenuItemPojo();
            pojo.setIcon(R.mipmap.placeholders_icon);
            pojo.setText("item:" + i);
            list.add(pojo);
        }
        return list;
    }

    public static List<CategoryMenuPojo> getMenuList(int size) {
        List<CategoryMenuPojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            CategoryMenuPojo pojo = new CategoryMenuPojo();
            pojo.setTitle("item:" + i);
            list.add(pojo);
        }
        return list;
    }

    public static List<HomePojo> getFindList(int size) {
        int[] arr = {Constants.FloorType.BANNER,
                Constants.FloorType.MENU,
                Constants.FloorType.ACTIVITY_ENTER,
                Constants.FloorType.FLASH_SALE,
                Constants.FloorType.DAILY_NEW,};
        List<HomePojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            HomePojo pojo = new HomePojo();
            if (i == 0) {
                pojo.setFloorType(Constants.FloorType.BANNER);
                list.add(pojo);
                continue;
            }
            if (i == 1) {
                pojo.setFloorType(Constants.FloorType.MENU);
                list.add(pojo);
                continue;
            }
            if (i == 2) {
                pojo.setFloorType(Constants.FloorType.FLASH_SALE);
                list.add(pojo);
                continue;
            }
            if (i == 3) {
                pojo.setFloorType(Constants.FloorType.DAILY_NEW);
                list.add(pojo);
                continue;
            }
            if (i == 4 || i == 5 || i == 6) {
                pojo.setFloorType(Constants.FloorType.ACTIVITY_ENTER);
                list.add(pojo);
                continue;
            }
            if (i > 7) {
                pojo.setFloorType(Constants.FloorType.RECOMMEND_GOODS);
                list.add(pojo);
                continue;
            }
            int index = (int) (Math.random() * arr.length);
            pojo.setFloorType(arr[index]);
            list.add(pojo);
        }
        return list;
    }

    public static List<HomePojo> getHomeList(int size) {
        int[] arr = {Constants.FloorType.MENU,
                Constants.FloorType.ACTIVITY_ENTER,
                Constants.FloorType.RECOMMEND_GOODS,
                Constants.FloorType.SHOP_WINDOW};
        List<HomePojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            HomePojo pojo = new HomePojo();
            if (i == 0) {
                pojo.setFloorType(Constants.FloorType.BANNER);
                list.add(pojo);
                continue;
            }
            if (i == 1) {
                pojo.setFloorType(Constants.FloorType.MENU);
                list.add(pojo);
                continue;
            }

            if (i == 2) {
                pojo.setFloorType(Constants.FloorType.RECOMMEND_GOODS);
                list.add(pojo);
                continue;
            }

            if (i == 3) {
                pojo.setFloorType(Constants.FloorType.SHOP_WINDOW);
                list.add(pojo);
                continue;
            }
            if (i == 4) {
                pojo.setFloorType(Constants.FloorType.SHOP_WINDOW);
                list.add(pojo);
                continue;
            }
            int index = (int) (Math.random() * arr.length);
            pojo.setFloorType(arr[index]);
            list.add(pojo);
        }
        return list;
    }

    public static List<HomePojo> getGoodsList(int size) {
        List<HomePojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            HomePojo pojo = new HomePojo();
            pojo.setTitle("item:" + i);
            pojo.setDesc("content content content content content content content content " +
                    "content content content content content content content content " +
                    "content content content content content content content content " +
                    "content content content content content content content content ");
            pojo.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531463747502&di=dfd96a9a0909db0cad21818981866e8e&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201507%2F01%2F20150701165443_3XdLc.jpeg");
            list.add(pojo);
        }
        return list;
    }

    public static List<RecommendPojo> getRecommendList(int size) {
        List<RecommendPojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            RecommendPojo pojo = new RecommendPojo();
            pojo.setName("item:" + i);
            pojo.setPrice("$20 ");
            pojo.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531463747502&di=dfd96a9a0909db0cad21818981866e8e&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201507%2F01%2F20150701165443_3XdLc.jpeg");
            list.add(pojo);
        }
        return list;
    }


    public static List<TreePojo<CartShopPojo, CartGoodsPojo>> getCartList(int size) {
        List<TreePojo<CartShopPojo, CartGoodsPojo>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreePojo<CartShopPojo, CartGoodsPojo> treePojo = new TreePojo<>();
            treePojo.setParentPojo(new CartShopPojo("SHOP：" + i));
            List<CartGoodsPojo> goodsPojos = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                goodsPojos.add(new CartGoodsPojo("GOODS:" + j));
            }
            treePojo.setChildPojo(goodsPojos);
            list.add(treePojo);
        }
        return list;
    }

    public static List<AddressPojo> getAddressList(int size) {
        List<AddressPojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            AddressPojo po = new AddressPojo();
            po.setAddress("这是地址这是地址这是地址这是地址这是地址这是地址");
            po.setUserName("海贼王路飞" + i);
            po.setPhoneNumber("1333444455" + i);
            list.add(po);
        }
        return list;
    }

    public static List<String> getImageUrlList(int size) {
        Random random = new Random();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String url = imageurls[random.nextInt(imageurls.length)];
            list.add(url);
        }
        return list;
    }

    static String[] imageurls = {"http://img3.imgtn.bdimg.com/it/u=2378520397,2469794148&fm=26&gp=0.jpg",
            "http://img5.duitang.com/uploads/item/201411/26/20141126010732_tVxea.jpeg",
            "http://m.360buyimg.com/n12/jfs/t145/261/906922512/191965/47d31c61/539c3e6aNb2090387.jpg%21q70.jpg",
            "http://img1.cache.netease.com/catchpic/B/B5/B51EC5A97FCE8C5084FF46164C1A6CE1.jpeg",
            "http://img2.ph.126.net/_213dOvOYNHJXj5QbQ_byA==/6631481182072640663.jpg",
            "http://pic13.nipic.com/20110309/3156127_113825426000_2.jpg",
            "http://img.tupianzj.com/uploads/allimg/170926/9-1F9261P920.jpg",
            "http://i1.hdslb.com/bfs/face/d9d4dda907e86435d8fbd60e347b40c0574aed19.jpg",
            "http://img4.99114.com/group10/M00/B4/FF/rBADsloSzDOAW9cRAAIaY1Z3ADg669.jpg",
            "http://img5.duitang.com/uploads/item/201410/05/20141005194336_JzLe8.thumb.700_0.jpeg",
            "http://img4q.duitang.com/uploads/item/201408/17/20140817144152_VJPCt.thumb.700_0.jpeg",
            "http://img610.ph.126.net/5TtuVh-X_wzxX0J8cvq3Cg==/1958221412978120078.jpg",
            "http://img5.duitang.com/uploads/item/201110/07/20111007192646_nhQay.jpg",
            "http://cdnq.duitang.com/uploads/item/201501/23/20150123181132_jU3Hr.jpeg"};


    public static List<OrderPojo> getOrderList(int size) {
        List<OrderPojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            OrderPojo po = new OrderPojo();
            list.add(po);
        }
        return list;
    }
}
