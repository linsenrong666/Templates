package com.linsr.main.model;

import com.linsr.common.model.BasePojo;
import com.linsr.common.model.BizPojo;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/8/13 下午5:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendPojo extends BizPojo {

    private List<IsbestBean> isbest;

}
