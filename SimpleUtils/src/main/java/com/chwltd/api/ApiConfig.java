package com.chwltd.api;

public class ApiConfig {
    //admin 后台地址
    //key 后台key
    //device 用户设备码
    private static String admin,key,device;
    private static int appid;
    private static String userToken;
    private static String qqAppid;

    private static String version;

    public static String getAdmin(){
        return admin;
    }

    public static void setAdmin(String str){
        admin = str;
    }

    public static String getKey(){
        return key;
    }

    public static void setKey(String str){
        key = str;
    }

    public static int getAppid(){
        return appid;
    }

    public static void setAppid(int int1){
        appid = int1;
    }

    public static String getDevice() {
        return device;
    }

    public static void setDevice(String device) {
        ApiConfig.device = device;
    }

    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        ApiConfig.userToken = userToken;
    }

    public static String getQqAppid() {
        return qqAppid;
    }

    public static void setQqAppid(String qqAppid) {
        ApiConfig.qqAppid = qqAppid;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        ApiConfig.version = version;
    }
}
