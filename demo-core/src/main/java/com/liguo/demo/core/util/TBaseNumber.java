package com.liguo.demo.core.util;

import java.util.Formatter;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/2/27 21:12
 * @since 0.0.1
 */
public class TBaseNumber {
    /****************************** 十进制 转 其他进制 *****************************/
    /**
     * 十进制转二进制
     *
     * @param decimalInt int类型的十进制数据
     * @return 输出字符型二进制
     */
    public static String dec2BinStr(int decimalInt) {
        return Integer.toBinaryString(decimalInt);
    }

    /**
     * 十进制转八进制
     *
     * @param decimalInt int类型的十进制数据
     * @return 输出字符型八进制
     */
    public static String dec2OctStr(int decimalInt) {
        return Integer.toOctalString(decimalInt);
    }

    /**
     * 十进制转十六进制
     *
     * @param decimalInt int类型的十进制数据
     * @return 输出字符型十六进制
     */
    public static String dec2HexStr(int decimalInt) {
        return Integer.toHexString(decimalInt);
    }

    public static boolean isBig = true; //是否是大端

    /**
     * short转byte数组
     *
     * @param b
     * @param s
     * @param index
     */
    public static void putShort2Byte(byte[] b, short s, int index) {
        if (isBig) {
            b[index + 0] = (byte) (s >> 8);
            b[index + 1] = (byte) (s >> 0);
        } else {
            b[index + 1] = (byte) (s >> 8);
            b[index + 0] = (byte) (s >> 0);
        }
    }

    /**
     * short转byte数组
     *
     * @param s
     * @return
     */
    public static byte[] putShort2Byte(short s) {
        byte[] b = new byte[2];
        if (isBig) {
            b[0] = (byte) (s >> 8);
            b[1] = (byte) (s >> 0);
        } else {
            b[1] = (byte) (s >> 8);
            b[0] = (byte) (s >> 0);
        }
        return b;
    }

    /**
     * 通过byte数组取到short
     *
     * @param b
     * @param index 第几位开始取
     * @return
     */
    public short byte2Short(byte[] b, int index) {
        if (isBig) {
            return (short) ((((b[index + 0] & 0xff) << 8) | b[index + 1] & 0xff));
        } else {
            return (short) ((((b[index + 1] & 0xff) << 8) | b[index + 0] & 0xff));
        }
    }

    /**
     * 通过byte数组取到short
     *
     * @param b
     * @return
     */
    public static short byte2Short(byte[] b) {
        if (isBig) {
            return (short) (((b[0] & 0xff) << 8) | (b[1] & 0xff));
        } else {
            return (short) ((((b[1] & 0xff) << 8) | b[0] & 0xff));
        }
    }

    public static void putChar2Byte(byte[] b, char c, int index) {
        if (isBig) {
            b[index] = (byte) ((c & 0xff00) >> 8);
            b[index + 1] = (byte) (c & 0x00ff);
        } else {
            b[index + 1] = (byte) ((c & 0xff00) >> 8);
            b[index] = (byte) (c & 0x00ff);
        }

    }

    public static byte[] putChar2Byte(char c) {
        byte[] b = new byte[2];
        if (isBig) {
            b[0] = (byte) ((c & 0xff00) >> 8);
            b[1] = (byte) (c & 0x00ff);
        } else {
            b[1] = (byte) ((c & 0xff00) >> 8);
            b[0] = (byte) (c & 0x00ff);
        }
        return b;
    }

    public char byte2Char(byte[] b, int index) {
        char c = 0;
        if (isBig) {
            c = (char) (((b[index] & 0xFF) << 8) | (b[index + 1] & 0xFF));
        } else {
            c = (char) (((b[index + 1] & 0xFF) << 8) | (b[index + 0] & 0xFF));
        }
        return c;
    }

    public static char byte2Char(byte[] b) {
        char c = 0;
        if (isBig) {
            c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
        } else {
            c = (char) (((b[1] & 0xFF) << 8) | (b[0] & 0xFF));
        }
        return c;
    }

    public static void putInt2Byte(byte[] bb, int x, int index) {
        if (isBig) {
            bb[index + 0] = (byte) (x >> 24);
            bb[index + 1] = (byte) (x >> 16);
            bb[index + 2] = (byte) (x >> 8);
            bb[index + 3] = (byte) (x >> 0);
        } else {
            bb[index + 3] = (byte) (x >> 24);
            bb[index + 2] = (byte) (x >> 16);
            bb[index + 1] = (byte) (x >> 8);
            bb[index + 0] = (byte) (x >> 0);
        }
    }

    public static byte[] putInt2Byte(int x) {
        byte[] bb = new byte[4];
        if (isBig) {
            bb[0] = (byte) (x >> 24);
            bb[1] = (byte) (x >> 16);
            bb[2] = (byte) (x >> 8);
            bb[3] = (byte) (x >> 0);
        } else {
            bb[3] = (byte) (x >> 24);
            bb[2] = (byte) (x >> 16);
            bb[1] = (byte) (x >> 8);
            bb[0] = (byte) (x >> 0);
        }
        return bb;
    }


