package com.kedacom.utils.encryptionstrategy;

import java.security.MessageDigest;

/**
 * Created by chumengwei on 2017/6/11.
 */
public class MessageDigestCoder extends AbstractCoder {

    private static MessageDigestCoder ourInstance = new MessageDigestCoder();

    private MessageDigestCoder() {
    }

    public static MessageDigestCoder getInstance() {
        return ourInstance;
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public String encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return encodeHex(md5.digest());
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public String encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(data);

        return encodeHex(sha.digest());
    }
}
