package com.linsr.main.model;

import com.linsr.common.model.BasePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryMenuPojo extends BasePojo{

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