    /**
     * 通过byte数组取到int
     *
     * @param bb
     * @param index 第几位开始
     * @return
     */
    public int byte2Int(byte[] bb, int index) {
        if (isBig) {
            return (int) ((((bb[index + 0] & 0xff) << 24) | ((bb[index + 1] & 0xff) << 16) | ((bb[index + 2] & 0xff) << 8) | ((bb[index + 3] & 0xff) << 0)));
        } else {
            return (int) ((((bb[index + 3] & 0xff) << 24) | ((bb[index + 2] & 0xff) << 16) | ((bb[index + 1] & 0xff) << 8) | ((bb[index + 0] & 0xff) << 0)));
        }
    }

    public int byte2Int(byte[] bb) {
        if (isBig) {
            return (int) ((((bb[0] & 0xff) << 24) | ((bb[1] & 0xff) << 16) | ((bb[2] & 0xff) << 8) | ((bb[3] & 0xff) << 0)));
        } else {
            return (int) ((((bb[3] & 0xff) << 24) | ((bb[2] & 0xff) << 16) | ((bb[1] & 0xff) << 8) | ((bb[0] & 0xff) << 0)));
        }
    }

    public void putFloat2Byte(byte[] bb, float x, int index) {
        int l = Float.floatToIntBits(x);
        if (isBig) {
            for (int i = 0; i < 4; i++) {
                bb[index + 3 - i] = Integer.valueOf(l).byteValue();
                l = l >> 8;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                bb[index + i] = Integer.valueOf(l).byteValue();
                l = l >> 8;
            }
        }
    }

    public byte[] putFloat2Byte(float x) {
        byte[] bb = new byte[4];
        int l = Float.floatToIntBits(x);
        if (isBig) {
            for (int i = 0; i < 4; i++) {
                bb[3 - i] = Integer.valueOf(l).byteValue();
                l = l >> 8;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                bb[i] = Integer.valueOf(l).byteValue();
                l = l >> 8;
            }
        }
        return bb;
    }

    public static float byte2Float(byte[] b, int index) {
        int l;
        if (isBig) {
            l = b[index + 3];
            l &= 0xff;
            l |= ((long) b[index + 2] << 8);
            l &= 0xffff;
            l |= ((long) b[index + 1] << 16);
            l &= 0xffffff;
            l |= ((long) b[index + 0] << 24);
        } else {
            l = b[index + 0];
            l &= 0xff;
            l |= ((long) b[index + 1] << 8);
            l &= 0xffff;
            l |= ((long) b[index + 2] << 16);
            l &= 0xffffff;
            l |= ((long) b[index + 3] << 24);
        }
        return Float.intBitsToFloat(l);
    }

    public static float byte2Float(byte[] b) {
        return byte2Float(b, 0);
    }


    public static void putLong2Byte(byte[] bb, long x, int index) {
        if (isBig) {
            bb[index + 0] = (byte) (x >> 56);
            bb[index + 1] = (byte) (x >> 48);
            bb[index + 2] = (byte) (x >> 40);
            bb[index + 3] = (byte) (x >> 32);
            bb[index + 4] = (byte) (x >> 24);
            bb[index + 5] = (byte) (x >> 16);
            bb[index + 6] = (byte) (x >> 8);
            bb[index + 7] = (byte) (x >> 0);
        } else {
            bb[index + 7] = (byte) (x >> 56);
            bb[index + 6] = (byte) (x >> 48);
            bb[index + 5] = (byte) (x >> 40);
            bb[index + 4] = (byte) (x >> 32);
            bb[index + 3] = (byte) (x >> 24);
            bb[index + 2] = (byte) (x >> 16);
            bb[index + 1] = (byte) (x >> 8);
            bb[index + 0] = (byte) (x >> 0);
        }
    }

    public static byte[] putLong2Byte(long x) {
        byte[] bb = new byte[8];
        if (isBig) {
            bb[0] = (byte) (x >> 56);
            bb[1] = (byte) (x >> 48);
            bb[2] = (byte) (x >> 40);
            bb[3] = (byte) (x >> 32);
            bb[4] = (byte) (x >> 24);
            bb[5] = (byte) (x >> 16);
            bb[6] = (byte) (x >> 8);
            bb[7] = (byte) (x >> 0);
        } else {
            bb[7] = (byte) (x >> 56);
            bb[6] = (byte) (x >> 48);
            bb[5] = (byte) (x >> 40);
            bb[4] = (byte) (x >> 32);
            bb[3] = (byte) (x >> 24);
            bb[2] = (byte) (x >> 16);
            bb[1] = (byte) (x >> 8);
            bb[0] = (byte) (x >> 0);
        }
        return bb;
    }


