package com.linsr.main.model.bean;

import com.linsr.common.model.BasePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午9:39
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderListBean extends BasePojo{
    /**
     * order_id : 22973
     * user_id : 6
     * rec_userid : 1
     * order_sn : 2018102223061727
     * order_time : 2018-10-22 22:58:09
     * order_status : 等待商家发货
     * shipping_id : 11
     * total_fee : 29.70
     * handler : <a class="btn cancel"  href="user.php?act=cancel_refund&order_id=22973">取消退款</a><a class="btn pay" href="#order_detail?order_id=22973">提醒发货</a>
     * desc : <span class="willclose" >退款申请中</span>
     */

    private String order_id;
    private String user_id;
    private String rec_userid;
    private String order_sn;
    private String order_time;
    private String order_status;
    private String shipping_id;
    private String total_fee;
    private String handler;
    private String desc;
}
