package com.linsr.common.net;


import android.text.TextUtils;
import android.util.Base64;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.linsr.common.model.BizPojo;
import com.linsr.common.model.ResponsePojo;
import com.linsr.common.utils.JLog;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Description
 * @author Linsr
 */
public class NetUtils implements NetConstants {

    private static Gson sGson = new Gson();

    public static <T> ObservableTransformer<T, T> ioMain() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<ResponsePojo, T> handleResponse(final Class<T> tClass) {
        return new ObservableTransformer<ResponsePojo, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<ResponsePojo> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<ResponsePojo, String>() {
                            @Override
                            public String apply(@NonNull ResponsePojo result) {
                                String output = result.getOutput();
                                if (TextUtils.isEmpty(output)) {
                                    throw new ApiException("output为空");
                                }
                                return new String(Base64.decode(output.getBytes(), Base64.DEFAULT));
                            }
                        })
                        .map(new Function<String, T>() {
                            @Override
                            public T apply(String json) {
                                JLog.d("网络请求响应：" + json);
                                BizPojo bizPojo = sGson.fromJson(json, BizPojo.class);

                                return sGson.fromJson(json, tClass);
                            }
                        });
            }
        };
    }
}
