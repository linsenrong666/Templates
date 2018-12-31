package com.linsr.main.model;

import com.google.gson.annotations.SerializedName;
import com.linsr.common.model.BasePojo;
import com.linsr.common.model.BizPojo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/12/31 下午8:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDetailsPojo extends BizPojo {

    private SuppliersInfoBean suppliers_info;
    private int spec_count;
    private RankPricesBean rank_prices;
    private GoodsBean goods;
    private List<?> properties;
    private List<?> specification;
    private List<PicturesBean> pictures;
    private List<?> related_goods;
    private List<?> goods_article_list;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SuppliersInfoBean extends BasePojo {
        /**
         * suppliers_id : 13
         * suppliers_name : 妖肌
         * suppliers_desc :
         * suppliers_address :
         * suppliers_phone :
         * suppliers_pic :
         * wap_pic :
         * suppliers_users :
         * is_check : 1
         * parent_id : 0
         * ord_id : 0
         * url : https://www.sisipay.com/api.php#suppliers?id=13
         */

        private String suppliers_id;
        private String suppliers_name;
        private String suppliers_desc;
        private String suppliers_address;
        private String suppliers_phone;
        private String suppliers_pic;
        private String wap_pic;
        private String suppliers_users;
        private String is_check;
        private String parent_id;
        private String ord_id;
        private String url;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class RankPricesBean extends BasePojo {
        /**
         * 1 : {"rank_name":"注册用户","price":"598.00"}
         * 2 : {"rank_name":"12","price":"598.00"}
         */

        @SerializedName("1")
        private _$1Bean _$1;
        @SerializedName("2")
        private _$2Bean _$2;

        public _$1Bean get_$1() {
            return _$1;
        }

        public void set_$1(_$1Bean _$1) {
            this._$1 = _$1;
        }

        public _$2Bean get_$2() {
            return _$2;
        }

        public void set_$2(_$2Bean _$2) {
            this._$2 = _$2;
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class _$1Bean extends BasePojo {
            /**
             * rank_name : 注册用户
             * price : 598.00
             */

            private String rank_name;
            private String price;

        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class _$2Bean extends BasePojo {
            /**
             * rank_name : 12
             * price : 598.00
             */

            private String rank_name;
            private String price;
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class GoodsBean extends BasePojo {
        /**
         * goods_id : 58
         * cat_id : 7
         * goods_sn : YS000058
         * goods_name : 【妖肌】玉颜淡化养肤套装（5合1）
         * short_name :
         * suppliers_id : 13
         * ck_id : 0
         * goods_short_name :
         * goods_name_style : +
         * click_count : 482
         * sales_count : 32
         * brand_id :
         * provider_name :
         * goods_number : 10018
         * goods_weight : 0克
         * market_price : 800.00
         * shop_price : 598.00
         * promote_price : 0.00
         * promote_start_date : 0
         * promote_end_date : 0
         * warn_number : 1
         * initial_value : 577
         * keywords :
         * goods_brief :
         * goods_desc : <p><img src="/upload/image/goods/20171207/1512611104667853.jpg" title="1512611104667853.jpg" alt="2a.jpg"/></p><p><img src="/upload/image/goods/20171207/1512611111956467.jpg" title="1512611111956467.jpg" alt="3a.jpg"/></p><p><img src="/upload/image/goods/20171207/1512611119623487.jpg" title="1512611119623487.jpg" alt="4a.jpg"/></p><p><img src="/upload/image/goods/20171207/1512611126236287.jpg" title="1512611126236287.jpg" alt="5a.jpg"/></p><p><img src="/upload/image/goods/20171207/1512611133100281.jpg" title="1512611133100281.jpg" alt="6a.jpg"/></p><p><img src="/upload/image/goods/20171207/1512611141274843.jpg" title="1512611141274843.jpg" alt="7a.jpg"/></p><p><img src="/upload/image/goods/20171207/1512611153584113.jpg" title="1512611153584113.jpg" alt="8a.jpg"/></p><p><img src="/upload/image/goods/20171207/1512611164616258.jpg" title="1512611164616258.jpg" alt="9a.jpg"/></p><p><img src="/upload/image/goods/20171207/1512611174117833.jpg" title="1512611174117833.jpg" alt="10a.jpg"/></p><p><br/></p>
         * goods_desc_baohan :
         * goods_desc_bubaohan :
         * goods_thumb : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/thumb_img/58_thumb_G_1491634059384.jpg
         * goods_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/58_G_1491634059947.jpg
         * original_img :
         * is_real : 1
         * extension_code :
         * is_on_sale : 1
         * is_alone_sale : 1
         * is_shipping : 1
         * integral : 0
         * add_time : 2017-04-08
         * sort_order : 100
         * is_delete : 0
         * is_best : 0
         * is_new : 1
         * is_hot : 0
         * is_promote : 0
         * bonus_type_id : 0
         * last_update : 1534244650
         * goods_type : 0
         * seller_note :
         * give_integral : 23.90
         * rank_integral : 0.00
         * goods_integral : 119.70
         * agency_id : 0
         * is_check : 0
         * bb_chicun :
         * mobile_desc :
         * sales_volume_base : 0
         * is_realsale : 0
         * share : 0.00
         * is_buymax : 0
         * is_everyday : 0
         * buymax : 0
         * buymax_start_date : 0
         * buymax_end_date : 0
         * is_linggo : 0
         * is_surprise_before : 0
         * surprise_before :
         * is_surprise_after : 0
         * surprise_after :
         * is_pass : 0
         * reason :
         * service_tel :
         * pin_time :
         * pin_number : 0
         * measure_unit :
         * goods_brand :
         * bonus_money : 0
         * comment_rank : 5
         * rank_price : 0
         * sales_volume_total : 0
         * market_price_formated : 800.00
         * shop_price_formated : 598.00
         * watermark_img : watermark_new
         * promote_price_org : 0
         * gmt_end_time : 0
         */

        private String goods_id;
        private String cat_id;
        private String goods_sn;
        private String goods_name;
        private String short_name;
        private String suppliers_id;
        private String ck_id;
        private String goods_short_name;
        private String goods_name_style;
        private String click_count;
        private String sales_count;
        private String brand_id;
        private String provider_name;
        private String goods_number;
        private String goods_weight;
        private String market_price;
        private String shop_price;
        private String promote_price;
        private String promote_start_date;
        private String promote_end_date;
        private String warn_number;
        private String initial_value;
        private String keywords;
        private String goods_brief;
        private String goods_desc;
        private String goods_desc_baohan;
        private String goods_desc_bubaohan;
        private String goods_thumb;
        private String goods_img;
        private String original_img;
        private String is_real;
        private String extension_code;
        private String is_on_sale;
        private String is_alone_sale;
        private String is_shipping;
        private int integral;
        private String add_time;
        private String sort_order;
        private String is_delete;
        private String is_best;
        private String is_new;
        private String is_hot;
        private String is_promote;
        private String bonus_type_id;
        private String last_update;
        private String goods_type;
        private String seller_note;
        private String give_integral;
        private String rank_integral;
        private String goods_integral;
        private String agency_id;
        private String is_check;
        private String bb_chicun;
        private String mobile_desc;
        private String sales_volume_base;
        private String is_realsale;
        private String share;
        private String is_buymax;
        private String is_everyday;
        private String buymax;
        private String buymax_start_date;
        private String buymax_end_date;
        private String is_linggo;
        private String is_surprise_before;
        private String surprise_before;
        private String is_surprise_after;
        private String surprise_after;
        private String is_pass;
        private String reason;
        private String service_tel;
        private String pin_time;
        private String pin_number;
        private String measure_unit;
        private String goods_brand;
        private int bonus_money;
        private int comment_rank;
        private String rank_price;
        private int sales_volume_total;
        private String market_price_formated;
        private String shop_price_formated;
        private String watermark_img;
        private int promote_price_org;
        private int gmt_end_time;

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class PicturesBean extends BasePojo {
        /**
         * thumb_url : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/thumb_img/58_thumb_P_1491634059850.jpg
         */

        private String thumb_url;
    }
}
