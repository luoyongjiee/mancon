package com.efunds.market.mc.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * created by root 2016/3/27
 * 功能：
 */
public class JsonUtil {
    private final static Gson gson = new Gson();

    private final static Gson unableEscapinggson = new GsonBuilder().disableHtmlEscaping().create();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public String toJson(Object src, Type typeOfSrc){
        return gson.toJson(src,typeOfSrc);
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json,classOfT);
    }

    public <T> T fromJson(String json, Type typeOfT){
        return gson.fromJson(json,typeOfT);
    }


    public static String toJsonunableEscaping(Object obj){
        return gson.toJson(obj);
    }

    public String toJsonunableEscaping(Object src, Type typeOfSrc){
        return gson.toJson(src,typeOfSrc);
    }

    public <T> T fromJsonunableEscaping(String json, Class<T> classOfT) {
        return gson.fromJson(json,classOfT);
    }

    public <T> T fromJsonunableEscaping(String json, Type typeOfT){
        return gson.fromJson(json,typeOfT);
    }

}
