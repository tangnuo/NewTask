package com.example.caowj.newtask.mvvm.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.caowj.newtask.mvvm.data.Special;
import com.example.caowj.newtask.mvvm.db.converter.RoomDataConverter;

import java.util.List;


@Entity(tableName = "special_list_entity")
public class SpecialListEntity {

    @NonNull
    @PrimaryKey
    public String id;
    @TypeConverters(RoomDataConverter.class)
    public List<Special> specialList;

    public SpecialListEntity(@NonNull String id, List<Special> specialList) {
        this.id = id;
        this.specialList = specialList;
    }
}
