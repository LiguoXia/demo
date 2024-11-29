package com.liguo.demo.core.study.list;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MergeList {

    public static void main(String[] args) {
        // 创建list1和list2，用于测试
        List<Money> list1 = new ArrayList<>();
        List<Money> list2 = new ArrayList<>();

        // 添加测试数据到list1和list2
        list1.add(new Money(1, 100));
        list1.add(new Money(2, 200));
        list1.add(new Money(3, 300));
        list1.add(new Money(4, 400));

        list2.add(new Money(1, 50));
        list2.add(new Money(2, 100));
        list2.add(new Money(5, 500));

        // 输出list1和list2的内容
        System.out.println("list1 before merge:");
        System.out.println(list1);
        System.out.println("list2 before merge:");
        System.out.println(list2);

        // 遍历list1中的每个元素
        list1.forEach(money -> {
            // 在list2中查找是否有与当前元素相同的元素
            int index = list2.indexOf(money);
            if (index != -1) {
                log.info("对象:{}存在list2, 金额合并", JSON.toJSONString(money));
                // 如果找到了相同的元素，则将金额合并到list2中的该元素上
                list2.get(index).addAmount(money.getAmount());
            } else {
                log.info("对象:{}list2中不存在", JSON.toJSONString(money));
                // 如果没有找到相同的元素，则将当前元素添加到list2中
                // list2.add(money);
            }
        });

        // 输出合并后的list1和list2的内容
        System.out.println("list1 after merge:");
        System.out.println(list1);
        System.out.println("list2 after merge:");
        System.out.println(list2);
    }
}

class Money {
    private int id;
    private double amount;

    public Money(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void addAmount(double amount) {
        this.amount += amount;
    }

    // 重写equals方法，用于判断两个Money对象是否相同
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Money)) {
            return false;
        }
        Money other = (Money) obj;
        return id == other.id;
    }

    // 重写toString方法，用于输出Money对象的内容
    @Override
    public String toString() {
        return "Money [id=" + id + ", amount=" + amount + "]";
    }
}

