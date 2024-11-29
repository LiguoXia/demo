package com.liguo.demo.core.study.tree.calcscore;

import java.util.Iterator;

/**
 * 迭代器
 *
 * @author 01395755[xialiguo]
 * @version 1.1
 * @date 2020/9/26 21:31
 * @since 1.1
 */
public class TreeNodeIterator1 implements Iterator<TreeNode> {
    enum ProcessStages {
        ProcessParent, ProcessChildCurNode, ProcessChildSubNode
    }

    private ProcessStages doNext;

    private TreeNode next;

    private Iterator<TreeNode> childrenCurNodeIter;

    private Iterator<TreeNode> childrenSubNodeIter;

    private TreeNode treeNode;

    public TreeNodeIterator1(TreeNode treeNode) {
        this.treeNode = treeNode;
        this.doNext = ProcessStages.ProcessParent;
        this.childrenCurNodeIter = treeNode.children.iterator();
    }

    @Override
    public boolean hasNext() {

        if (this.doNext == ProcessStages.ProcessParent) {
            this.next = this.treeNode;
            this.doNext = ProcessStages.ProcessChildCurNode;
            return true;
        }

        if (this.doNext == ProcessStages.ProcessChildCurNode) {
            if (childrenCurNodeIter.hasNext()) {
                TreeNode childDirect = childrenCurNodeIter.next();
                childrenSubNodeIter = childDirect.iterator();
                this.doNext = ProcessStages.ProcessChildSubNode;
                return hasNext();
            } else {
                this.doNext = null;
                return false;
            }
        }

        if (this.doNext == ProcessStages.ProcessChildSubNode) {
            if (childrenSubNodeIter.hasNext()) {
                this.next = childrenSubNodeIter.next();
                return true;
            } else {
                this.next = null;
                this.doNext = ProcessStages.ProcessChildCurNode;
                return hasNext();
            }
        }

        return false;
    }

    @Override
    @SuppressWarnings("all")
    public TreeNode next() {
        return this.next;
    }

    /**
     * 目前不支持删除节点
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
