package com.liguo.demo.core.study.tree.calcscore.t;

import java.util.Iterator;
import java.util.Stack;

public class TreeIterator implements Iterator<TreeNode> {

    private Stack<TreeNode> stack;

    public TreeIterator(TreeNode rootNode) {
        stack = new Stack<>();
        stack.push(rootNode);
        initializeStack();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public TreeNode next() {
        TreeNode node = stack.pop();
        initializeStack();
        return node;
    }

    private void initializeStack() {
        while (!stack.isEmpty() && !stack.peek().isLeaf()) {
            TreeNode node = stack.pop();
            if (node.getParent() != null) {
                stack.push(node.getParent());
            }
        }
    }
}
