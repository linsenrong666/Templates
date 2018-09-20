package com.linsr.main.model;

import com.linsr.main.adapters.cart.TreeObject;

import lombok.Data;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午4:53
 */
@Data
public class CartShopPojo extends TreeObject {
    private String name;
    private boolean isChecked;

    public CartShopPojo(String name) {
        this.name = name;
    }
}
