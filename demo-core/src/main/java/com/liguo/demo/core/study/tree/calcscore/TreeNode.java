package com.liguo.demo.core.study.tree.calcscore;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

/**
 * 树结构
 * <p>2.2 SP4删除当前节点及其所有子节点属性</p>
 *
 * @author 01395755[xialiguo]
 * @version 2.2 SP4
 * @date 2020/9/26 21:26
 * @since 1.1
 */
@Slf4j
public class TreeNode implements Iterable<TreeNode> {

    @Getter
    @Setter
    public String nodeCode;
    @Getter
    public BigDecimal weight;
    @Getter
    public BigDecimal score;
    @Getter
    public List<TreeNode> children;
    @Getter
    @Setter
    public TreeNode parent;


    public TreeNode(String nodeCode, BigDecimal weight) {
        this.nodeCode = nodeCode;
        this.weight = weight;
        this.score = BigDecimal.ZERO;
        this.children = new ArrayList<>();
    }

    /**
     * 判断是否为根：根没有父节点
     *
     * @return 布尔
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * 获取根节点信息
     *
     * @return 根节点信息
     */
    public TreeNode getRoot() {
        if (this.isRoot()) {
            return this;
        } else {
            return parent;
        }
    }

    /**
     * 判断是否为叶子节点：子节点没有子节点
     *
     * @return 布尔
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * 添加一个子节点
     *
     * @param childNode 子节点
     * @return
     */
    public TreeNode addChild(TreeNode childNode) {
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    /**
     * 获取当前节点的层
     *
     * @return 返回当前节点层数
     */
    public int getLevel() {
        if (this.isRoot()) {
            return 0;
        } else {
            return parent.getLevel() + 1;
        }
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<TreeNode> iterator() {
        TreeNodeIterator iterator = new TreeNodeIterator(this);
        return iterator;
    }

    /**
     * 获取当前节点所有兄弟节点,包括自己
     *
     * @return 当前节点所有兄弟节点, 包括自己
     */
    public List<TreeNode> getBrothers() {
        List<TreeNode> brothersNode = new ArrayList<>();
        if (this.parent == null) {
            return brothersNode;
        }
        brothersNode = this.parent.children;
        return brothersNode;
    }

    /**
     * 获取当前节点下所有叶子节点
     *
     * @return 当前节点下所有叶子节点
     */
    public List<TreeNode> getLeaves() {
        List<TreeNode> leavesNode = new ArrayList<>();
        for (TreeNode node : this) {
            if (node.isLeaf()) {
                leavesNode.add(node);
            }
        }
        return leavesNode;
    }


    private static TreeNode buildTreeFromTable(List<Model> modelList) {
        Map<Long, TreeNode> nodeMap = new HashMap<>();

        // 创建节点实例并将其存储在节点映射表中
        for (Model m : modelList) {
            String nodeCode = m.getNodeCode();
            BigDecimal weight = m.getWeight();
            TreeNode node = new TreeNode(nodeCode, weight);
            nodeMap.put(m.getId(), node);
        }

        // 构建树形结构
        TreeNode root = null;
        for (Model m : modelList) {
            //String nodeCode = m.getNodeCode();
            String parentCode = m.getParentCode();
            TreeNode currentNode = nodeMap.get(m.getId());
            if (parentCode == null) {
                root = currentNode;
            } else {
                TreeNode parentNode = nodeMap.get(m.getParentId());
                parentNode.addChild(currentNode);
            }
        }
        return root;
    }

    public static void calculateScoreAndPrint(TreeNode node) {

    }


    public static void main(String[] args) {
        // 模拟表数据集合
        List<Model> modelList = new ArrayList<>();
        Model row1 = new Model();
        row1.setId(1L);
        row1.setParentId(null);
        row1.setLevel(0);
        row1.setNodeCode("A");
        row1.setParentCode(null);
        row1.setWeight(BigDecimal.ONE);
        modelList.add(row1);

        Model row2 = new Model();
        row2.setLevel(1);
        row2.setId(2L);
        row2.setParentId(1L);
        row2.setNodeCode("b");
        row2.setParentCode("A");
        row2.setWeight(BigDecimal.ONE);
        modelList.add(row2);

        Model row3 = new Model();
        row3.setLevel(1);
        row3.setId(3L);
        row3.setParentId(1L);
        row3.setNodeCode("c");
        row3.setParentCode("A");
        row3.setWeight(BigDecimal.ONE);
        modelList.add(row3);

        Model row4 = new Model();
        row4.setLevel(2);
        row4.setId(4L);
        row4.setParentId(2L);
        row4.setNodeCode("d");
        row4.setParentCode("b");
        row4.setWeight(BigDecimal.ONE);
        modelList.add(row4);

        Model row5 = new Model();
        row5.setLevel(2);
        row5.setId(5L);
        row5.setParentId(2L);
        row5.setNodeCode("e");
        row5.setParentCode("b");
        row5.setWeight(BigDecimal.ONE);
        modelList.add(row5);

        Model row6 = new Model();
        row6.setLevel(2);
        row6.setId(6L);
        row6.setParentId(2L);
        row6.setNodeCode("f");
        row6.setParentCode("b");
        row6.setWeight(BigDecimal.ONE);
        modelList.add(row6);

        Model row7 = new Model();
        row7.setLevel(2);
        row7.setId(7L);
        row7.setParentId(3L);
        row7.setNodeCode("x");
        row7.setParentCode("c");
        row7.setWeight(BigDecimal.ONE);
        modelList.add(row7);

        Model row8 = new Model();
        row8.setLevel(2);
        row8.setId(8L);
        row8.setParentId(3L);
        row8.setNodeCode("y");
        row8.setParentCode("c");
        row8.setWeight(BigDecimal.ONE);
        modelList.add(row8);


        Model row9 = new Model();
        row9.setLevel(2);
        row9.setId(9L);
        row9.setParentId(3L);
        row9.setNodeCode("z");
        row9.setParentCode("c");
        row9.setWeight(BigDecimal.ONE);
        modelList.add(row9);

        Model row10 = new Model();
        row10.setLevel(2);
        row10.setId(10L);
        row10.setParentId(2L);
        row10.setNodeCode("z");
        row10.setParentCode("b");
        row10.setWeight(BigDecimal.ONE);
        modelList.add(row10);
        // 构建树模型
        TreeNode root = buildTreeFromTable(modelList);
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        int maxLevel = 0;
        for (TreeNode node : root) {
            log.info("节点:{},层级:{},记录:{},父节编码:{},子节点:{}", node.getNodeCode(), node.getLevel(), node.getNodeCode(), node.isRoot() ? "" : node.getParent().getNodeCode(), node.getChildren());
            if (CollectionUtil.isEmpty(map.get(node.getLevel()))) {
                map.put(node.getLevel(), new ArrayList<>(Arrays.asList(node)));
            } else {
                map.get(node.getLevel()).add(node);
            }
            maxLevel = node.getLevel() > maxLevel ? node.getLevel() : maxLevel;
        }
        log.info("maxLevel:{}", maxLevel);
        for (int i = maxLevel; i >= 0; i--) {
            List<TreeNode> list = map.get(i);
            log.info("层级:{}", i);
            for (TreeNode node : list) {
                log.info("node：{}", node.getNodeCode());
            }
        }
    }
}
