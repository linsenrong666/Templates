package com.linsr.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/8/7 下午4:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResponsePojo extends BasePojo {

    private String output;

}
