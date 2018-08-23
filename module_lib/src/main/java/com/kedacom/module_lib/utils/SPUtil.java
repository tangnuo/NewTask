package com.kedacom.module_lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by Administrator on 2016/7/15.
 */
public class SPUtil {

    private static SPUtil intance = null;
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SPUtil(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("caowj_sp", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static SPUtil getInstance(Context context) {
        if (intance == null) {
            intance = new SPUtil(context);
        }
        return intance;
    }

    /**
     * 存String
     *
     * @param key
     * @param value
     */
    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 存 int型的值
     *
     * @param key
     * @param value
     */
    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 存 float型的值
     *
     * @param key
     * @param value
     */
    public void saveFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();

    }

    /**
     * 存 布尔型的值
     *
     * @param key
     * @param value
     */
    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 存 long
     *
     * @param key
     * @param value
     */
    public void saveLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 清空某key
     *
     * @param key
     */
    public void removeKey(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 存 对象
     *
     * @param key
     * @param object
     */
    public void putObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(key, objectVal);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取 String
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return sp.getString(key, "");
    }

    /**
     * 取 int
     *
     * @param key
     * @return
     */
    public int getInt(String key, int defateValue) {
        return sp.getInt(key, defateValue);
    }

    /**
     * 取 布尔
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    /**
     * 取 Long
     *
     * @param key
     * @param defValue
     * @return
     */
    public Long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
    }


    /**
     * 取 对象
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObject(String key, Class<T> clazz) {
        if (sp.contains(key)) {
            String objectVal = sp.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
