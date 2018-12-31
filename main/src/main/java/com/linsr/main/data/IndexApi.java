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
    Observable<ResponsePojo> recommendGoodsList(@Field("key") String key);


    @POST("api.php?c=category&a=index")
    Observable<ResponsePojo> categoryList();

    @FormUrlEncoded
    @POST("api.php?c=category&a=catlist")
    Observable<ResponsePojo> childCategoryList(@Field("fid") String fid,
                                               @Field("sid") String sid);

    @FormUrlEncoded
    @POST("api.php?c=goods&a=index")
    Observable<ResponsePojo> goodsInfo(@Field("id") String goodsId);


}
