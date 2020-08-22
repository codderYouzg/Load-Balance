package edu.youzg.balance;

import java.util.List;

/**
 * “轮询式” 均衡策略
 */
public class PollingBalance extends AbstractNetNodeBalance {
    private PollingNetNode headNode; // 头节点
    private PollingNetNode currentNode; // 当前应该遍历的节点(还未遍历)

    public PollingBalance() {
    }

    /**
     * 新增一个 网络节点
     * @param node 要新增的节点
     */
    @Override
    public synchronized void addNode(DefaultNetNode node) {
        PollingNetNode newNode = new PollingNetNode(node.getIp(), node.getPort());
        if (this.headNode == null) {
            this.headNode = newNode;
            this.currentNode = this.headNode;
        } else {
            newNode.setPre(this.headNode.getPre());
            newNode.setNext(this.headNode);
            this.headNode.setPre(newNode);
            newNode.getPre().setNext(newNode);
        }
        super.addNode(newNode);
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
     * 删除一个指定的节点
     * @param node 要删除的节点
     * @return 被删除的节点
     */
    @Override
    public synchronized INetNode removeNode(INetNode node) {
        PollingNetNode target = new PollingNetNode(node.getIp(), node.getPort());
        target = (PollingNetNode) super.removeNode(target);
        if (target == null) {
            return null;
        } else if (this.isNodePoolEmpty()) {
            this.headNode = null;
            this.currentNode = null;
            return this.headNode;
        } else {
            if (this.currentNode == target) {
                this.currentNode = target.getNext();
            }

            if (this.headNode == target) {
                this.headNode = target.getNext();
            }

            target.getPre().setNext(target.getNext());
            target.getNext().setPre(target.getPre());
            target.setPre(target);
            target.setNext(target);
            return target;
        }
    }

    /**
     * 按照 存储顺序，依次遍历获取 一个网络节点
     * @return 一个网络节点
     */
    @Override
    public synchronized DefaultNetNode getNode() {
        PollingNetNode resNode = this.currentNode;
        if (resNode!=null) {
            this.currentNode = this.currentNode.getNext();
        }
        return new DefaultNetNode(resNode.getIp(), resNode.getPort());
    }

}
