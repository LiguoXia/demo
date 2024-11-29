package com.liguo.demo.core.study.算法题.链表;

import lombok.extern.slf4j.Slf4j;

/**
 * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度O(n)空间复杂度O(1)
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/13 10:28
 * @since 0.0.1
 */
@Slf4j
public class 链表内指定区间反转 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 输入：
     * {1, 2,3,4, 5},2,4
     * 返回值：
     * {1, 4,3,2, 5}
     *
     * @param head ListNode类
     * @param m    int整型
     * @param n    int整型
     * @return ListNode类
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode prev = null;

        ListNode reverse = null;

        ListNode curr = head;
        int nodeIndex = 0;
        while (curr != null) {
            nodeIndex++;
            ListNode next = curr.next;
            if (nodeIndex >= m) {
                // 断开pre

            }
            prev = curr;
            // 遍历条件
            curr = next;
        }

        return prev;
    }

    public static void main(String[] args) {

        ListNode newNode = reverseBetween(ListNode.buildTestNode(), 2, 4);
        while (newNode != null) {
            ListNode next = newNode.next;
            log.info("{}", newNode.val);
            newNode = next;
        }
    }
}
