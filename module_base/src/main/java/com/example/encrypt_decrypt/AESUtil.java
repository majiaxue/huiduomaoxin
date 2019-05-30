package com.example.encrypt_decrypt;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by Administrator on 2019/3/14.
 */

public class AESUtil {
    /**
     * AES加密
     *
     * @param strIn
     * @return
     * @throws Exception
     */
    public static String encrypt(String strIn) {
        SecretKeySpec skeySpec = null;
        byte[] encrypted = null;
        try {
            skeySpec = getKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("1923195353035399".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            encrypted = cipher.doFinal(strIn.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BASE64Encoder().encode(encrypted);
    }

    /**
     * AES解密
     *
     * @param strIn
     * @return
     * @throws Exception
     */
    public static String decrypt(String strIn) {
        String originalString = "";
        try {
            SecretKeySpec skeySpec = getKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("1923195353035399".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(strIn);
            byte[] original = cipher.doFinal(encrypted1);
            originalString = new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return originalString;
    }

    public static String getSHAKey() {
        return SHAUtil.encryptToSHA(MD5Util.md5("Y@WKyzcB9LP7%SEy"));
    }

    private static SecretKeySpec getKey() throws Exception {
        byte[] arrBTmp = "Y@WKyzcB9LP7%SEy".getBytes();
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

        return skeySpec;
    }
}
