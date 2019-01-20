package com.linsr.main.model;

import com.linsr.common.model.BasePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午4:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CartShopPojo extends BasePojo{
    private String name;
    private boolean isChecked;

    public CartShopPojo(String name) {
        this.name = name;
    }
}
