package com.wen.util;

import java.util.UUID;

/**
 * Created by wenfeng on 2017/12/7.
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public static String getCurrentUUID(){
        return getUUID()+System.currentTimeMillis();
    }
}
