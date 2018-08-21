package com.kedacom.module_common.utils.encryptionstrategy;

import android.util.Base64;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by chumengwei on 2017/6/11.
 */
public class RcFourCoder extends AbstractCoder {

    private static RcFourCoder ourInstance = new RcFourCoder();

    private RcFourCoder() {
    }

    public static RcFourCoder getInstance() {
        return ourInstance;
    }

    /**
     * 生成密钥
     *
     * @return
     * @throws Exception
     */
    public String initKey() throws Exception {
        return initKey(null);
    }

    /**
     * 生成密钥
     *
     * @param seed
     * @return
     * @throws Exception
     */
    public String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;

        if (seed != null) {
            secureRandom = new SecureRandom(Base64.decode(seed, Base64.DEFAULT));
        } else {
            secureRandom = new SecureRandom();
        }

        KeyGenerator kg = KeyGenerator.getInstance(getAlgorithm());
        if (getKeySize() == null) {
            kg.init(secureRandom);
        } else {
            kg.init(getKeySize(), secureRandom);
        }

        SecretKey secretKey = kg.generateKey();

        return encodeHex(secretKey.getEncoded());
    }

    protected Integer getKeySize() {
        return 128;
    }

    protected Key toKey(byte[] key) {
        return new SecretKeySpec(key, getAlgorithm());
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);

        Cipher cipher = Cipher.getInstance(getAlgorithmCipher());
        cipher.init(Cipher.DECRYPT_MODE, k);

        return cipher.doFinal(data);
    }

    protected String getAlgorithm() {
        return "ARCFOUR";
    }

    protected String getAlgorithmCipher() {
        return "ARCFOUR";
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(getAlgorithmCipher());
        cipher.init(Cipher.ENCRYPT_MODE, k);

        return cipher.doFinal(data);
    }
}
