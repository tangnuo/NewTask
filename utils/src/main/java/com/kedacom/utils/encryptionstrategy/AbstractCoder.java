package com.kedacom.utils.encryptionstrategy;


/**
 * AbstractCoder: 加解密
 * <p></p>
 * Author:  gqm
 * Date:    16/4/15
 * Version: since
 */
public abstract class AbstractCoder {


    /**
     * 16进制解码
     *
     * @param content
     * @return
     */
    public byte[] decodeHex(String content) {
        int i = 0;
        byte[] results = new byte[content.length() / 2];
        for (int k = 0; k < content.length(); ) {
            results[i] = (byte) (Character.digit(content.charAt(k++), 16) << 4);
            results[i] += (byte) (Character.digit(content.charAt(k++), 16));
            i++;
        }
        return results;
    }

    /**
     * 16进制编码
     *
     * @param content
     * @return
     */
    public String encodeHex(byte[] content) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < content.length; i++) {
            if (((int) content[i] & 0xff) < 0x10) {
                buffer.append("0");
            }
            buffer.append(Long.toString((int) content[i] & 0xff, 16));
        }
        return buffer.toString();
    }
}
