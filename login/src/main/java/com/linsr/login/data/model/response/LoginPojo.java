package com.linsr.login.data.model.response;

import com.linsr.common.model.BizPojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/11/30 下午4:22
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginPojo extends BizPojo {

    /**
     * user_id : 6
     * token : d71b18349de82c5cc0d70bcd1ba0abe1
     */

    private String user_id;
    private String token;

    /**
     * sess_id : a99e78d28da6852e7865dbc89e87f73e
     * os : 其它
     * from : 其它
     */

    private String sess_id;
    private String os;
    private String from;
}
