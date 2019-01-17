package com.linsr.main.model;

import com.linsr.common.model.BizPojo;
import com.linsr.main.model.bean.OrderListBean;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/8/31 下午5:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderPojo extends BizPojo {

    private List<OrderListBean> list;
    private int count;

}
