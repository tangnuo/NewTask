package com.example.caowj.newtask.module1.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * package: com.example.caowj.newtask.module1.converter
 * author: Administrator
 * date: 2017/10/16 11:45
 */
public class QipaiGsonConverterFactory<T> extends Converter.Factory {
    public static QipaiGsonConverterFactory create() {
        return create(new Gson());
    }

    public static QipaiGsonConverterFactory create(Gson gson) {
        return new QipaiGsonConverterFactory(gson);
    }

    private final Gson gson;

    private QipaiGsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody,
            ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new QipaiGsonResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?,
            RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }
}
