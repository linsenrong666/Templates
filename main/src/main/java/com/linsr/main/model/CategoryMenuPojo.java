package com.linsr.main.model;

import com.linsr.common.model.BasePojo;
import com.linsr.common.model.BizPojo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryMenuPojo extends BizPojo {

    private List<CatListsBean> cat_lists;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class CatListsBean extends BasePojo {
        /**
         * cat_id : 16
         * cat_name : 姨妈街
         * cat_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/1494407624725229901.png
         * parent_id : 0
         * kids : [{"cat_id":"7","cat_name":"美容专区","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/1522055603861940810.png","parent_id":"16"},{"cat_id":"60","cat_name":"洗涤专区","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/1517904286576906757.png","parent_id":"16"},{"cat_id":"71","cat_name":"健康护理专区","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/1526892804418873864.png","parent_id":"16"},{"cat_id":"58","cat_name":"品牌内衣专区","cat_img":"http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/1516763826711846305.png","parent_id":"16"}]
         */

        private String cat_id;
        private String cat_name;
        private String cat_img;
        private String parent_id;
        private List<KidsBean> kids;


        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class KidsBean extends BasePojo {
            /**
             * cat_id : 7
             * cat_name : 美容专区
             * cat_img : http://yimaw.oss-cn-hangzhou.aliyuncs.com/cat_img/1522055603861940810.png
             * parent_id : 16
             */

            private String cat_id;
            private String cat_name;
            private String cat_img;
            private String parent_id;
        }
    }
}
