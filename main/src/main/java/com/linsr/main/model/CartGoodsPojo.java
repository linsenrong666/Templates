package com.linsr.main.model;

import lombok.Data;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午4:53
 */
@Data
public class CartGoodsPojo {
    private String name;

    public CartGoodsPojo(String name) {
        this.name = name;
    }
}
