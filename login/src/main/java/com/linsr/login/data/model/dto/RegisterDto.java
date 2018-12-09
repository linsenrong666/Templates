package com.linsr.login.data.model.dto;

import lombok.Data;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午4:44
 */
@Data
public class RegisterDto {

    private String username;
    private String password;
    private String mobile;
    private String code;
    private String recode;
    private String key;

    public RegisterDto(String username, String password, String mobile, String code, String recode, String key) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.code = code;
        this.recode = recode;
        this.key = key;
    }
}
