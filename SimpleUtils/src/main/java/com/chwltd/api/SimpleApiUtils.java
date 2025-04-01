package com.chwltd.api;

import com.chwltd.utils.DataUtils;
import com.chwltd.utils.OkHttpUtils;
import com.google.gson.JsonObject;
public class SimpleApiUtils {
    public static String getAppData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", ApiConfig.getPostLimit());
            jsonObject.addProperty("page", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());
            String result = OkHttpUtils.postJson(ApiConfig.getSimpleApi() + "getAppData", jsonObject.toString());
            return backData(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getHomeFragmentData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", ApiConfig.getPostLimit());
            jsonObject.addProperty("page", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());
            String result = OkHttpUtils.postJson(ApiConfig.getSimpleApi() + "getHomeFragmentData", jsonObject.toString());
            return backData(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getResFragmentData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", ApiConfig.getPostLimit());
            jsonObject.addProperty("page", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());
            String result = OkHttpUtils.postJson(ApiConfig.getSimpleApi() + "getResFragmentData", jsonObject.toString());
            return backData(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getActivitiesPageData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", ApiConfig.getPostLimit());
            jsonObject.addProperty("page", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());
            String result = OkHttpUtils.postJson(ApiConfig.getSimpleApi() + "getActivitiesPageData", jsonObject.toString());
            return backData(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFineSelectionPageData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", ApiConfig.getPostLimit());
            jsonObject.addProperty("page", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());
            String result = OkHttpUtils.postJson(ApiConfig.getSimpleApi() + "getFineSelectionPageData", jsonObject.toString());
            return backData(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPlatePageData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", ApiConfig.getPostLimit());
            jsonObject.addProperty("page", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());
            String result = OkHttpUtils.postJson(ApiConfig.getSimpleApi() + "getPlatePageData", jsonObject.toString());
            return backData(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMsgData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("usertoken", ApiConfig.getUserToken());
            jsonObject.addProperty("limit", ApiConfig.getMsgLimit());
            jsonObject.addProperty("page", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());
            String result = OkHttpUtils.postJson(ApiConfig.getSimpleApi() + "getMsgData", jsonObject.toString());
            return backData(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String backData(String data){
        String results;
        if(DataUtils.isJson(data)){
            results = data;
        }else if(data.contains("Error")){
            results = "请求错误";
        }else {
            try {
                results = SignUtils.decrypt(data);
            } catch (Exception e) {
                results = "解密失败"+e;
            }
        }
        return results;
    }
}
