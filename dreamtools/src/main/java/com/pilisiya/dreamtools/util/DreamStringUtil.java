package com.pilisiya.dreamtools.util;

/**
 * Created by xujiang on 2018/6/5.
 */

public class DreamStringUtil {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    /**
     * 字符串转16进制字符串
     */
    public static String string2HexString(String s) throws Exception {
        String r = bytes2HexString(string2Bytes(s));
        return r;
    }

    /**
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        String r = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }

        return r;
    }

    /**
     * 字符串转字节数组
     */
    public static byte[] string2Bytes(String s) {
        byte[] r = s.getBytes();
        return r;
    }

    public static byte[] hexString2Bytes(String data) {
        if (isNullEmpty(data)) {
            return EMPTY_BYTE_ARRAY;
        } else {
            byte[] result = new byte[(data.length() + 1) / 2];
            if ((data.length() & 1) == 1) {
                data = data + "0";
            }

            for (int i = 0; i < result.length; ++i) {
                result[i] = (byte) (hex2byte(data.charAt(i * 2 + 1)) | hex2byte(data.charAt(i * 2)) << 4);
            }

            return result;
        }
    }

    public static byte hex2byte(char hex) {
        if (hex <= 'f' && hex >= 'a') {
            return (byte) (hex - 97 + 10);
        } else if (hex <= 'F' && hex >= 'A') {
            return (byte) (hex - 65 + 10);
        } else {
            return hex <= '9' && hex >= '0' ? (byte) (hex - 48) : 0;
        }
    }

    public static boolean isNullEmpty(String str) {
        return str == null || str.length() == 0;
    }


}
