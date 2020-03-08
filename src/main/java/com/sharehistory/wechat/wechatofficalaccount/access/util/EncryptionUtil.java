package com.sharehistory.wechat.wechatofficalaccount.access.util;

import java.security.MessageDigest;

/**
 * Package-name:com.sharehistory.wechat.wechatpublicnumber.access.util
 * Time:2020/3/7-12:02.
 * Autor:computer-Administrator--zhangtianxiao
 * Note:GOOD PROGRAMER.
 */

public class EncryptionUtil  {
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
