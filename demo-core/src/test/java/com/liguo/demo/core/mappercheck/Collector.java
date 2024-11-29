package com.liguo.demo.core.mappercheck;

import java.util.ArrayList;
import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/23 16:12
 * @since 0.0.1
 */
public class Collector {
    // dao 单个方法的执行结果列表
    private List<CollectorItem> items = new ArrayList<CollectorItem>();

    public List<CollectorItem> getItems() {
        return items;
    }

    public List<CollectorItem> getFailedItems() {
        List<CollectorItem> failedItems = new ArrayList<CollectorItem>();
        for (CollectorItem item : items) {
            if (!item.isSuccess()) {
                failedItems.add(item);
            }
        }
        return failedItems;
    }

    public int failedCount() {
        return getFailedItems().size();
    }

    public void print() {
        List<CollectorItem> failedItems = getFailedItems();
        int total = getItems().size();
        int failedCount = failedItems.size();
        System.out.println("\n------ dao test result ------");
        System.out.println(String.format("共执行 %s 个方法.", total));
        System.out.println(String.format("成功 %s 个, 失败 %s 个.", total - failedCount, failedCount));

        if (failedCount > 0) {
            System.out.println("失败信息:");
            int i = 0;
            for (CollectorItem item : failedItems) {
                i++;
                System.out.println("\n" + i + "\t" + item);
            }
        }
        System.out.println("------ dao test result end ------\n");
    }
}
