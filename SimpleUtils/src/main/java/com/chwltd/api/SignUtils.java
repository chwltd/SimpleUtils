package com.chwltd.api;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import com.google.gson.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SignUtils {
    /**
     * 生成签名
     *
     * @param params    json格式的参数
     * @return 签名
     */
    public static String buildSign(String params) {
        // 将JSON字符串解析为JsonObject
        JsonObject jsonObject = new Gson().fromJson(params, JsonObject.class);
        // 拼接字符串
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue().toString()).append("&");
        }
        sb.append("secretKey=").append(ApiConfig.getKey());
        return md5(sb.toString());
    }

    /**
     * MD5加密
     *
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(StandardCharsets.UTF_8)); // 使用UTF-8编码
            byte[] byteDigest = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte b : byteDigest) {
                i = b;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密失败：" + e.getMessage(), e);
        }
    }

        public static String decrypt(String encryptedText) throws Exception {
            byte[] encryptedData = Base64.getDecoder().decode(encryptedText);
            byte[] keyBytes = ApiConfig.getKey().getBytes(StandardCharsets.UTF_8);
            byte[] ivBytes = ApiConfig.getKey().getBytes(StandardCharsets.UTF_8);

            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

            byte[] decryptedData = cipher.doFinal(encryptedData);
            return new String(decryptedData, StandardCharsets.UTF_8);
        }
    }