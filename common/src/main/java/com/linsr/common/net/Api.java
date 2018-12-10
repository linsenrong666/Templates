package com.linsr.common.net;

import android.support.annotation.NonNull;

import com.linsr.common.biz.ApplicationEx;
import com.linsr.common.utils.JLog;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Description
 *
 * @author Linsr 2018/6/27 下午3:19
 */
public class Api {

    private static final String BASE_URL = "https://www.sisipay.com/";
    private static final String BASE_URL_DEBUG = "";
    /**
     * 连接超时时间
     */
    private static final int CONNECT_TIMEOUT = 10;
    /**
     * 读取超时时间
     */
    private static final int READ_TIMEOUT = 10;
    /**
     * 重试次数
     */
    private static final int RETRY_TIMES = 3;

    private ConcurrentMap<String, Object> mServiceCache;
    private Retrofit mRetrofit;

    private Api() {
        mServiceCache = new ConcurrentHashMap<>();
        mRetrofit = new Retrofit.Builder()
                .client(initClient())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static volatile Api mApi;

    public static <S> S getService(Class<S> c) {
        if (mApi == null) {
            synchronized (Api.class) {
                if (mApi == null) {
                    mApi = new Api();
                }
            }
        }
        S service;
        String key = c.getClass().getCanonicalName();
        if (mApi.mServiceCache.get(key) == null) {
            service = mApi.create(c);
            mApi.mServiceCache.put(key, service);
        } else {
            service = (S) mApi.mServiceCache.get(key);
        }
        return service;
    }

    private <S> S create(Class<S> c) {
        return mRetrofit.create(c);
    }

    private OkHttpClient initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        //重试次数
        if (RETRY_TIMES > 0) {
            builder.addInterceptor(new Retry(RETRY_TIMES));
        }
        boolean isDebug = ApplicationEx.getInstance().isDebug();
        //添加日志拦截
        if (isDebug) {
            builder.addInterceptor(new LogInterceptor());
        }

        return builder.build();
    }

    private class LogInterceptor implements Interceptor {

        private static final String TAG = "NET";
        private static final String POST = "POST";
        private static final String GET = "GET";

        @Override
        public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            okhttp3.Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();

            JLog.d(TAG, "| " + request.method() + "| 请求路径：" + request.url());
            String method = request.method();
            if (POST.equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    if (body != null) {
                        for (int i = 0; i < body.size(); i++) {
                            sb.append(body.encodedName(i))
                                    .append("=")
                                    .append(body.encodedValue(i)).append(",");
                        }
                        sb.delete(sb.length() - 1, sb.length());
                        JLog.d(TAG, "| 参数:" + URLDecoder.decode(sb.toString()));
                    }
                }
            } else if (GET.equals(method)) {

            }
            JLog.d(TAG, "| 用时:" + duration + "毫秒");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }

    /**
     * 自定义的，重试N次的拦截器
     * 通过：addInterceptor 设置
     */
    public static class Retry implements Interceptor {
        public int maxRetry;//最大重试次数
        private int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

        public Retry(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                JLog.i("Retry", "num:" + retryNum);
                response = chain.proceed(request);
            }
            return response;
        }
    }

}
