package com.kedacom.utils.encryptionstrategy;


/**
 * 加密工具
 * Created by chumengwei on 2017/6/11.
 */
public class Encryption {

    private static Encryption encryption;

    private Encryption() {

    }

    /**
     * 获取实例
     *
     * @return
     */
    public static Encryption getInstance() {
        if (encryption == null) {
            synchronized (Encryption.class) {
                if (encryption == null) {
                    encryption = new Encryption();
                }
            }
        }
        return encryption;
    }

    /**
     * 加密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public String encryptContent(String content) throws Exception {
        byte[] data = RcFourCoder.getInstance().encrypt(content.getBytes(), EncryptConfig.RC4_KEY.getBytes());
        return MessageDigestCoder.getInstance().encodeHex(data);
    }

    /**
     * 解密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public String decryptContent(String content) throws Exception {
        byte[] data = RcFourCoder.getInstance().decrypt(MessageDigestCoder.getInstance().decodeHex(content), EncryptConfig.RC4_KEY.getBytes());
        return new String(data);
    }

}
