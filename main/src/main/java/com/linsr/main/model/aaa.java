package com.linsr.main.model;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/12/31 下午4:52
 */
public class aaa {

    private List<HomeListBean> home_list;

    public static class HomeListBean {
        /**
         * floorType : 1
         * floorName : banner
         * bannerData : [{"ad_control":"goods","ad_link":"23","ad_image":"https://www.sisipay.com/m/themes/images/w_banner1.png"}]
         * recData : [{"ad_control":"goods","ad_link":"23","ad_image":"https://www.sisipay.com/m/themes/images/w_banner1.png"}]
         * yimaStreetData : [{"cat":{"id":"91","cat_desc":"","cat_name":"宅女卫生巾","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_03.png"},"gs":[{"goods_id":"842","short_name":"宅女夜用卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/842_G_1535952525592.jpg","sales_count":"3144"},{"goods_id":"841","short_name":"宅女夜用3包","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/841_G_1535952070098.jpg","sales_count":"3219"}]}]
         */

        private int floorType;
        private String floorName;
        private List<BannerDataBean> bannerData;
        private List<RecDataBean> recData;
        private List<YimaStreetDataBean> yimaStreetData;

        public int getFloorType() {
            return floorType;
        }

        public void setFloorType(int floorType) {
            this.floorType = floorType;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public List<BannerDataBean> getBannerData() {
            return bannerData;
        }

        public void setBannerData(List<BannerDataBean> bannerData) {
            this.bannerData = bannerData;
        }

        public List<RecDataBean> getRecData() {
            return recData;
        }

        public void setRecData(List<RecDataBean> recData) {
            this.recData = recData;
        }

        public List<YimaStreetDataBean> getYimaStreetData() {
            return yimaStreetData;
        }

        public void setYimaStreetData(List<YimaStreetDataBean> yimaStreetData) {
            this.yimaStreetData = yimaStreetData;
        }

        public static class BannerDataBean {
            /**
             * ad_control : goods
             * ad_link : 23
             * ad_image : https://www.sisipay.com/m/themes/images/w_banner1.png
             */

            private String ad_control;
            private String ad_link;
            private String ad_image;

            public String getAd_control() {
                return ad_control;
            }

            public void setAd_control(String ad_control) {
                this.ad_control = ad_control;
            }

            public String getAd_link() {
                return ad_link;
            }

            public void setAd_link(String ad_link) {
                this.ad_link = ad_link;
            }

            public String getAd_image() {
                return ad_image;
            }

            public void setAd_image(String ad_image) {
                this.ad_image = ad_image;
            }
        }

        public static class RecDataBean {
            /**
             * ad_control : goods
             * ad_link : 23
             * ad_image : https://www.sisipay.com/m/themes/images/w_banner1.png
             */

            private String ad_control;
            private String ad_link;
            private String ad_image;

            public String getAd_control() {
                return ad_control;
            }

            public void setAd_control(String ad_control) {
                this.ad_control = ad_control;
            }

            public String getAd_link() {
                return ad_link;
            }

            public void setAd_link(String ad_link) {
                this.ad_link = ad_link;
            }

            public String getAd_image() {
                return ad_image;
            }

            public void setAd_image(String ad_image) {
                this.ad_image = ad_image;
            }
        }

        public static class YimaStreetDataBean {
            /**
             * cat : {"id":"91","cat_desc":"","cat_name":"宅女卫生巾","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/brand_03.png"}
             * gs : [{"goods_id":"842","short_name":"宅女夜用卫生巾","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/842_G_1535952525592.jpg","sales_count":"3144"},{"goods_id":"841","short_name":"宅女夜用3包","shop_price":"29.7","promote_price":"","goods_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/841_G_1535952070098.jpg","sales_count":"3219"}]
             */

            private CatBean cat;
            private List<GsBean> gs;

            public CatBean getCat() {
                return cat;
            }

            public void setCat(CatBean cat) {
                this.cat = cat;
            }

            public List<GsBean> getGs() {
                return gs;
            }

            public void setGs(List<GsBean> gs) {
                this.gs = gs;
            }

            public static class CatBean {
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

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCat_desc() {
                    return cat_desc;
                }

                public void setCat_desc(String cat_desc) {
                    this.cat_desc = cat_desc;
                }

                public String getCat_name() {
                    return cat_name;
                }

                public void setCat_name(String cat_name) {
                    this.cat_name = cat_name;
                }

                public String getCat_img() {
                    return cat_img;
                }

                public void setCat_img(String cat_img) {
                    this.cat_img = cat_img;
                }
            }

            public static class GsBean {
                /**
                 * goods_id : 842
                 * short_name : 宅女夜用卫生巾
                 * shop_price : 29.7
                 * promote_price :
                 * goods_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/images/201809/goods_img/842_G_1535952525592.jpg
                 * sales_count : 3144
                 */

                private String goods_id;
                private String short_name;
                private String shop_price;
                private String promote_price;
                private String goods_img;
                private String sales_count;

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getShort_name() {
                    return short_name;
                }

                public void setShort_name(String short_name) {
                    this.short_name = short_name;
                }

                public String getShop_price() {
                    return shop_price;
                }

                public void setShop_price(String shop_price) {
                    this.shop_price = shop_price;
                }

                public String getPromote_price() {
                    return promote_price;
                }

                public void setPromote_price(String promote_price) {
                    this.promote_price = promote_price;
                }

                public String getGoods_img() {
                    return goods_img;
                }

                public void setGoods_img(String goods_img) {
                    this.goods_img = goods_img;
                }

                public String getSales_count() {
                    return sales_count;
                }

                public void setSales_count(String sales_count) {
                    this.sales_count = sales_count;
                }
            }
        }
    }
}
