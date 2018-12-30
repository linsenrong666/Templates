package com.linsr.common.net;


import android.arch.lifecycle.LifecycleOwner;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.stream.MalformedJsonException;
import com.linsr.common.model.BizPojo;
import com.linsr.common.model.ResponsePojo;
import com.linsr.common.net.exception.ApiException;
import com.linsr.common.net.exception.RetryException;
import com.linsr.common.utils.JLog;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

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
                            public String apply(@NonNull ResponsePojo result) throws ApiException {
                                String output = result.getOutput();
                                if (TextUtils.isEmpty(output)) {
                                    throw new ApiException("output为空");
                                }
                                return new String(Base64.decode(output.getBytes(), Base64.DEFAULT));
                            }
                        })
                        .map(new Function<String, T>() {
                            @Override
                            public T apply(String json) throws ApiException {
                                log(json);
                                BizPojo bizPojo = sGson.fromJson(json, BizPojo.class);
                                int error = bizPojo.getError();
                                String message = bizPojo.getMessage();
                                if (error != CODE_SUCCESS) {
                                    throw new ApiException(message);
                                }
                                return sGson.fromJson(json, tClass);
                            }
                        });
            }
        };
    }

    private static void log(String json) {
        try {
            JLog.d("网络请求响应：" + json);
            BizPojo bizPojo = sGson.fromJson(json, BizPojo.class);
            JLog.i("errorCode：" + bizPojo.getError() + ",message：" + bizPojo.getMessage());
        } catch (Exception e) {
            JLog.e("解析失败:" + json);
        }
    }

    public static Function<? super Observable<Throwable>, ? extends ObservableSource<?>> retry() {
        return retry(5);
    }

    public static Function<? super Observable<Throwable>, ? extends ObservableSource<?>> retry(
            final int maxConnectCount) {
        final int[] currentRetryCount = {0};
        final int[] _waitRetryTime = {0};
        return new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull final Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
                        if (throwable instanceof MalformedJsonException) {
                            return Observable.error(throwable);
                        } else if (throwable instanceof IOException) {
                            if (currentRetryCount[0] < maxConnectCount) {
                                currentRetryCount[0]++;
                                _waitRetryTime[0] = _waitRetryTime[0] + currentRetryCount[0] * 1000;
                                JLog.d("retryCount:" + currentRetryCount[0] + ",time:" + _waitRetryTime[0]);
                                return Observable.just(1).delay(_waitRetryTime[0], TimeUnit.MILLISECONDS);
                            } else {
                                JLog.d("retryCount:" + currentRetryCount[0] + ",time:" + _waitRetryTime[0]);
                                return Observable.error(new RetryException(currentRetryCount[0]));
                            }
                        } else {
                            return Observable.error(throwable);
                        }
                    }
                });
            }
        };
    }

    public static <T> AutoDisposeConverter<T> bindLifecycle(LifecycleOwner lifecycleOwner) {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner));
    }

}
