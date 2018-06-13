package com.example.caowj.newtask.mvvm.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.example.caowj.newtask.mvvm.data.Special;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;


public class RoomDataConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static List<Special> toSpecialList(String json) {
        return new Gson().fromJson(json, new TypeToken<List<Special>>() {
        }.getType());
    }

    @TypeConverter
    public static String toSpecialListJson(List<Special> specialList) {
        return new Gson().toJson(specialList);
    }
//
//    @TypeConverter
//    public static List<Comment> toCommentList(String json) {
//        return new Gson().fromJson(json, new TypeToken<List<Comment>>() {
//        }.getType());
//    }
//
//    @TypeConverter
//    public static String toCommentListJson(List<Comment> commentList) {
//        return new Gson().toJson(commentList);
//    }
//
//    @TypeConverter
//    public static List<News> toNewsList(String json) {
//        return new Gson().fromJson(json, new TypeToken<List<News>>() {
//        }.getType());
//    }
//
//    @TypeConverter
//    public static String toNewsListJson(List<News> newsList) {
//        return new Gson().toJson(newsList);
//    }

}
