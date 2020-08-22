package edu.youzg.balance;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 负载均衡 基本功能 基本实现类<br/>
 * 设置了一个 以当前网络节点的hashcode为键，以当前节点的信息为值 的map
 */
public abstract class AbstractNetNodeBalance implements INetNodeBalance {
    protected final Map<Integer, INetNode> nodePool;

    public AbstractNetNodeBalance() {
        this.nodePool = new ConcurrentHashMap<>();
    }

    /**
     * 向nodePool中 新增一个 网络节点
     * @param node 目标 网络节点
     */
    @Override
    public void addNode(DefaultNetNode node) {
        if (node == null) {
            return;
        }

        int nodeHashCode = node.hashCode();
        if (nodePool.containsKey(nodeHashCode)) {
            return;
        }

        nodePool.put(nodeHashCode, node);
    }

    /**
     * 向nodePool中 新增一个 网络节点列表
     * @param nodeList 目标 网络节点列表
     */
    @Override
    public void addNodeList(List<DefaultNetNode> nodeList) {
        if (nodeList == null) {
            return;
        }
        for (DefaultNetNode node : nodeList) {
            addNode(node);
        }
    }

    /**
     * 判断nodepool是否为空
     * @return
     */
    public boolean isNodePoolEmpty() {
        return nodePool.isEmpty();
    }

    /**
     * 删除指定的网络节点
     * @param node
     * @return
     */
    @Override
    public INetNode removeNode(INetNode node) {
        if (node == null) {
            return null;
        }
        return nodePool.remove(node.hashCode());
    }

}
