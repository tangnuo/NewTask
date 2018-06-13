package com.example.caowj.newtask.mvp.converter;

import com.example.caowj.newtask.utils.GsonUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * package: com.example.caowj.newtask.module1.converter
 * author: Administrator
 * date: 2017/10/16 13:22
 */
class QipaiGsonResponseBodyConverter<T> implements Converter<ResponseBody,
        T> {
    private final Gson gson;
    private final Type type;

    QipaiGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * 针对数据返回成功、错误不同类型字段处理
     */
    @Override
    public T convert(ResponseBody value) throws IOException {

        return gson.fromJson(GsonUtil.getJsonData(value.string()), type);
    }
}
