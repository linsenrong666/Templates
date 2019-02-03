package com.linsr.main.model;

import com.linsr.common.model.BasePojo;
import com.linsr.common.model.BizPojo;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/12/30 下午9:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChildCategoryPojo extends BizPojo {


    private List<CatListBean> cat_list;
    private List<IsbestBean> cat_list_goods;
    private String cat_img;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class CatListBean extends BasePojo {
        /**
         * cat_id : 7
         * cat_name : 美容专区
         */

        private String cat_id;
        private String cat_name;

        public CatListBean(){}

        public CatListBean(String cat_id) {
            this.cat_id = cat_id;
        }
    }
}
