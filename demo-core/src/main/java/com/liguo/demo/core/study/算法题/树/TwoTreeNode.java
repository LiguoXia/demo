package com.liguo.demo.core.study.算法题.树;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/16 8:59
 * @since 0.0.1
 */
public class TwoTreeNode {
    public int val;         // 节点值
    public TwoTreeNode left;   // 左子节点引用
    public TwoTreeNode right;  // 右子节点引用

    public TwoTreeNode(int x) {
        val = x;
    }

    public static TwoTreeNode buildTree() {
        TwoTreeNode t1 = new TwoTreeNode(1);
        TwoTreeNode t2 = new TwoTreeNode(2);
        TwoTreeNode t3 = new TwoTreeNode(3);
        TwoTreeNode t4 = new TwoTreeNode(4);
        TwoTreeNode t5 = new TwoTreeNode(5);
        TwoTreeNode t6 = new TwoTreeNode(6);
        TwoTreeNode t7 = new TwoTreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        return t1;
    }
}
