package com.kedacom.module_common.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Gson工具类<br/>
 * <p>
 * 注意：1、实体类的字段类型，字段名称必须一致；<br/>
 * 2、Gson下的JsonObject和原生态的大小写有区别；<br/>
 * 3、Gson解析null替换为空字符串<br/>
 * 4、json的字段类型和实体的类型必须保持一致；<br/>
 * <p>
 * 缺点就是无法设置null替换，需要特别处理。<br/>
 * <p>
 * 字段数量可以不一致，但是key（大小写）必须一致。
 * package: com.jsfengling.qipai.tools.json
 * author: caowj
 * date: 2016/11/29 14:23
 */
public class GsonUtil {

    private static Gson mGson = null;

    static {
        if (mGson == null) {
//            mGson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
            mGson = new Gson();
           /* mGson = new GsonBuilder()
                    //序列化null
                    .serializeNulls()
                    // 设置日期时间格式，另有2个重载方法
                    // 在序列化和反序化时均生效
//                    .setDateFormat("yyyy-MM-dd")
                    // 禁此序列化内部类
                    .disableInnerClassSerialization()
                    //生成不可执行的Json（多了 )]}' 这4个字符）
                    .generateNonExecutableJson()
                    //禁止转义html标签
                    .disableHtmlEscaping()
                    //格式化输出
                    .setPrettyPrinting()
                    .create();*/
        }
    }

    /**
     * 获取Gson对象
     *
     * @return
     */
    public static Gson getGsonObj() {
        return mGson;
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonToString(Object object) {
        String gsonString = null;
        if (mGson != null) {
            gsonString = mGson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (mGson != null) {
            t = mGson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (mGson != null) {
            list = mGson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (mGson != null) {
            map = mGson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 按章节点得到相应的内容
     *
     * @param jsonString json字符串
     * @param key        节点
     * @return 节点对应的内容
     */
    public static JsonElement getGsonEleByKey(String jsonString, String key) {
        if (TextUtils.isEmpty(jsonString)) {
            throw new RuntimeException("json字符串");
        }
        if (TextUtils.isEmpty(key)) {
            throw new RuntimeException("note标签不能为空");
        }
        JsonElement element = new JsonParser().parse(jsonString);
        if (element.isJsonNull()) {
            throw new RuntimeException("得到的jsonElement对象为空");
        }
        return element.getAsJsonObject().get(key);
    }

    /**
     * 根据key获取字符串（不是字符串对象）
     *
     * @param jsonString
     * @param key
     * @return 1001
     */
    public static String getGsonStrByKey(String jsonString, String key) {
//        TODO getAsString()区别于toString();
        return getGsonEleByKey(jsonString, key).getAsString();
    }

    /**
     * 根据key获取字符串对象(字符串对象带有引号)
     *
     * @param jsonString
     * @param key
     * @return "1001"
     */
//    public static String getGsonObjByKey(String jsonString, String key) {
////        TODO getAsString()区别于toString();
//        return getGsonEleByKey(jsonString, key).toString();
//    }

    /**
     * 按照节点得到节点内容，转化为一个数组
     *
     * @param arrayStr  json数组字符串
     * @param beanClazz 集合里存入的数据对象
     * @return 含有目标对象的集合
     */
    public static <T> List<T> parserArrayStrToBeans(String arrayStr, Class<T> beanClazz) {
        if (TextUtils.isEmpty(arrayStr)) {
            throw new RuntimeException("json字符串为空");
        }
        JsonElement jsonElement = new JsonParser().parse(arrayStr);
        if (jsonElement.isJsonNull()) {
            throw new RuntimeException("得到的jsonElement对象为空");
        }
        if (!jsonElement.isJsonArray()) {
            throw new RuntimeException("json字符不是一个数组对象集合");
        }
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        List<T> beans = new ArrayList<T>();
        for (JsonElement jsonElement2 : jsonArray) {
            T bean = mGson.fromJson(jsonElement2, beanClazz);
            beans.add(bean);
        }
        return beans;
    }

    /**
     * 将JsonArray转换成实体类集合
     *
     * @param jsonArray
     * @param beanClazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parserJsonArrToBeans(JsonArray jsonArray, Class<T> beanClazz) {
        List<T> beans = new ArrayList<T>();
        for (JsonElement jsonElement2 : jsonArray) {
            T bean = mGson.fromJson(jsonElement2, beanClazz);
            beans.add(bean);
        }
        return beans;
    }


    public static <T> ArrayList<T> parserJsonArrToBeans2(JsonArray jsonArray, Class<T> beanClazz) {
        ArrayList<T> beans = new ArrayList<T>();
        for (JsonElement jsonElement2 : jsonArray) {
            T bean = mGson.fromJson(jsonElement2, beanClazz);
            beans.add(bean);
        }
        return beans;
    }

    /**
     * 将json格式转换成map对象
     *
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        Map<?, ?> objMap = null;
        if (mGson != null) {
            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
            }.getType();
            objMap = mGson.fromJson(jsonStr, type);
        }
        return objMap;
    }

    /**
     * 根据
     *
     * @param jsonStr
     * @param key
     * @return
     */
    public static Object getJsonValue(String jsonStr, String key) {
        Object rulsObj = null;
        Map<?, ?> rulsMap = jsonToMap(jsonStr);
        if (rulsMap != null && rulsMap.size() > 0) {
            rulsObj = rulsMap.get(key);
        }
        return rulsObj;
    }

    /**
     * 显示格式化后的gson
     *
     * @param jsonStr
     */
    public static void showFormatJson(String jsonStr) {
        String prettyJsonString = jsonFormatter(jsonStr);
//        System.out.println("JSON格式化前：");
//        System.out.println(jsonStr);
        System.out.println("JSON格式化后：");
        System.out.println(prettyJsonString);
    }

    public static String jsonFormatter(String uglyJSONString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSONString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

    /**
     * Gson解析null替换为空字符串<br/>
     * http://www.itwendao.com/article/detail/10072.html
     *
     * @param <T>
     */
//    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
//
//        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
//            Class<T> rawType = (Class<T>) type.getRawType();
//            if (rawType != String.class) {
//                return null;
//            }
//            return (TypeAdapter<T>) new StringNullAdapter();
//        }
//    }

    /************************************/
    public static String StringToCodeType(String str) {
//        方法一：
//        JsonParser jp = new JsonParser();
//        JsonElement je = jp.parse(str);
//        JsonObject jsonObj = jp.parse(str).getAsJsonObject();
//        jsonObj.get("code").getAsString();
        return getGsonStrByKey(str, "code");
    }

    /**
     * 返回值不是json，格式，需要特殊处理。<p/>
     * 例如：http://test.qipaiapp.com///QiPaiAPI/PF/PF.asmx/UserLikeIsTrue?UserID=1146&COFID=12&token=1
     *
     * @param responseData
     * @return
     */
    public static String getJsonData(String responseData) {
        final String START_FLAG = "<string xmlns=\"http://tempuri.org/\">";
        final String END_FLAG = "</string>";
//        LogUtil.myD("responseData:" + responseData);

        if (responseData != null && responseData.contains(START_FLAG)) {
            int indexS = responseData.indexOf(START_FLAG);
            int indexEnd = responseData.indexOf(END_FLAG);
//            LogUtil.myD("indexS:" + indexS + ",indexEnd:" + indexEnd);
            if (indexS >= 0 && indexEnd >= indexS) {
                responseData = responseData.substring(indexS + START_FLAG.length(), indexEnd);
            }
        } else {
            LogUtil.myD("不需要特殊处理。");
        }

        return responseData;
    }
}