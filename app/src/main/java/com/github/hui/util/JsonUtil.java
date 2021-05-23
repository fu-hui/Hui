package com.github.hui.util;

import com.github.hui.model.User;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static String obj2str(User user) {
        return gson.toJson(user);
    }

    public static <T> Class<T> str2obj(String str, Class<T> z) {
        return gson.fromJson(str, (Type) z);
    }
}
