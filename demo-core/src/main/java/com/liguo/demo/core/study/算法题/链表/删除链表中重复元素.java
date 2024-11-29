package com.liguo.demo.core.study.算法题.链表;

import lombok.extern.slf4j.Slf4j;

/**
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/14 18:45
 * @since 0.0.1
 */
@Slf4j
public class 删除链表中重复元素 {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return head;
        }
        // 前一个node指针，默认指向当前节点
        ListNode pre = null;
        // 需要比较的node值
        int preCompareVal = 0;
        // 当前节点
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            if (pre == null) {
                preCompareVal = curr.val;
                pre = curr;
                curr = next;
                continue;
            }
            if(preCompareVal == curr.val) {
                // 当前节点下一个节点指向下下个节点
                pre.next = next;
            } else {
                pre = curr;
                preCompareVal = curr.val;
            }
            curr = next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = ListNode.buildSortWithDuplicationNode();
        listNode = deleteDuplicates(listNode);
        while (listNode != null) {
            ListNode next = listNode.next;
            log.info("{}", listNode.val);
            listNode = next;
        }
    }
}
