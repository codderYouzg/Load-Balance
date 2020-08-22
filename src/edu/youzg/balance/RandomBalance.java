package edu.youzg.balance;

import java.util.LinkedList;
import java.util.List;

/**
 * 随机实现 负载均衡
 */
public class RandomBalance extends AbstractNetNodeBalance {
    private final List<INetNode> nodeList = new LinkedList();

    public RandomBalance() {
    }

    /**
     * 增加一个 网络节点
     * @param node 要增加的网络节点
     */
    @Override
    public void addNode(DefaultNetNode node) {
        super.addNode(node);
        if (node!=null && !this.nodeList.contains(node)) {
            this.nodeList.add(node);
        }
    }

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
     * 删除一个 网络节点
     * @param node 要删除的网络节点
     * @return 被删除的网络节点
     */
    @Override
    public INetNode removeNode(INetNode node) {
        if (node != null && this.nodeList.contains(node)) {
            this.nodeList.remove(node);
            return super.removeNode(node);
        } else {
            return null;
        }
    }

    /**
     * 通过随机数，<br/>
     * 获取一个网络节点
     * @return 随机获取的网络节点
     */
    @Override
    public DefaultNetNode getNode() {
        if (this.isNodePoolEmpty()) {
            return null;
        } else {
            int index = (int) Math.random() * (nodeList.size() + 1);
            return (DefaultNetNode) this.nodeList.get(index);
        }
    }

}
