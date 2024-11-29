package com.liguo.demo.core.study.算法题;

import com.liguo.demo.core.study.算法题.数据结构.ListNode;
import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/12 8:43
 * @since 0.0.1
 */
@Slf4j
public class AA {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        ListNode listNode1 = new ListNode(3);
        log.info("hash码:{}", listNode);
        log.info("hash码:{}", listNode1);
        log.info("ListNode Class实例:{}", ListNode.class);

        Object obj = new Object();

        // 获取对象的哈希码（通常基于内存地址）
        int hashCode = System.identityHashCode(obj);
        System.out.println("Object Address: " + obj);
        System.out.println("Object Address: 0x" + hashCode);
        System.out.println("Object Address: 0x" + Integer.toHexString(hashCode));
    }
}
