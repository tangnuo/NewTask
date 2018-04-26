package com.example.caowj.newtask.example.helper.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author : caowj
 * @Date : 2018/4/26
 */

public class InjectViewUtils {
    public static void inject(final Activity activity) {
        Class clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            InjectView injectView = f.getAnnotation(InjectView.class);
            if (injectView == null) {
                continue;
            }

            int id = injectView.value();

            View view = activity.findViewById(id);

            try {
                f.set(activity, view);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            final Method method = methods[i];

            OnClick click = method.getAnnotation(OnClick.class);
            if (click == null) {
                continue;
            }

            int[] value = click.value();
            for (int j = 0; j < value.length; j++) {
                int id = value[j];

                final View v = activity.findViewById(id);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            method.invoke(activity, v);//反射调用用户设定的方法
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
