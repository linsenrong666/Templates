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

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class CatListBean extends BasePojo {
        /**
         * cat_id : 7
         * cat_name : 美容专区
         */

        private String cat_id;
        private String cat_name;

    }
//
//    @EqualsAndHashCode(callSuper = true)
//    @Data
//    public static class CatListGoodsBean extends BasePojo {
//        /**
//         * goods_id : 58
//         * goods_name : 【妖肌】玉颜淡化养肤套装（5合1）
//         * short_name :
//         * market_price : 800.00
//         * org_price : 598.00
//         * shop_price : 598.00
//         * promote_price : 0.00
//         * promote_start_date : 0
//         * promote_end_date : 0
//         * goods_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/58_G_1491634059947.jpg
//         * sales_count : 608
//         */
//
//        private String goods_id;
//        private String goods_name;
//        private String short_name;
//        private String market_price;
//        private String org_price;
//        private String shop_price;
//        private String promote_price;
//        private String promote_start_date;
//        private String promote_end_date;
//        private String goods_img;
//        private String sales_count;
//    }
}
