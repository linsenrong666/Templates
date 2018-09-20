package com.linsr.main.model;

import com.linsr.main.adapters.cart.TreeObject;

import lombok.Data;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午4:53
 */
@Data
public class CartGoodsPojo extends TreeObject {
    private String name;
    private boolean isChecked;
    private int count;

    public CartGoodsPojo(String name) {
        this.name = name;
    }
}
