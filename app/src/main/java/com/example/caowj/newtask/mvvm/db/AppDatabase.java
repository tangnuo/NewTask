package com.example.caowj.newtask.mvvm.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.example.caowj.newtask.mvvm.db.converter.RoomDataConverter;
import com.example.caowj.newtask.mvvm.db.dao.SpecialListDao;
import com.example.caowj.newtask.mvvm.db.entity.SpecialListEntity;


@Database(entities = {SpecialListEntity.class,},
        version = 1, exportSchema = false)
@TypeConverters(RoomDataConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    @VisibleForTesting
    public static final String DATABASE_NAME = "newtask-db";

    private static AppDatabase INSTANCE;

//    public abstract NewsDao newsDao();
private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

//    public abstract BannerDao bannerDao();
//
//    public abstract NewDetailDao newDetailDao();
//
//    public abstract CommentListDao commentListDao();
//
//    public abstract NewsListDao newsListDao();
//
//    public abstract SpecialDetailDao specialDetailDao();
//
//    public abstract UserInfoDao userInfoDao();

    public static AppDatabase get(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .build();
                    INSTANCE.updateDatabaseCreated(context);
                }
            }
        }
        return INSTANCE;
    }

    public abstract SpecialListDao specialListDao();

    private void updateDatabaseCreated(Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            isDatabaseCreated.postValue(true);
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }
}
