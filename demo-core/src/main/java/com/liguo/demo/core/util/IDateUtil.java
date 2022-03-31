package com.liguo.demo.core.util;

import org.apache.commons.lang3.StringUtils;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/23 21:43
 * @since 0.0.1
 */
public class IDateUtil {

    /**
     * 把毫秒数转换成时分秒
     *
     * @param millis 毫秒
     * @return 时分秒
     */
    public static String millisToStringShort(long millis) {
        StringBuilder sb = new StringBuilder();
        long temp = millis;
        long h = 3600000;
        long m = 60000;
        long s = 1000;
        if (temp / h > 0) {
            sb.append(temp / h).append("小时");
        }
        temp = temp % h;

        if (temp / m > 0) {
            sb.append(temp / m).append("分钟");
        }
        temp = temp % m;
        if (temp / s > 0) {
            sb.append(temp / s).append("秒");
        }
        sb = StringUtils.isEmpty(sb) ? new StringBuilder(millis + "毫秒") : sb;
        return sb.toString();
    }

    public static String costTime(long startTime) {
        return millisToStringShort(System.currentTimeMillis() - startTime);
    }
}
