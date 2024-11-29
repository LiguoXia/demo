package com.liguo.demo.core.study.tree.calcscore.t;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;

public class TreeNode implements Iterable<TreeNode> {

    @Getter
    @Setter
    public String nodeCode;
    @Getter
    @Setter
    public TreeNode parent;

    public TreeNode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    @Override
    public Iterator<TreeNode> iterator() {
        return new TreeIterator(this);
    }

    public boolean isLeaf() {
        return getChildCount() == 0;
    }

    public int getChildCount() {
        // 根据您的具体实现，返回子节点的数量
        return 0;
    }

    public TreeNode getChildAt(int index) {
        // 根据您的具体实现，返回指定索引位置的子节点
        return null;
    }

    public static void main(String[] args) {
    }
}
