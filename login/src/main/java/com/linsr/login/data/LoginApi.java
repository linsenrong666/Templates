package com.linsr.login.data;

import com.linsr.common.model.ResponsePojo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description
 *
 * @author Linsr 2018/8/7 下午4:50
 */
public interface LoginApi {

    @FormUrlEncoded
    @POST("api.php?c=user&a=login")
    Observable<ResponsePojo> login(@Field("username") String userName,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("api.php?c=user&a=register")
    Observable<ResponsePojo> register(@Field("username") String userName,
                                      @Field("password") String password,
                                      @Field("mobile") String phone,
                                      @Field("code") String code,
                                      @Field("recode") String recode,
                                      @Field("key") String key);

    @FormUrlEncoded
    @POST("api.php?c=user&a=send_code")
    Observable<ResponsePojo> sendCode(@Field("mobile") String mobile,
                                      @Field("key") String key);

    @FormUrlEncoded
    @POST("api.php?c=user&a=send_forget_code")
    Observable<ResponsePojo> sendForgetCode(@Field("mobile") String mobile,
                                            @Field("key") String key);

    @FormUrlEncoded
    @POST("api.php?c=user&a=reset_password")
    Observable<ResponsePojo> resetPassword(@Field("mobile") String mobile,
                                           @Field("code") String code,
                                           @Field("password") String password,
                                           @Field("key") String key);

    @FormUrlEncoded
    @POST("api.php?c=user&a=wx_login")
    Observable<ResponsePojo> wxLogin(@Field("code") String code);

}
