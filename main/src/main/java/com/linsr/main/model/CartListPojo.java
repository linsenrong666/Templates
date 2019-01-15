package com.linsr.main.model;

import com.linsr.common.model.BizPojo;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2019/1/12 下午10:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CartListPojo extends BizPojo {

    private List<IsbestBean> recommended;

}
