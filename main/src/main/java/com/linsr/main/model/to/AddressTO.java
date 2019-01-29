package com.linsr.main.model.to;

import com.linsr.common.model.BasePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2019/1/28 下午11:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddressTO extends BasePojo {

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
