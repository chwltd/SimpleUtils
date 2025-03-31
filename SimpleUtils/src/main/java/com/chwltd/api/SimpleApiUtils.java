package com.chwltd.api;

import com.chwltd.utils.OkHttpUtils;
import com.chwltd.utils.TextUtils;
import com.google.gson.JsonObject;
public class SimpleApiUtils {
    public static String getAppData() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("appid", ApiConfig.getAppid());
            jsonObject.addProperty("timestamp", System.currentTimeMillis());
            String result = OkHttpUtils.postJson(ApiConfig.getAdminSimple() + "getAppData", jsonObject.toString());
            String results;
            if(TextUtils.isJson(result)){
                results = result;
            }else if(result.contains("Error")){
                results = "请求错误";
            }else {
                results = SignUtils.decrypt(result);
            }
            return results;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
