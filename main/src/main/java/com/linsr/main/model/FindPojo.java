package com.linsr.main.model;

import java.util.List;

import lombok.Data;

/**
 * Description
 *
 * @author Linsr 2018/7/12 下午4:49
 */
@Data
public class FindPojo {

    /**
     * floorStyle : ball
     * styleTpl : tpl3
     * edge : false
     * floorTitle : {}
     * data : [{"floorCellData":{"to":"channelPage","params":{"channelId":"3983"},"index":1,"words":"","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t14425/119/2167777056/7787/1dcd226/5a71b22bN2040ad02.png","title":"超市便利","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"超市便利\",\"unit_index\":1,\"styleTpl\":\"tpl3\",\"channelId\":\"3983\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"res_unit\":1}","titleColor":"#000000"},"floorCellType":"common"},{"floorCellData":{"to":"channelPage","params":{"channelId":"3997"},"index":2,"words":"","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t14668/164/2179740910/10114/cbb106bc/5a71b242N2df40f47.png","title":"新鲜果蔬","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"新鲜果蔬\",\"unit_index\":1,\"styleTpl\":\"tpl3\",\"channelId\":\"3997\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"res_unit\":2}","titleColor":"#000000"},"floorCellType":"common"},{"floorCellData":{"to":"channelPage","params":{"channelId":"4017"},"index":3,"words":"","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t21601/231/1708067969/7963/252d8694/5b30e566N992dd305.png","title":"烘焙蛋糕","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"烘焙蛋糕\",\"unit_index\":1,\"styleTpl\":\"tpl3\",\"channelId\":\"4017\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"res_unit\":3}","titleColor":"#000000"},"floorCellType":"common"},{"floorCellData":{"to":"channelPage","params":{"channelId":"4016"},"index":4,"words":"七夕节","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t17581/271/396026257/9818/c3b9db13/5a71b284N73d24bef.png","title":"鲜花绿植","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"鲜花绿植\",\"unit_index\":1,\"styleTpl\":\"tpl3\",\"channelId\":\"4016\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"res_unit\":4}","titleColor":"#000000"},"floorCellType":"common"},{"floorCellData":{"to":"channelPage","params":{"channelId":"7"},"index":5,"words":"","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t16939/215/389486843/9388/f78978fa/5a71b299Nc3164770.png","title":"医药健康","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"医药健康\",\"unit_index\":1,\"styleTpl\":\"tpl3\",\"channelId\":\"7\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"res_unit\":5}","titleColor":"#000000"},"floorCellType":"common"},{"floorCellData":{"to":"web","params":{"shareId":1534301603746,"url":"https://daojia.jd.com/activity/specialActivity/newPeopleActivity.html?channel_id=app&membertype=0&channel=xrzq_indexball"},"index":6,"words":"","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t16582/229/1984380615/7712/f8a88a81/5a71b435N5525cc0b.png","title":"进店有礼","exposureType":"venderNewPersonWeb","fixIndex":2,"userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"进店有礼\",\"unit_index\":1,\"shareId\":1534301603746,\"styleTpl\":\"tpl3\",\"exposureType\":\"venderNewPersonWeb\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"url\":\"https://daojia.jd.com/activity/specialActivity/newPeopleActivity.html?channel_id=app&membertype=0&channel=xrzq_indexball\",\"res_unit\":6}","titleColor":"#000000"},"floorCellType":"common"},{"floorCellData":{"to":"web","params":{"shareId":1534301603746,"url":"https://daojia.jd.com/client?functionId=login/appThroughH5&body=%7B%22returnLink%22%3A%22https%3A%2F%2Fdaojia.jd.com%2Fhtml%2Findex.html%3Fh5hash%3Dvip%26channel%3Dvip_indexball%22%7D&needPin=yes"},"index":7,"words":"","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t19531/329/400521908/6753/545469bc/5a71b416N108a30ed.png","title":"VIP会员","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"VIP会员\",\"unit_index\":1,\"shareId\":1534301603746,\"styleTpl\":\"tpl3\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"url\":\"https://daojia.jd.com/client?functionId=login/appThroughH5&body=%7B%22returnLink%22%3A%22https%3A%2F%2Fdaojia.jd.com%2Fhtml%2Findex.html%3Fh5hash%3Dvip%26channel%3Dvip_indexball%22%7D&needPin=yes\",\"res_unit\":7}","titleColor":"#000000"},"floorCellType":"common"},{"floorCellData":{"to":"web","params":{"shareId":1534301603746,"url":"https://daojia.jd.com/client?functionId=login/appThroughDada&platCode=H5&appName=paidaojia&body=%7b%22returnLink%22%3a%22https%3a%2f%2fkuai.imdada.cn%2ftoc%2fcorp%2findex_v2%23%2fbwm%2fhome%3fchannel%3dJDDJ_sixball%26%22%7d&needPin=yes&bangwomaiball=1"},"index":8,"words":"","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t16876/204/443650814/8187/f41d95c7/5a7aa948Nfbaa98a3.png","title":"跑腿代买","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"跑腿代买\",\"unit_index\":1,\"shareId\":1534301603746,\"styleTpl\":\"tpl3\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"url\":\"https://daojia.jd.com/client?functionId=login/appThroughDada&platCode=H5&appName=paidaojia&body=%7b%22returnLink%22%3a%22https%3a%2f%2fkuai.imdada.cn%2ftoc%2fcorp%2findex_v2%23%2fbwm%2fhome%3fchannel%3dJDDJ_sixball%26%22%7d&needPin=yes&bangwomaiball=1\",\"res_unit\":8}","titleColor":"#000000"},"floorCellType":"common"},{"floorCellData":{"to":"web","params":{"shareId":1534301603746,"url":"https://daojia.jd.com/client?functionId=login/appThroughH5&body=%7B%22returnLink%22%3A%22https%3A%2F%2Fdaojia.jd.com%2Factivity%2Finvite%2Findex.html%3Fchannel%3Dfxyl_indexball%22%7D&needPin=yes"},"index":9,"words":"","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t19426/109/405451230/7637/160ed0a4/5a71b4cdN40e7a111.png","title":"分享有礼","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"分享有礼\",\"unit_index\":1,\"shareId\":1534301603746,\"styleTpl\":\"tpl3\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"url\":\"https://daojia.jd.com/client?functionId=login/appThroughH5&body=%7B%22returnLink%22%3A%22https%3A%2F%2Fdaojia.jd.com%2Factivity%2Finvite%2Findex.html%3Fchannel%3Dfxyl_indexball%22%7D&needPin=yes\",\"res_unit\":9}","titleColor":"#000000"},"floorCellType":"common"},{"floorCellData":{"to":"web","params":{"shareId":1534301603746,"url":"https://daojia.jd.com/client?functionId=login/appThroughH5&body=%7B%22returnLink%22%3A%22https%3A%2F%2Fdaojia.jd.com%2Factivity%2Fmarket%2FsignIn%2Findex.html%3Fchannel%3Dqiandao_indexball%22%7D&needPin=yes"},"index":10,"words":"99减50","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t12724/31/1425195131/7008/2a2076cf/5a71b4ecNd4334eb1.png","title":"签到","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"签到\",\"unit_index\":1,\"shareId\":1534301603746,\"styleTpl\":\"tpl3\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"url\":\"https://daojia.jd.com/client?functionId=login/appThroughH5&body=%7B%22returnLink%22%3A%22https%3A%2F%2Fdaojia.jd.com%2Factivity%2Fmarket%2FsignIn%2Findex.html%3Fchannel%3Dqiandao_indexball%22%7D&needPin=yes\",\"res_unit\":10}","titleColor":"#000000"},"floorCellType":"common"}]
     * index : 30
     * timer : 1534299070000
     * showFeed : true
     * channelPage : false
     * floorType : 2
     * floorBusiness : -1
     * borderStyle : 1
     * returnFrameNum : 8
     * moreBtnDesc : 更多
     * syncTime : 300000
     */

