package com.linsr.common.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Description
 *
 * @author Linsr 2018/11/30 下午3:29
 */
public class BaseGson {
    private static BaseGson sJGson;
    private static Gson mGson;

    private BaseGson() {
        mGson = new GsonBuilder()
                .registerTypeAdapter(Integer.class, new JsonSerializer<Integer>() {
                    @Override
                    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
                        if (src == src.longValue())
                            return new JsonPrimitive(src.longValue());
                        return new JsonPrimitive(src);
                    }
                }).registerTypeAdapter(long.class, new JsonSerializer<Long>() {
                    @Override
                    public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
                        if (src == src.longValue())
                            return new JsonPrimitive(src);
                        return new JsonPrimitive(src);
                    }
                })
                .excludeFieldsWithoutExposeAnnotation().create();
    }

    public static Gson instance() {
        if (sJGson == null) {
            synchronized (BaseGson.class) {
                if (sJGson == null) {
                    sJGson = new BaseGson();
                }
            }
        }
        return sJGson.gson();
    }

    private Gson gson() {
        return mGson;
    }
}
