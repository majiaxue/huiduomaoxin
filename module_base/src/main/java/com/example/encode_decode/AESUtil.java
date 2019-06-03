package com.example.encode_decode;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by Administrator on 2019/3/14.
 */

public class AESUtil {
    private static final String CipherMode = "AES/CBC/PKCS5Padding";

    /**
     * AES加密
     *
     * @param strIn
     * @return
     * @throws Exception
     */
    public static String encrypt(String strIn, String randomStr) {
        SecretKeySpec skeySpec = null;
        byte[] encrypted = null;
        try {
            skeySpec = getKey(randomStr);
            Cipher cipher = Cipher.getInstance(CipherMode);
            IvParameterSpec iv = new IvParameterSpec(new byte[cipher.getBlockSize()]);
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
    public static String decrypt(String strIn, String randomStr) {
        String originalString = "";
        try {
            SecretKeySpec skeySpec = getKey(randomStr);
            Cipher cipher = Cipher.getInstance(CipherMode);
            IvParameterSpec iv = new IvParameterSpec(new byte[cipher.getBlockSize()]);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(strIn);
            byte[] original = cipher.doFinal(encrypted1);
            originalString = new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return originalString;
    }

    public static String getSHAKey(String randomStr) {
        return SHAUtil.encryptToSHA(MD5Util.md5(randomStr));
    }

    private static SecretKeySpec getKey(String randomStr) throws Exception {
        byte[] arrBTmp = randomStr.getBytes();
        byte[] arrB = new byte[32]; // 创建一个空的16位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

        return skeySpec;
    }

    public static String createRadomStr() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = "qazxcvbnmlkjhgfdswertyuiop!@#$%^&*213456789QWERTYUIOPLKJHGFDSAZXCVBNM";
        for (int i = 0; i < 40; i++) {
            stringBuffer.append(str.charAt((int) (Math.random() * (str.length() + 1))));
        }
        return stringBuffer.toString();
    }
}
