package com.linsr.main.utils;

import com.linsr.main.adapters.cart.TreePojo;
import com.linsr.main.model.CartGoodsPojo;
import com.linsr.main.model.CartShopPojo;
import com.linsr.main.model.CategoryMenuPojo;
import com.linsr.main.model.FindPojo;
import com.linsr.main.model.HomePojo;
import com.linsr.main.model.RecommendPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:43
 */
public class Mock {

    public static List<CategoryMenuPojo> getMenuList(int size) {
        List<CategoryMenuPojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            CategoryMenuPojo pojo = new CategoryMenuPojo();
            pojo.setTitle("item:" + i);
            list.add(pojo);
        }
        return list;
    }

    public static List<FindPojo> getFindList(int size) {
        int[] arr = {0, 1, 2, 3};
        List<FindPojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            FindPojo pojo = new FindPojo();
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
            for (int j = 0; j < 3; j++) {
                goodsPojos.add(new CartGoodsPojo("GOODS:" + j));
            }
            treePojo.setChildPojo(goodsPojos);
            list.add(treePojo);
        }
        return list;
    }
}
