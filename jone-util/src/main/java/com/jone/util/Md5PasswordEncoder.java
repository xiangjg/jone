package com.jone.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5PasswordEncoder {
    public static String encrypt(String value, String key) throws NoSuchAlgorithmException {
        MessageDigest message = MessageDigest.getInstance("MD5");
        message.update(key.getBytes());
        message.update(value.getBytes());
        byte[] data = message.digest();
        return new sun.misc.BASE64Encoder().encode(data);
    }
}
