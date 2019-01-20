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
 * @author Linsr 2019/1/12 下午10:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CartListPojo extends BizPojo {

    private List<IsbestBean> recommended;
    private String shopping_money;
    private TotalBean total;
    private List<GoodsListBean> goods_list;
    private List<?> fittings_list;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class TotalBean extends BasePojo {
        /**
         * goods_price : 12741.70
         * market_price : 17073.19
         * saving : 4331.49
         * save_rate : 25%
         * goods_amount : 12741.7
         * total_number : 24
         * real_goods_count : 4
         * virtual_goods_count : 0
         */

        private String goods_price;
        private String market_price;
        private String saving;
        private String save_rate;
        private double goods_amount;
        private int total_number;
        private int real_goods_count;
        private int virtual_goods_count;

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class GoodsListBean extends BasePojo {
        /**
         * suppliers_name : 供货商：妖肌
         * list : [{"rec_id":"25","user_id":"6","session_id":"SESS_ID","goods_id":"58","goods_sn":"YS000058","product_id":"0","goods_name":"【妖肌】玉颜淡化养肤套装（5合1）","market_price":"800.00","goods_price":"598.00","goods_number":"21","goods_attr":"","give_integral":"23.90","goods_integral":"119.70","rank_integral":"0.00","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"1","can_handsel":"0","goods_attr_id":"","suppliers_id":"13","ck_id":"0","pid":"58","is_buymax":"0","buymax":"0","is_everyday":"0","buymax_start_date":"0","buymax_end_date":"0","subtotal":"12558.00","shop_price":"598.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/58_G_1491634059947.jpg"},{"rec_id":"32","user_id":"6","session_id":"SESS_ID","goods_id":"60","goods_sn":"YS000060","product_id":"0","goods_name":"【妖肌】舒缓修护活泉喷雾120ml正品","market_price":"98.00","goods_price":"58.00","goods_number":"1","goods_attr":"","give_integral":"0.00","goods_integral":"10.00","rank_integral":"0.00","is_real":"1","extension_code":"","parent_id":"0","rec_type":"0","is_gift":"0","is_shipping":"1","can_handsel":"0","goods_attr_id":"","suppliers_id":"13","ck_id":"0","pid":"60","is_buymax":"0","buymax":"0","is_everyday":"0","buymax_start_date":"0","buymax_end_date":"0","subtotal":"58.00","shop_price":"58.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201807/goods_img/60_G_1530591038750.jpg"}]
         */

        private String suppliers_name;
        private List<ListBean> list;

        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class ListBean extends BasePojo {
            /**
             * rec_id : 25
             * user_id : 6
             * session_id : SESS_ID
             * goods_id : 58
             * goods_sn : YS000058
             * product_id : 0
             * goods_name : 【妖肌】玉颜淡化养肤套装（5合1）
             * market_price : 800.00
             * goods_price : 598.00
             * goods_number : 21
             * goods_attr :
             * give_integral : 23.90
             * goods_integral : 119.70
             * rank_integral : 0.00
             * is_real : 1
             * extension_code :
             * parent_id : 0
             * rec_type : 0
             * is_gift : 0
             * is_shipping : 1
             * can_handsel : 0
             * goods_attr_id :
             * suppliers_id : 13
             * ck_id : 0
             * pid : 58
             * is_buymax : 0
             * buymax : 0
             * is_everyday : 0
             * buymax_start_date : 0
             * buymax_end_date : 0
             * subtotal : 12558.00
             * shop_price : 598.00
             * goods_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/58_G_1491634059947.jpg
             */

            private String rec_id;
            private String user_id;
            private String session_id;
            private String goods_id;
            private String goods_sn;
            private String product_id;
            private String goods_name;
            private String market_price;
            private String goods_price;
            private String goods_number;
            private String goods_attr;
            private String give_integral;
            private String goods_integral;
            private String rank_integral;
            private String is_real;
            private String extension_code;
            private String parent_id;
            private String rec_type;
            private String is_gift;
            private String is_shipping;
            private String can_handsel;
            private String goods_attr_id;
            private String suppliers_id;
            private String ck_id;
            private String pid;
            private String is_buymax;
            private String buymax;
            private String is_everyday;
            private String buymax_start_date;
            private String buymax_end_date;
            private String subtotal;
            private String shop_price;
            private String goods_img;

            private boolean isChecked;
            private int count;
        }
    }
}
