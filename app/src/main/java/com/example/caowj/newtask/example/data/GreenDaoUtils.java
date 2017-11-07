package com.example.caowj.newtask.example.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.caowj.newtask.example.bean.DaoMaster;
import com.example.caowj.newtask.example.bean.DaoSession;

/**
 * package: com.example.caowj.newtask.example.data
 * author: Administrator
 * date: 2017/11/7 16:26
 */
public class GreenDaoUtils {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static Context mContext;

    private static GreenDaoUtils greenDaoUtils;

    private GreenDaoUtils() {
    }

    public static GreenDaoUtils getSingleTon(Context mContext2) {
        mContext = mContext2;
        if (greenDaoUtils == null) {
            greenDaoUtils = new GreenDaoUtils();
        }
        return greenDaoUtils;
    }

    private void initGreenDao() {
        mHelper = new DaoMaster.DevOpenHelper(mContext, "newTask-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getmDaoSession() {
        if (mDaoMaster == null) {
            initGreenDao();
        }
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            initGreenDao();
        }
        return db;
    }

}
