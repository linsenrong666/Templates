package com.linsr.main.model;

import com.linsr.common.model.BasePojo;
import com.linsr.common.model.BizPojo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2019/1/27 下午5:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CollectPojo extends BizPojo {

    /**
     * list : [{"id":"3","user_id":"6","goods_id":"833","add_time":"2017-09-30","short_name":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/833_G_1535188809048.jpg","shop_price":"29.70","promote_price":"0.00","promote_start_date":"0","promote_end_date":"0","count_collect":"1"},{"id":"2","user_id":"6","goods_id":"88","add_time":"2017-09-30","short_name":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/88_G_1491881485561.jpg","shop_price":"96.00","promote_price":"0.00","promote_start_date":"0","promote_end_date":"0","count_collect":"1"},{"id":"1","user_id":"6","goods_id":"46","add_time":"2017-09-28","short_name":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/46_G_1491618296882.jpg","shop_price":"45.80","promote_price":"0.00","promote_start_date":"0","promote_end_date":"0","count_collect":"1"}]
     * count : 3
     */

    private int count;
    private List<ListBean> list;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ListBean extends BasePojo {
        /**
         * id : 3
         * user_id : 6
         * goods_id : 833
         * add_time : 2017-09-30
         * short_name :
         * goods_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/833_G_1535188809048.jpg
         * shop_price : 29.70
         * promote_price : 0.00
         * promote_start_date : 0
         * promote_end_date : 0
         * count_collect : 1
         */

        private String id;
        private String user_id;
        private String goods_id;
        private String add_time;
        private String short_name;
        private String goods_img;
        private String shop_price;
        private String promote_price;
        private String promote_start_date;
        private String promote_end_date;
        private String count_collect;

    }
}
