package com.linsr.common.model;

import lombok.Data;

/**
 * Description
 *
 * @author Linsr 2018/11/30 下午4:21
 */
@Data
public class BizPojo extends BasePojo {

    /**
     * error : 1
     * message : 用户不存在
     */
    private int error;
    private String message;

}
