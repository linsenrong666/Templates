package com.linsr.main.model;

import com.linsr.common.model.BasePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/7/13 上午11:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HomePojo extends BasePojo{

    private String url;
    private String title;
    private String desc;
    private int floorType;

}
