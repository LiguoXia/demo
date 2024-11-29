package com.liguo.demo.core.util;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/2/27 21:11
 * @since 0.0.1
 */
public class UnicodeConvertUtil {
    private static boolean isBig = true;

    /**
     * 将String内容转成Unicode,且忽略\\u
     *
     * @param str
     * @return
     */
    public static byte[] putString2UnicodeBytes(String str) {
        return putString2UnicodeBytes(str, isBig);
    }

    /**
     * 将字符串转成unicode
     *
     * @param str 待转字符串
     * @return unicode字符串
     */
    public static byte[] putString2UnicodeBytes(String str, boolean isBig) {
        str = (str == null ? "" : str);
        char c;
        int i, j;

        byte[] strByteRes = new byte[str.length() * 2];
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);

            if (isBig) { // 大端
                j = (c >>> 8); // 取出高8位
                strByteRes[2 * i] = (byte) j;

                j = (c & 0xFF); // 取出低8位
                strByteRes[2 * i + 1] = (byte) j;

            } else { // 小端
                j = (c & 0xFF); // 取出低8位
                strByteRes[2 * i] = (byte) j;

                j = (c >>> 8); // 取出高8位
                strByteRes[2 * i + 1] = (byte) j;
            }
        }

        return strByteRes;
    }

    /**
     * 将字符串转成unicode
     *
     * @param str
     * @return
     */
    public static String putString2UnicodeString(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8); //取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
            j = (c & 0xFF); //取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);

        }
        return (new String(sb));
    }




    /**
     * 将16进制表示的unicode转成中文，开头不含\\u
     *
     * @param unicodeBytes
     * @return
     */
    public static String unicodeBytes2Str(byte[] unicodeBytes) {
        return unicodeBytes2Str(unicodeBytes, true);
    }

    /**
     * \\u5f20   new byte[]{0x5f,0x20}
     *
     * @param unicodeBytes
     * @param isBig
     * @return
     */
    public static String unicodeBytes2Str(byte[] unicodeBytes, boolean isBig) {
        String strRes = "";
        if (null == unicodeBytes) {
            return strRes;
        }

        byte[] valueBytes = new byte[2];

        for (int i = 0; i < unicodeBytes.length; i += 2) {
            if (isBig) {
                valueBytes[0] = unicodeBytes[i];
                valueBytes[1] = unicodeBytes[i + 1];
                // strRes += ((char) Integer.valueOf(TBaseNumber.byte2HexString(valueBytes), 16).intValue());
                strRes += TBaseNumber.byte2Char(valueBytes);
            } else {
                valueBytes[0] = unicodeBytes[i + 1];
                valueBytes[1] = unicodeBytes[i];
                //  strRes += ((char) Integer.valueOf(TBaseNumber.byte2HexString(valueBytes), 16).intValue());
                strRes += TBaseNumber.byte2Char(valueBytes);
            }

        }

        return strRes;
    }



    public  static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格 */
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }
}
