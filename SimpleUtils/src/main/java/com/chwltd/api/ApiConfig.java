package com.chwltd.api;

public class ApiConfig {
    private static String admin;
    private static String api;
    private static String simpleApi;
    private static String key;
    private static String device;
    private static String userToken;
    private static String appid;
    private static String version;
    private static String appidQQ;
    private static int msgLimit = 20;
    private static int postLimit = 10;
    private static int commentLimit = 20;
    private static int appLimit = 20;

    public static String getAdmin() {
        return admin;
    }

    public static void setAdmin(String admin) {
        ApiConfig.admin = admin;
        ApiConfig.simpleApi = admin + "/simpleapi/";
        ApiConfig.api = admin + "/api/";
    }

    public static String getApi() {
        return api;
    }

    public static void setApi(String api) {
        ApiConfig.api = api;
    }

    public static String getSimpleApi() {
        return simpleApi;
    }

    public static void setSimpleApi(String simpleApi) {
        ApiConfig.simpleApi = simpleApi;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        ApiConfig.key = key;
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

    public static String getAppid() {
        return appid;
    }

    public static void setAppid(String appid) {
        ApiConfig.appid = appid;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        ApiConfig.version = version;
    }

    public static String getAppidQQ() {
        return appidQQ;
    }

    public static void setAppidQQ(String appidQQ) {
        ApiConfig.appidQQ = appidQQ;
    }

    public static int getMsgLimit() {
        return msgLimit;
    }

    public static void setMsgLimit(int msgLimit) {
        ApiConfig.msgLimit = msgLimit;
    }

    public static int getPostLimit() {
        return postLimit;
    }

    public static void setPostLimit(int postLimit) {
        ApiConfig.postLimit = postLimit;
    }

    public static int getCommentLimit() {
        return commentLimit;
    }

    public static void setCommentLimit(int commentLimit) {
        ApiConfig.commentLimit = commentLimit;
    }

    public static int getAppLimit() {
        return appLimit;
    }

    public static void setAppLimit(int appLimit) {
        ApiConfig.appLimit = appLimit;
    }
}
