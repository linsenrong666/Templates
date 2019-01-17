package com.linsr.main.model.bean;

import com.linsr.common.model.BasePojo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午9:39
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderListBean extends BasePojo {

    /**
     * order_id : 22973
     * user_id : 6
     * rec_userid : 1
     * order_sn : 2018102223061727
     * order_time : 2018-10-22 22:58:09
     * desc : 等待商家发货
     * refund_status : 0
     * total_fee : 29.70
     * shipping_fee : 0.00
     * goods_info : [{"goods_name":"【宅女】夜用x2+加长夜用x1","goods_img":"images/201809/goods_img/842_G_1535952525592.jpg","goods_number":"1","goods_price":"29.70"}]
     * total_number : 1
     */

    private String order_id;
    private String user_id;
    private String rec_userid;
    private String order_sn;
    private String order_time;
    private String desc;
    private String refund_status;
    private String total_fee;
    private String shipping_fee;
    private String total_number;
    private List<GoodsInfoBean> goods_info;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class GoodsInfoBean extends BasePojo {
        /**
         * goods_name : 【宅女】夜用x2+加长夜用x1
         * goods_img : images/201809/goods_img/842_G_1535952525592.jpg
         * goods_number : 1
         * goods_price : 29.70
         */

        private String goods_name;
        private String goods_img;
        private String goods_number;
        private String goods_price;

    }
}
