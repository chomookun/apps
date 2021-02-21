package net.chomookun.apps.core;

import java.util.UUID;
import java.util.Base64;

public class IdGenerator {

    /**
     * generates UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString()
            .replaceAll("-", "")
            .toUpperCase();    
    }

    /**
     * hash MD5
     */
    public static String md5(String value) {
        System.out.println(value);
        return null;
    }

    /**
     * encode base64
     */
    public static String encodeBase64(String plainValue) throws Exception {
        byte[] bytes = Base64.getUrlEncoder().encode(plainValue.getBytes("UTF-8"));
        return new String(bytes, "UTF-8");
    }

    /**
     * decode base64
     */
    public static String decodeBase64(String encodedValue) throws Exception {
        byte[] bytes = Base64.getUrlDecoder().decode(encodedValue.getBytes("UTF-8"));
        return new String(bytes, "UTF-8");
    }

}