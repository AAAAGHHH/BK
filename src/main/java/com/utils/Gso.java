
 package com.utils;

import com.google.gson.Gson;

/**
 * json转换工具
 */
public class Gso {
    private static final Gson gson = new Gson();

    public static String toJsonString(Object object) {
        return object == null ? null : gson.toJson(object);
    }
}
