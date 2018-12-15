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
public interface UserApi {


    @FormUrlEncoded
    @POST("api.php?c=user&a=order_list")
    Observable<ResponsePojo> orderList(@Field("user_id") String userId);

}