    public static long byte2Long(byte[] bb, int index) {
        if (isBig) {
            return ((((long) bb[index + 0] & 0xff) << 56) | (((long) bb[index + 1] & 0xff) << 48) | (((long) bb[index + 2] & 0xff) << 40) | (((long) bb[index + 3] & 0xff) << 32)
                    | (((long) bb[index + 4] & 0xff) << 24) | (((long) bb[index + 5] & 0xff) << 16) | (((long) bb[index + 6] & 0xff) << 8) | (((long) bb[index + 7] & 0xff) << 0));
        } else {
            return ((((long) bb[index + 7] & 0xff) << 56) | (((long) bb[index + 6] & 0xff) << 48) | (((long) bb[index + 5] & 0xff) << 40) | (((long) bb[index + 4] & 0xff) << 32)
                    | (((long) bb[index + 3] & 0xff) << 24) | (((long) bb[index + 2] & 0xff) << 16) | (((long) bb[index + 1] & 0xff) << 8) | (((long) bb[index + 0] & 0xff) << 0));
        }
    }

    public static long byte2Long(byte[] bb) {
        return byte2Long(bb, 0);
    }


    public static void putDouble2byte(byte[] bb, double x, int index) {
        long intBits = Double.doubleToLongBits(x);
        putLong2Byte(bb, intBits, 0);
    }

    public static double btye2Double(byte[] b, int index) {
        long l;
        if (isBig) {
            l = b[index + 7];
            l &= 0xff;
            l |= ((long) b[index + 6] << 8);
            l &= 0xffff;
            l |= ((long) b[index + 5] << 16);
            l &= 0xffffff;
            l |= ((long) b[index + 4] << 24);
            l &= 0xffffffffl;
            l |= ((long) b[index + 3] << 32);
            l &= 0xffffffffffl;
            l |= ((long) b[index + 2] << 40);
            l &= 0xffffffffffffl;
            l |= ((long) b[index + 1] << 48);
            l &= 0xffffffffffffffl;
            l |= ((long) b[index + 0] << 56);
        } else {
            l = b[0];
            l &= 0xff;
            l |= ((long) b[index + 1] << 8);
            l &= 0xffff;
            l |= ((long) b[index + 2] << 16);
            l &= 0xffffff;
            l |= ((long) b[index + 3] << 24);
            l &= 0xffffffffl;
            l |= ((long) b[index + 4] << 32);
            l &= 0xffffffffffl;
            l |= ((long) b[index + 5] << 40);
            l &= 0xffffffffffffl;
            l |= ((long) b[index + 6] << 48);
            l &= 0xffffffffffffffl;
            l |= ((long) b[index + 7] << 56);
        }
        return Double.longBitsToDouble(l);
    }


    /**
     * 获取byte字节的高四位
     *
     * @param data
     * @return
     */
    public static int getByteHigh4(byte data) {// 获取高四位
        int height;
        height = ((data & 0xf0) >> 4);
        return height;
    }

    /**
     * 获取byte字节的低四位
     *
     * @param data
     * @return
     */
    public static int getByteLow4(byte data) {// 获取低四位
        int low;
        low = (data & 0x0f);
        return low;
    }

    /**
     * @param bb
     * @param index
     * @param isUpperCase true:字母大写 false:字母小写
     * @return
     */
    public static String byte2HexString(byte[] bb, int index, boolean isUpperCase) {
        if (null == bb || bb.length == 0) {
            return "";
        }
        String hexStr = "";
        String formatterStr = "%02X";
        Formatter formatter = new Formatter();
        if (isUpperCase) {
            formatterStr = "%02X";
        } else {
            formatterStr = "%02x";
        }

        for (int i = index; i < bb.length; i++) {
            hexStr = formatter.format(formatterStr, bb[i]).toString();
        }
        return hexStr;
    }

    public static String byte2HexString(byte[] bb) {
        return byte2HexString(bb, 0, true);
    }

    public static String byte2HexString(byte[] bb, int index) {
        return byte2HexString(bb, index, true);
    }

    /**
     * 将单个byte转成16进制字符
     *
     * @param bb
     * @param isUpperCase
     * @return
     */
    public static String byte2HexString(byte bb, boolean isUpperCase) {

        String formatterStr = "%02X";
        Formatter formatter = new Formatter();
        if (isUpperCase) {
            formatterStr = "%02X";
        } else {
            formatterStr = "%02x";
        }

        return formatter.format(formatterStr, bb).toString();
    }

    public static String byte2HexString(byte bb) {

        return byte2HexString(bb, true);
    }

    public static byte[] hexStringToByte(String hex) {
        byte[] result = null;
        if (null == hex) {
            return result;
        }
        hex = hex.toUpperCase();
        final String HEX_NUMS_STR = "0123456789ABCDEF";
        int len = (hex.length() / 2);
        result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }
}
