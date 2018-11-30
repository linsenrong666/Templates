package com.linsr.login.data;

import com.linsr.common.model.BasePojo;
import com.linsr.common.model.ResponsePojo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
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

}
