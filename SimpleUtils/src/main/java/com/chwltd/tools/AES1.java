package com.chwltd.tools;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class AES1 {
    private static final String TRANSFORMATION = "AES/ECB/PKCS5PADDING";

    
    public static String encryptFile(String inputFile, String outputFile,String key) {
        return processFile(Cipher.ENCRYPT_MODE, inputFile, outputFile,key);
    }

    public static String decryptFile(String inputFile, String outputFile,String key) {
        return processFile(Cipher.DECRYPT_MODE,  inputFile, outputFile,key);
    }

    private static String processFile(int mode, String inputFile, String outputFile,String key) {
        try {
			SecretKey secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(mode, secretKey);
            byte[] inputBytes = Files.readAllBytes(Paths.get(inputFile));
            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
			return "success";
        } catch (Exception e) {
            return "error:"+e;
        }
    }
	private static SecretKeySpec generateKey(String str) throws Exception {
        return new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8")), 16), "AES");
    }
}

