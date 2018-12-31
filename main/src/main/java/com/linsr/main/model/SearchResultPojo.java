package com.linsr.main.model;

import com.linsr.common.model.BasePojo;
import com.linsr.common.model.BizPojo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/12/31 下午4:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchResultPojo extends BizPojo {

    /**
     * list : [{"goods_id":"421","goods_name":"【宅女】本色抽纸3提9包（每提3包，每包140抽）","market_price":"0.00","is_new":"1","is_best":"1","is_hot":"0","org_price":"0.00","shop_price":"0.00","promote_price":"","promote_start_date":"0","promote_end_date":"0","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201801/goods_img/421_G_1516591870006.jpg"},{"goods_id":"758","goods_name":"【宅女】日用x2+护垫x1","market_price":"0.00","is_new":"1","is_best":"1","is_hot":"0","org_price":"0.00","shop_price":"0.00","promote_price":"","promote_start_date":"0","promote_end_date":"0","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/758_G_1535189201190.jpg"},{"goods_id":"757","goods_name":"【宅女】日用x1+护垫x1+加长夜用x1","market_price":"0.00","is_new":"1","is_best":"1","is_hot":"0","org_price":"0.00","shop_price":"0.00","promote_price":"","promote_start_date":"0","promote_end_date":"0","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/757_G_1535189365037.jpg"},{"goods_id":"756","goods_name":"【宅女】日用x1+夜用x1+加长夜用x1","market_price":"0.00","is_new":"1","is_best":"1","is_hot":"0","org_price":"0.00","shop_price":"0.00","promote_price":"","promote_start_date":"0","promote_end_date":"0","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/756_G_1535189381685.jpg"},{"goods_id":"755","goods_name":"【宅女】日用x1+护垫x1+夜用x1","market_price":"0.00","is_new":"1","is_best":"1","is_hot":"0","org_price":"0.00","shop_price":"0.00","promote_price":"","promote_start_date":"0","promote_end_date":"0","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/755_G_1535189399180.jpg"},{"goods_id":"754","goods_name":"【宅女】日用x2+夜用X1","market_price":"0.00","is_new":"1","is_best":"1","is_hot":"0","org_price":"0.00","shop_price":"0.00","promote_price":"","promote_start_date":"0","promote_end_date":"0","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/754_G_1535189421348.jpg"},{"goods_id":"753","goods_name":"【宅女】日用x3","market_price":"0.00","is_new":"1","is_best":"1","is_hot":"0","org_price":"0.00","shop_price":"0.00","promote_price":"","promote_start_date":"0","promote_end_date":"0","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/753_G_1535189433546.jpg"},{"goods_id":"752","goods_name":"【宅女】护垫x3","market_price":"0.00","is_new":"1","is_best":"1","is_hot":"0","org_price":"0.00","shop_price":"0.00","promote_price":"","promote_start_date":"0","promote_end_date":"0","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/752_G_1535189448797.jpg"},{"goods_id":"751","goods_name":"【宅女】加长夜用x3","market_price":"0.00","is_new":"1","is_best":"1","is_hot":"0","org_price":"0.00","shop_price":"0.00","promote_price":"","promote_start_date":"0","promote_end_date":"0","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/751_G_1535189530103.jpg"}]
     * count : 10
     */

    private int count;
    private List<ListBean> list;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListBean extends BasePojo {
        /**
         * goods_id : 421
         * goods_name : 【宅女】本色抽纸3提9包（每提3包，每包140抽）
         * market_price : 0.00
         * is_new : 1
         * is_best : 1
         * is_hot : 0
         * org_price : 0.00
         * shop_price : 0.00
         * promote_price :
         * promote_start_date : 0
         * promote_end_date : 0
         * goods_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201801/goods_img/421_G_1516591870006.jpg
         */

        private String goods_id;
        private String goods_name;
        private String market_price;
        private String is_new;
        private String is_best;
        private String is_hot;
        private String org_price;
        private String shop_price;
        private String promote_price;
        private String promote_start_date;
        private String promote_end_date;
        private String goods_img;
    }
}
