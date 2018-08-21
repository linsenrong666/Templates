package com.linsr.main.model;

import com.linsr.common.model.BasePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/8/21 上午9:57
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AddressPojo extends BasePojo {

    private String userName;
    private String phoneNumber;
    private String address;
    private boolean isDefault;

}
