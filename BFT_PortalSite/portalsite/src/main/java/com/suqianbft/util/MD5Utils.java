package com.suqianbft.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String code(String str){
        try {
//            此 MessageDigest 类为应用程序提供信息摘要算法的功能
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            //转换md5码
            byte[] digest = md5.digest();
            int i;
            StringBuffer bf = new StringBuffer("");
            for (int offset = 0; offset < digest.length; offset++) {
                i=digest[offset];
                if (i<0)
                    i+=256;

                if (i<16)
                    bf.append("0");
                bf.append(Integer.toHexString(i));
            }
            //32位加密
            return bf.toString();
            //16位加密
            //return bf.toString().substring(8,24);

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }


//    public static void main(String[] args) {
//        String code = code("123456");
//        System.out.println(code);
//    }
}
