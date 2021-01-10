package com.pilisiya.dream;

import android.text.TextUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
        System.out.println("FF58:" + getTlvValueByTag("1F500430303030FF36042D363533FF38032D3935FF5881803430383633202020202020202020202030303030303030303030303030303032303030303030303030303030303031313030303030303030303030303030303230303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030", "FF58"));
    }

    public String getTlvValueByTag(String tlvData, String tag) {
//        if (TextUtils.isEmpty(tlvData)) {
//            return null;
//        }
        boolean hasTLV = tlvData.contains(tag);
        if (hasTLV) {
            int lengthIndex = tlvData.indexOf(tag);
            int length = Integer.parseInt(tlvData.substring(lengthIndex + tag.length(), lengthIndex + tag.length() + 2), 16);

            int len = length - 128;
            String oriTagValue;
            if (len < 0) {
                oriTagValue = tlvData.substring(lengthIndex + tag.length() + 2, lengthIndex + tag.length() + 2 + length * 2);
            } else if (len == 1) {
                length = Integer.parseInt(tlvData.substring(lengthIndex + tag.length() + 2, lengthIndex + tag.length() + 4), 16);
                oriTagValue = tlvData.substring(lengthIndex + tag.length() + 4, lengthIndex + tag.length() + 4 + length * 2);
            } else if (len == 2) {
                length = Integer.parseInt(tlvData.substring(lengthIndex + tag.length() + 2, lengthIndex + tag.length() + 6), 16);
                oriTagValue = tlvData.substring(lengthIndex + tag.length() + 6, lengthIndex + tag.length() + 6 + length * 2);
            } else {
                oriTagValue = "";
            }
            String tagValue = hexStr2Str(oriTagValue);
            return tagValue;

        } else {
            return null;
        }

    }

    public String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        try {
            return new String(bytes, "gbk");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}