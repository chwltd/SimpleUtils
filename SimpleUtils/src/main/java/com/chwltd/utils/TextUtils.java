package com.chwltd.utils;
import android.annotation.SuppressLint;

import com.chwltd.tools.AES;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
    public static boolean isJson(String jsonString) {
        try (JsonReader jsonReader = new JsonReader(new StringReader(jsonString))) {
            JsonToken token = jsonReader.peek();
            return token != JsonToken.END_DOCUMENT;
        } catch (Exception e) {
            return false;
        }
    }
    //提取文本中的url
    public static List<String> extractLinks(String text) {
        List<String> links = new ArrayList<>();

        if (android.text.TextUtils.isEmpty(text)) {
            return links;
        }
        
        String regex = "(https?://(?:[\\w\\d\\-\\.]+)(?::\\d+)?(?:/(?:\\w+)*\\??(?:[\\w\\d\\-\\.&=]+)*(?:#[\\w\\-]+)?)?)";
        Matcher matcher = Pattern.compile(regex).matcher(text);

        while (matcher.find()) {
            String url = matcher.group();
            links.add(url);
        }

        return links;
    }
    /*
    Mus类库
    */
    //AES加解密
	public static String aes(int type,String t,String key)
	{
		try{
		if(type==0)
		return AES.encrypt(t,key);
		else if(type==1)
		return AES.decrypt(t,key);
		}
		catch(Exception e){}
		return "加密方式错误";
	}
	
	public static String getBetween(String str, String str2, String str3) {
        int i;
        int indexOf;
        if (str2 != null) {
            int indexOf2 = str.indexOf(str2);
            if (indexOf2 == -1) {
                return null;
            }
            i = indexOf2 + str2.length();
        } else {
            i = 0;
        }
        if (str3 == null) {
            indexOf = str.length();
        } else {
            indexOf = str.indexOf(str3, i);
            if (indexOf == -1) {
                return null;
            }
        }
        return str.substring(i, indexOf);
    }
	
    public static String hex2str(String str) {
        byte[] bArr = new byte[str.length() / 2];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < str.length()) {
                bArr[i2 / 2] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
                i = i2 + 2;
            } else {
                return new String(bArr, StandardCharsets.UTF_8);
            }
        }
    }

    public static String str2hex(String str) {
        StringBuilder sb = new StringBuilder();
        for (byte b : str.getBytes()) {
            sb.append(new StringBuffer().append(String.format("%02x", new Byte(b))).append(" ").toString());
        }
        return sb.toString().trim();
    }

    public static String stringToMD5(String str) throws NoSuchAlgorithmException {
        String bigInteger = new BigInteger(1, MessageDigest.getInstance("md5").digest(str.getBytes())).toString(16);
        for (int i = 0; i < 32 - bigInteger.length(); i++) {
            bigInteger = new StringBuffer().append("0").append(bigInteger).toString();
        }
        return bigInteger;
    }

    @SuppressLint("NewApi")
    public static String str2base64(String str) {
        return new String(Base64.getEncoder().encode(str.trim().getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8).trim().replace("\n", "");
    }

    @SuppressLint("NewApi")
    public static String base642Str(String str) {
        return new String(Base64.getDecoder().decode(str.trim()), StandardCharsets.UTF_8).trim();
    }

	//移除所有英文标点
    public static String removePunctuation(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isLetterOrDigit(charAt) || Character.isWhitespace(charAt)) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
	
	//出现次数计数
    public static int countOccurences(String str, String str2)
	{
        return str.split(str2,-1).length-1;
    }
}
