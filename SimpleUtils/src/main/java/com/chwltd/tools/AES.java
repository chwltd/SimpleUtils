package com.chwltd.tools;

import android.util.Base64;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public static String encrypt(String str, String str2) throws Exception {
        SecretKeySpec generateKey = generateKey(str);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(1, generateKey, new IvParameterSpec(new byte[16]));
        return Base64.encodeToString(cipher.doFinal(str2.getBytes()), 0);
    }

    public static String decrypt(String str, String str2) throws Exception {
        SecretKeySpec generateKey = generateKey(str);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(2, generateKey, new IvParameterSpec(new byte[16]));
        return new String(cipher.doFinal(Base64.decode(str2, 0)));
    }

    private static SecretKeySpec generateKey(String str) throws Exception {
        return new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8")), 16), "AES");
    }
}