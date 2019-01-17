package com.linsr.main.model;

import com.google.gson.annotations.SerializedName;
import com.linsr.common.model.BasePojo;
import com.linsr.common.model.BizPojo;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/7/13 上午11:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HomePojo extends BizPojo {


    private List<HomeListBean> home_list;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class HomeListBean extends BasePojo {

        /**
         * floorType : 1
         * floorName : banner
         * bannerData : [{"ad_control":"goods","ad_link":"23","ad_image":"https://www.sisipay.com/m/themes/images/w_banner1.png"},{"ad_control":"goods","ad_link":"345","ad_image":"https://www.sisipay.com/m/themes/images/index_ad_1_01.jpg"},{"ad_control":"goods","ad_link":"98","ad_image":"https://www.sisipay.com/m/themes/images/w_banner3.png"}]
         * adOneData : {"ad_control":"goods","ad_link":"86","ad_image":"https://www.sisipay.com/m/themes/images/index_ad_2_01.jpg"}
         * adTwoData : [{"ad_control":"goods","ad_link":"47","ad_image":"https://www.sisipay.com/m/themes/images/index_ad_3_03.jpg"},{"ad_control":"goods","ad_link":"85","ad_image":"https://www.sisipay.com/m/themes/images/index_ad_2_02.jpg"},{"ad_control":"goods","ad_link":"34","ad_image":"https://www.sisipay.com/m/themes/images/index_ad_3_01.jpg"}]
         * adThreeData : {"ad_control":"goods","ad_link":"123","ad_image":"https://www.sisipay.com/m/themes/images/index_ad_3_02.jpg"}
         * storeInfoData :
         * yimaStreeData : [{"cat":{"id":"91","cat_desc":"","cat_name":"宅女卫生巾","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_03.png"},"gs":[{"goods_id":"842","short_name":"宅女夜用卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/842_G_1535952525592.jpg","sales_count":"3146"},{"goods_id":"841","short_name":"宅女夜用3包","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/841_G_1535952070098.jpg","sales_count":"3219"},{"goods_id":"839","short_name":"宅女夜用卫生巾","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/839_G_1535951483505.jpg","sales_count":"2563"},{"goods_id":"838","short_name":"宅女夜用6包","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/838_G_1535950529475.jpg","sales_count":"2456"},{"goods_id":"837","short_name":"宅女卫生巾","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/837_G_1535604634224.jpg","sales_count":"2565"},{"goods_id":"826","short_name":"宅女卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/826_G_1535184783547.jpg","sales_count":"2569"},{"goods_id":"825","short_name":"宅女卫生巾","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/825_G_1535184004746.jpg","sales_count":"2578"},{"goods_id":"824","short_name":"宅女卫生巾","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/824_G_1535184054092.jpg","sales_count":"2291"}]},{"cat":{"id":"92","cat_desc":"","cat_name":"娜菲卫生巾","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_02.png"},"gs":[{"goods_id":"827","short_name":"娜菲卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/827_G_1535185790412.jpg","sales_count":"2163"},{"goods_id":"134","short_name":"娜菲卫生护垫3包","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/134_G_1535185815575.jpg","sales_count":"2633"},{"goods_id":"125","short_name":"娜菲日用卫生巾3包","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/125_G_1535185838789.jpg","sales_count":"2752"},{"goods_id":"124","short_name":"娜菲卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/124_G_1535185865045.jpg","sales_count":"3922"},{"goods_id":"123","short_name":"娜菲卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/123_G_1535185892374.jpg","sales_count":"3831"},{"goods_id":"122","short_name":"娜菲卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/122_G_1535185937503.jpg","sales_count":"3373"},{"goods_id":"56","short_name":"娜菲卫生巾6包","shop_price":"52.8","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/56_G_1500453747780.jpg","sales_count":"2574"},{"goods_id":"53","short_name":"娜菲卫生巾礼盒","shop_price":"98.8","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/53_G_1500453266624.jpg","sales_count":"2630"}]},{"cat":{"id":"93","cat_desc":"","cat_name":"七度空间","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_04.png"},"gs":[{"goods_id":"7","short_name":"少女系列4包","shop_price":"39.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/7_G_1491535803015.jpg","sales_count":"2"},{"goods_id":"6","short_name":"少女系列13盒","shop_price":"99.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/6_G_1491535527006.jpg","sales_count":"0"},{"goods_id":"4","short_name":"少女系列14包","shop_price":"72.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/4_G_1491535231687.jpg","sales_count":"23"},{"goods_id":"3","short_name":"多维秘护36片装","shop_price":"75.1","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201704/goods_img/3_G_1491535047371.jpg","sales_count":"0"}]},{"cat":{"id":"94","cat_desc":"","cat_name":"苏菲卫生巾","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_05.png"},"gs":[{"goods_id":"107","short_name":"苏菲 6包组合","shop_price":"50","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201705/goods_img/107_G_1493968968657.jpg","sales_count":"1"},{"goods_id":"68","short_name":"苏菲 6包组合","shop_price":"45.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/68_G_1500539045417.jpg","sales_count":"3"},{"goods_id":"67","short_name":"苏菲 6包组合","shop_price":"53.99","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/67_G_1500539069964.jpg","sales_count":"3"},{"goods_id":"66","short_name":"苏菲 6包组合","shop_price":"52","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/66_G_1500539146993.jpg","sales_count":"3"},{"goods_id":"65","short_name":"苏菲 6包组合","shop_price":"59.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/65_G_1500539174689.jpg","sales_count":"1"},{"goods_id":"64","short_name":"苏菲 7包组合","shop_price":"58.8","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/64_G_1500539189901.jpg","sales_count":"3"}]},{"cat":{"id":"95","cat_desc":"","cat_name":"ABC系列卫生巾","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_06.png"},"gs":[{"goods_id":"108","short_name":"ABC 6包组合","shop_price":"47.3","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201705/goods_img/108_G_1493969386834.jpg","sales_count":"6"},{"goods_id":"76","short_name":"ABC 6包组合","shop_price":"48.8","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/76_G_1500540390392.jpg","sales_count":"5"},{"goods_id":"74","short_name":"ABC 10包组合","shop_price":"49","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/74_G_1500540440692.jpg","sales_count":"1"},{"goods_id":"73","short_name":"ABC 6包组合","shop_price":"56","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/73_G_1500540472627.jpg","sales_count":"2"},{"goods_id":"72","short_name":"ABC 7包组合","shop_price":"57.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/72_G_1500540576951.jpg","sales_count":"0"}]},{"cat":{"id":"96","cat_desc":"","cat_name":"娜菲纸品","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_07.png"},"gs":[{"goods_id":"420","short_name":"本色抽纸9包","shop_price":"39.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201801/goods_img/420_G_1516591454679.jpg","sales_count":"504"},{"goods_id":"51","short_name":"娜菲抽纸9包","shop_price":"39.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201707/goods_img/51_G_1500452234657.jpg","sales_count":"474"}]},{"cat":{"id":"98","cat_desc":"","cat_name":"生活日用","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_08.png"},"gs":[{"goods_id":"695","short_name":"HL&ONE-护发素","shop_price":"59.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201806/goods_img/695_G_1529054533154.jpg","sales_count":"83"},{"goods_id":"694","short_name":"海洋生物沐浴露","shop_price":"59.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201806/goods_img/694_G_1529054499936.jpg","sales_count":"77"},{"goods_id":"693","short_name":"无硅油氨基酸洗发液","shop_price":"59.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201806/goods_img/693_G_1529054471090.jpg","sales_count":"85"},{"goods_id":"691","short_name":"丝亮高效洗衣液","shop_price":"29.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201806/goods_img/691_G_1529050171026.jpg","sales_count":"73"},{"goods_id":"690","short_name":"厨房强力去污剂","shop_price":"29.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201806/goods_img/690_G_1529050058458.jpg","sales_count":"59"},{"goods_id":"462","short_name":"","shop_price":"39.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201802/goods_img/462_G_1517904965243.jpg","sales_count":"345"},{"goods_id":"459","short_name":"","shop_price":"39.9","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201802/goods_img/459_G_1517904512197.jpg","sales_count":"323"}]}]
         * recData : [{"goods_id":"842","short_name":"宅女夜用卫生巾","shop_price":"29.70","promote_price":"0.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/842_G_1535952525592.jpg"},{"goods_id":"841","short_name":"宅女夜用3包","shop_price":"29.70","promote_price":"0.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/841_G_1535952070098.jpg"},{"goods_id":"839","short_name":"宅女夜用卫生巾","shop_price":"39.60","promote_price":"0.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/839_G_1535951483505.jpg"},{"goods_id":"838","short_name":"宅女夜用6包","shop_price":"39.60","promote_price":"0.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/838_G_1535950529475.jpg"},{"goods_id":"837","short_name":"宅女卫生巾","shop_price":"39.60","promote_price":"0.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/837_G_1535604634224.jpg"},{"goods_id":"833","short_name":"","shop_price":"29.70","promote_price":"0.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/833_G_1535188809048.jpg"},{"goods_id":"832","short_name":"","shop_price":"29.70","promote_price":"0.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/832_G_1535188830886.jpg"},{"goods_id":"831","short_name":"","shop_price":"29.70","promote_price":"0.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/831_G_1535188850918.jpg"},{"goods_id":"830","short_name":"","shop_price":"29.70","promote_price":"0.00","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/830_G_1535188871722.jpg"}]
         */

        private int floorType;
        private String floorName;
        private AdDataBean adOneData;
        private AdDataBean adThreeData;
        private StoreInfoDataBean storeInfoData;
        private List<BannerDataBean> bannerData;
        private List<AdDataBean> adTwoData;
        private YimaStreeDataBean yimaStreeData;
        private List<IsbestBean> recData;


        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class AdDataBean extends BasePojo {
            /**
             * ad_control : goods
             * ad_link : 86
             * ad_image : https://www.sisipay.com/m/themes/images/index_ad_2_01.jpg
             */

            private String ad_control;
            private String ad_link;
            private String ad_image;

        }
//
//        @EqualsAndHashCode(callSuper = true)
//        @Data
//        public static class AdThreeDataBean extends BasePojo {
//            /**
//             * ad_control : goods
//             * ad_link : 123
//             * ad_image : https://www.sisipay.com/m/themes/images/index_ad_3_02.jpg
//             */
//
//            private String ad_control;
//            private String ad_link;
//            private String ad_image;
//
//        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class BannerDataBean extends BasePojo {
            /**
             * ad_control : goods
             * ad_link : 23
             * ad_image : https://www.sisipay.com/m/themes/images/w_banner1.png
             */

            private String ad_control;
            private String ad_link;
            private String ad_image;

        }
//
//        @EqualsAndHashCode(callSuper = true)
//        @Data
//        public static class AdTwoDataBean extends BasePojo {
//            /**
//             * ad_control : goods
//             * ad_link : 47
//             * ad_image : https://www.sisipay.com/m/themes/images/index_ad_3_03.jpg
//             */
//
//            private String ad_control;
//            private String ad_link;
//            private String ad_image;
//
//        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class YimaStreeDataBean extends BasePojo {
            /**
             * cat : {"id":"91","cat_desc":"","cat_name":"宅女卫生巾","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_03.png"}
             * gs : [{"goods_id":"842","short_name":"宅女夜用卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/842_G_1535952525592.jpg","sales_count":"3146"},{"goods_id":"841","short_name":"宅女夜用3包","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/841_G_1535952070098.jpg","sales_count":"3219"},{"goods_id":"839","short_name":"宅女夜用卫生巾","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/839_G_1535951483505.jpg","sales_count":"2563"},{"goods_id":"838","short_name":"宅女夜用6包","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/838_G_1535950529475.jpg","sales_count":"2456"},{"goods_id":"837","short_name":"宅女卫生巾","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/837_G_1535604634224.jpg","sales_count":"2565"},{"goods_id":"826","short_name":"宅女卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/826_G_1535184783547.jpg","sales_count":"2569"},{"goods_id":"825","short_name":"宅女卫生巾","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/825_G_1535184004746.jpg","sales_count":"2578"},{"goods_id":"824","short_name":"宅女卫生巾","shop_price":"39.6","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201808/goods_img/824_G_1535184054092.jpg","sales_count":"2291"}]
             */

            private CatBean cat;
            private List<GsBean> gs;

            @EqualsAndHashCode(callSuper = true)
            @Data
            public static class CatBean extends BasePojo {
                /**
                 * id : 91
                 * cat_desc :
                 * cat_name : 宅女卫生巾
                 * cat_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_03.png
                 */

                private String id;
                private String cat_desc;
                private String cat_name;
                private String cat_img;

            }

            @EqualsAndHashCode(callSuper = true)
            @Data
            public static class GsBean extends BasePojo {
                /**
                 * goods_id : 842
                 * short_name : 宅女夜用卫生巾
                 * shop_price : 29.7
                 * promote_price :
                 * goods_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/842_G_1535952525592.jpg
                 * sales_count : 3146
                 */

                private String goods_id;
                private String short_name;
                private String shop_price;
                private String promote_price;
                private String goods_img;
                private String sales_count;

            }
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class RecDataBean extends BasePojo {
            /**
             * goods_id : 842
             * short_name : 宅女夜用卫生巾
             * shop_price : 29.70
             * promote_price : 0.00
             * goods_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/842_G_1535952525592.jpg
             */

            private String goods_id;
            private String short_name;
            private String shop_price;
            private String promote_price;
            private String goods_img;

        }


        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class StoreInfoDataBean extends BasePojo {
            /**
             * id : 1
             * user_id : 6
             * storename : TONY的网店
             * storeface : http://thirdwx.qlogo.cn/mmopen/iaEFXL4ns1RhjhTduVdCvArbSdnn9pL0KicsluTia9ASTnvETlFIVBa854Dylqokxy94PlNRviaT0lTFBOmYBedfzCEqpYmjHMtb/132
             * storedesc :
             * bgimg : 0
             * visit : 13149
             * order_count : 0
             * is_show : 1
             * view : 847140
             * users_total : 6234
             */

            private String id;
            private String user_id;
            private String storename;
            private String storeface;
            private String storedesc;
            private String bgimg;
            private String visit;
            private String order_count;
            private String is_show;
            private String view;
            private String users_total;
        }
    }
}
