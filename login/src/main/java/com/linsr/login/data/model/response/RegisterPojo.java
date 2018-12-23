package com.linsr.login.data.model.response;

import com.linsr.common.model.BizPojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午4:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterPojo extends BizPojo {


    /**
     * is_check : 1
     * user_id : 33913
     * token : ff109e04d47a567861b0b37925ddc115
     */

    private int is_check;
    private String user_id;
    private String token;
}
