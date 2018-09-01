package com.linsr.main.model;

import com.linsr.common.model.BasePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/9/1 下午12:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentPojo extends BasePojo{
    private String name;
}
