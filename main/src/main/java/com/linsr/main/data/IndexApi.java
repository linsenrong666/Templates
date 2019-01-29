package com.linsr.main.data;

import com.linsr.common.model.ResponsePojo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午8:23
 */
public interface IndexApi {

    @FormUrlEncoded
    @POST("api.php?c=index&a=main")
    Observable<ResponsePojo> mainList(@Field("user_id") String userId);


    @FormUrlEncoded
    @POST("api.php?c=search&a=async_search")
    Observable<ResponsePojo> search(@Field("keyword") String keyword,
                                    @Field("last") int last,
                                    @Field("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("api.php?c=cate&a=isbest")
    Observable<ResponsePojo> recommendGoodsList(@Field("last") int last,
                                                @Field("pageSize") int pageSize);


    @POST("api.php?c=category&a=index")
    Observable<ResponsePojo> categoryList();

    @FormUrlEncoded
    @POST("api.php?c=category&a=catlist")
    Observable<ResponsePojo> childCategoryList(@Field("fid") String fid,
                                               @Field("sid") String sid);

    @FormUrlEncoded
    @POST("api.php?c=goods&a=index")
    Observable<ResponsePojo> goodsInfo(@Field("id") String goodsId);


    @FormUrlEncoded
    @POST("api.php?c=user&a=async_order_list")
    Observable<ResponsePojo> orderList(@Field("status") int status,
                                       @Field("last") int pageIndex,
                                       @Field("pageSize") int pageSize);


    @FormUrlEncoded
    @POST("api.php?c=user&a=index")
    Observable<ResponsePojo> userCenter(@Field("user_id") String userId);

    //加车
    @FormUrlEncoded
    @POST("api.php?c=cart&a=add_to_cart")
    Observable<ResponsePojo> addToCart(@Field("spec") String[] spec,
                                       @Field("spec_count") int specCount,
                                       @Field("goodsId") String goodsId,
                                       @Field("number") int number);

    //购物车列表
    @FormUrlEncoded
    @POST("api.php?c=cart&a=index")
    Observable<ResponsePojo> cartList(@Field("key") String key);

    //选择地址
    @FormUrlEncoded
    @POST("api.php?c=cart&a=consignee_do")
    Observable<ResponsePojo> chooseAddress(@Field("id") String addressId);


    //结算列表
    @POST("api.php?c=cart&a=checkout")
    Observable<ResponsePojo> settleList();

    //结算
    @FormUrlEncoded
    @POST("api.php?c=cart&a=done")
    Observable<ResponsePojo> settleAccounts(@Field("payment") int payment,
                                            @Field("shipping") int shipping,
                                            @Field("postscript") String postscript);

    //查看物流
    @FormUrlEncoded
    @POST("api.php?c=user&a=async_ems")
    Observable<ResponsePojo> queryExpress(@Field("invoice_no") String no);


    /**
     *
     * 确认收货
     * 不可确认收货：
     *1、订单已完成
     *2、发货状态不等于1（已发货）（商品未发货）
     */
    @FormUrlEncoded
    @POST("api.php?c=user&a=affirm_received")
    Observable<ResponsePojo> confirmReceived(@Field("order_id") String orderId);


    @FormUrlEncoded
    @POST("api.php?c=user&a=refund_do")
    Observable<ResponsePojo> refund(@Field("order_id") String orderId,
                                    @Field("refund_reason") String reason,
                                    @Field("refund_note") String node);

    @FormUrlEncoded
    @POST("api.php?c=user&a=cancel_refund")
    Observable<ResponsePojo> cancelRefund(@Field("order_id") String orderId);


    @FormUrlEncoded
    @POST("api.php?c=user&a=order_detail")
    Observable<ResponsePojo> orderDetails(@Field("order_id") String orderId);


    //修改商品数量
    @FormUrlEncoded
    @POST("api.php?c=goods&a=price")
    Observable<ResponsePojo> modifyNumber(@Field("id") String goodsId,
                                          @Field("attr") String[] array,
                                          @Field("number") int number);

    //修改购物车商品数量
    @FormUrlEncoded
    @POST("api.php?c=cart&a=update_number")
    Observable<ResponsePojo> modifyCartNumber(@Field("id") String recId,
                                              @Field("number") int number);


    //删除商品
    @FormUrlEncoded
    @POST("api.php?c=cart&a=drop_goods")
    Observable<ResponsePojo> dropGoods(@Field("id") String cartId,
                                       @Field("user_id") String userId);


    //收藏列表
    @FormUrlEncoded
    @POST("api.php?c=user&a=async_col_goods")
    Observable<ResponsePojo> collectList(@Field("last") int page,
                                         @Field("pageSize") int pageSize);

    //收藏列表
    @FormUrlEncoded
    @POST("api.php?c=user&a=like_goods")
    Observable<ResponsePojo> collect(@Field("id") String goodsId);


    //登出
    @FormUrlEncoded
    @POST("api.php?c=user&a=logout")
    Observable<ResponsePojo> logout(@Field("key") String key);//登出

    //删除地址
    @FormUrlEncoded
    @POST("api.php?c=user&a=address_drop")
    Observable<ResponsePojo> dropAddress(@Field("key") String key);

    //修改地址
    @FormUrlEncoded
    @POST("api.php?c=user&a=address_edit_do")
    Observable<ResponsePojo> editAddress(@Field("address_id") String address_id,
                                         @Field("consignee") String consignee,
                                         @Field("mobile") String mobile,
                                         @Field("address") String address,
                                         @Field("zipcode") String zipcode,
                                         @Field("is_default") String is_default,
                                         @Field("province_str") String province_str,
                                         @Field("city_str") String city_str,
                                         @Field("district_str") String district_str,
                                         @Field("user_id") String user_id);


}
