package com.linsr.common.net;

import android.support.annotation.NonNull;

import com.linsr.common.biz.ApplicationEx;
import com.linsr.common.biz.config.AppConfig;
import com.linsr.common.biz.config.UserInfoKey;
import com.linsr.common.utils.PrefsUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午4:44
 */
public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (request.body() instanceof FormBody) {
            FormBody formBody = (FormBody) request.body();
            FormBody.Builder builder = new FormBody.Builder();
            assert formBody != null;
            for (int i = 0; i < formBody.size(); i++) {
                builder.add(formBody.name(i), formBody.value(i));
            }
            if (AppConfig.getInstance().isLoggedIn()) {
                builder.add("token", PrefsUtils.getSharedString(ApplicationEx.getInstance(),
                        UserInfoKey.TOKEN));
            }
            formBody = builder.build();
            request = request.newBuilder().post(formBody).build();
        }
        return chain.proceed(request);
    }
}