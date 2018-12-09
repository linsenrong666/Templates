package com.linsr.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/11/30 下午4:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizPojo extends BasePojo {


    /**
     * error : 1
     * message : 用户不存在
     * a : login
     * c : user
     */

    private int error;
    private String message;
    private String a;
    private String c;

}
