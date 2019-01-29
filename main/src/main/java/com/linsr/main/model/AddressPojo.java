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

    private String address_id;
    private String consignee;
    private String mobile;
    private String address;
    private String zipcode;
    private String is_default;
    private String province_str;
    private String city_str;
    private String district_str;
    private String user_id;

}
