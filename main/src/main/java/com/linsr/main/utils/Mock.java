package com.linsr.main.utils;

import com.linsr.main.model.CategoryMenuPojo;
import com.linsr.main.model.FindPojo;
import com.linsr.main.model.HomePojo;

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
        List<FindPojo> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            FindPojo pojo = new FindPojo();
            pojo.setTitle("item:" + i);
            pojo.setContent("content content content content content content content content " +
                    "content content content content content content content content " +
                    "content content content content content content content content " +
                    "content content content content content content content content ");
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
}
