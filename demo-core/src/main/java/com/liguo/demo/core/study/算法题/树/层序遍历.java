package com.liguo.demo.core.study.算法题.树;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/16 9:02
 * @since 0.0.1
 */
@Slf4j
public class 层序遍历 {
    public static List<Integer> list1 = new ArrayList<>();
    public static List<Integer> list2 = new ArrayList<>();
    public static List<Integer> list3 = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> list = levelOrder(TwoTreeNode.buildTree());
        log.info("层序遍历:{}", list);

        preOrder(TwoTreeNode.buildTree());
        log.info("前序遍历:{}", list1);

        inOrder(TwoTreeNode.buildTree());
        log.info("中序遍历:{}", list2);

        postOrder(TwoTreeNode.buildTree());
        log.info("后序遍历:{}", list3);
    }

    /**
     * 层序遍历
     * 使用队列先进先出实现层序遍历，即广度优先遍历
     * <p>时间复杂度：O(n)</p>
     * <p>空间复杂度：O(n), 满二叉树时,遍历到最底层之前,队列中的数量: (n+1)/2 </p>
     *
     * @param root 二叉树跟节点
     * @return 层序遍历树节点值
     */
    public static List<Integer> levelOrder(TwoTreeNode root) {
        // 初始化队列，加入根节点
        Queue<TwoTreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 初始化一个列表，用于保存遍历序列
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TwoTreeNode node = queue.poll(); // 队列出队
            list.add(node.val);           // 保存节点值
            if (node.left != null)
                queue.offer(node.left);   // 左子节点入队
            if (node.right != null)
                queue.offer(node.right);  // 右子节点入队
        }
        return list;
    }

    /**
     * 前序、中序和后序遍历都属于「深度优先遍历 depth-first traversal」
     * 也称「深度优先搜索 depth-first search, DFS」，它体现了一种“先走到尽头，再回溯继续”的遍历方式。
     *
     * @param root 树节点
     * @return
     */
    public static void preOrder(TwoTreeNode root) {
        if (root == null)
            return;
        // 访问优先级：根节点 -> 左子树 -> 右子树
        list1.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(TwoTreeNode root) {
        if (root == null)
            return;
        // 访问优先级：左子树 -> 根节点 -> 右子树
        inOrder(root.left);
        list2.add(root.val);
        inOrder(root.right);
    }

    public static void postOrder(TwoTreeNode root) {
        if (root == null)
            return;
        // 访问优先级：左子树 -> 右子树 -> 根节点
        postOrder(root.left);
        postOrder(root.right);
        list3.add(root.val);
    }
}
