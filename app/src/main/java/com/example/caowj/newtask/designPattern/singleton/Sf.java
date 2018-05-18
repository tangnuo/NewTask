package com.example.caowj.newtask.designPattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TianBin on 2017/7/8 17:54.
 * Description :容器实现
 */

public class Sf {
    private static Map<String, Object> map = new HashMap<>();

    private Sf() {
    }

    public static void registerService(String key, Object instance) {
        if (!map.containsKey(key)) {
            map.put(key, instance);
        }
    }

    public static Object getService(String key) {
        return map.get(key);
    }
}
