package com.example.caowj.newtask.example.helper.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义注解的解释器
 *
 * @Author : caowj
 * @Date : 2018/4/26
 */
public class InjectViewUtils {

    /**
     * 通过反射获取字段的id，然后获取控件，然后做相应的设定
     *
     * @author Caowj
     * @Date 2018/4/26 11:14
     */
    public static void inject(final Activity activity) {
        Class clazz = activity.getClass();
        //通过字节码获取field的时候一定要用getDeclaredField(),只有该方法才能获取到任何权限修饰符的Field
        Field[] fields = clazz.getDeclaredFields();

        for (Field f : fields) {
            //设置为可访问，暴力反射，私有也能访问
            f.setAccessible(true);

            if (f.isAnnotationPresent(InjectView.class)) {
                //获取到字段的注解对象
                InjectView injectView = f.getAnnotation(InjectView.class);
                if (injectView == null) {
                    continue;//如果该方法上没有注解，循环下一个
                }

                int id = injectView.value();//获取注解中的值

                View view = activity.findViewById(id);//获取控件
                try {
                    f.set(activity, view);//将控件设置给field对象
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
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
