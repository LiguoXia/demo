package com.liguo.demo.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/10/11 14:17
 * @since 0.0.1
 */
public class IStringUtils {
    public static void main(String[] args) {
        String input = "本月资金计划消耗金额剩余不多，请及时追加资金计划。[资金计划周期:01202309012601,资金计划组织=任意字符串,流量项:2B110,是否总值:是]";

        // 定义正则表达式来匹配资金计划组织，使用自定义分隔符"="，直到分隔符或结尾
        Pattern pattern = Pattern.compile("资金计划组织=(.*?)(,|$)");
        Matcher matcher = pattern.matcher(input);

        // 查找匹配项
        if (matcher.find()) {
            // 提取匹配项中的资金计划组织部分
            String fundPlanOrganization = matcher.group(1);
            System.out.println("资金计划组织: " + fundPlanOrganization);
        } else {
            System.out.println("未找到资金计划组织信息");
        }
    }
}
