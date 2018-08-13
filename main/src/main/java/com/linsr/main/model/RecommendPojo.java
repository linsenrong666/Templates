package com.linsr.main.model;

import com.linsr.common.model.BasePojo;

/**
 * Description
 *
 * @author Linsr 2018/8/13 下午5:25
 */
public class RecommendPojo extends BasePojo {

    private String name;
    private String price;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
