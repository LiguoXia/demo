package com.liguo.demo.core.study.算法题.链表;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
 *
 * 数据范围：
 *
 * 0≤n≤1000
 * 要求：空间复杂度
 * O(1) ，时间复杂度
 * O(n) 。
 * 如当输入链表{1,2,3}时，
 * 经反转后，原链表变为{3,2,1}，所以对应的输出为{3,2,1}。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/3 20:03
 * @since 0.0.1
 */
@Slf4j
public class 反转链表 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public static ListNode reverseList(ListNode head) {
        // write code here
        Stack<ListNode> stack = new Stack<>();
        //把链表节点全部摘掉放到栈中
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty())
            return null;
        ListNode node = stack.pop();
        ListNode dummy = node;
        //栈中的结点全部出栈，然后重新连成一个新的链表
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        //最后一个结点就是反转前的头结点，一定要让他的next
        //等于空，否则会构成环
        node.next = null;
        return dummy;
    }

    /**
     * 指针方式实现，不开辟新的内存空间
     *
     * @param head 链表
     * @return 反转后链表
     */
    public static ListNode reverseList1(ListNode head) {
        // 空链表 previous上一个
        ListNode prev = null;
        // 当前遍历指针 currentsms
        ListNode curr = head;
        // 遍历指针下一节点有数据就一直遍历
        while (curr != null) {
            // 获取当前节点下一节点
            ListNode next = curr.next;
            // 断开当前节点下一节点，指向上一个链表()
            curr.next = prev;
            // 当前节点新链表给上一个链表
            prev = curr;
            // 当前指针后移
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode node1 = ListNode.buildTestNode();
        ListNode newNode = reverseList(node1);
        while (newNode != null) {
            ListNode next = newNode.next;
            log.info("{}", newNode.val);
            newNode = next;
        }
    }
}