    private String floorStyle;
    private String styleTpl;
    private boolean edge;
    private FloorTitleBean floorTitle;
    private int index;
    private long timer;
    private boolean showFeed;
    private boolean channelPage;
    private int floorType;
    private int floorBusiness;
    private int borderStyle;
    private int returnFrameNum;
    private String moreBtnDesc;
    private int syncTime;
    private List<DataBean> data;

    @Data
    public static class FloorTitleBean {
    }

    @Data
    public static class DataBean {
        /**
         * floorCellData : {"to":"channelPage","params":{"channelId":"3983"},"index":1,"words":"","imgUrl":"https://img30.360buyimg.com/mobilecms/jfs/t14425/119/2167777056/7787/1dcd226/5a71b22bN2040ad02.png","title":"超市便利","userAction":"{\"pageType\":\"indexPage\",\"res_name\":\"超市便利\",\"unit_index\":1,\"styleTpl\":\"tpl3\",\"channelId\":\"3983\",\"platCode\":\"IOS\",\"res_type\":\"ball_10\",\"res_unit\":1}","titleColor":"#000000"}
         * floorCellType : common
         */

        private FloorCellDataBean floorCellData;
        private String floorCellType;

        @Data
        public static class FloorCellDataBean {
            /**
             * to : channelPage
             * params : {"channelId":"3983"}
             * index : 1
             * words :
             * imgUrl : https://img30.360buyimg.com/mobilecms/jfs/t14425/119/2167777056/7787/1dcd226/5a71b22bN2040ad02.png
             * title : 超市便利
             * userAction : {"pageType":"indexPage","res_name":"超市便利","unit_index":1,"styleTpl":"tpl3","channelId":"3983","platCode":"IOS","res_type":"ball_10","res_unit":1}
             * titleColor : #000000
             */

            private String to;
            private ParamsBean params;
            private int index;
            private String words;
            private String imgUrl;
            private String title;
            private String userAction;
            private String titleColor;

            @Data
            public static class ParamsBean {
                /**
                 * channelId : 3983
                 */

                private String channelId;

            }
        }
    }
}
