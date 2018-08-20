package com.linsr.main.adapters.cart;

import java.util.List;

import lombok.Data;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午4:45
 */
@Data
public class TreePojo<K, V> {
    private K parentPojo;
    private List<V> childPojo;

    public TreePojo() {

    }

    public TreePojo(K parentPojo, List<V> childPojo) {
        this.parentPojo = parentPojo;
        this.childPojo = childPojo;
    }
}
