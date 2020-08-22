package edu.youzg.balance;

/**
 * 用于“轮询” 的 双向链表式 网络节点
 */
public class PollingNetNode extends DefaultNetNode {
    private PollingNetNode next = this;
    private PollingNetNode pre = this;

    public PollingNetNode() {
    }

    public PollingNetNode(String ip, int port) {
        super(ip, port);
    }

    public void setNext(PollingNetNode next) {
        this.next = next;
    }

    public void setPre(PollingNetNode pre) {
        this.pre = pre;
    }

    public PollingNetNode getNext() {
        return next;
    }

    public PollingNetNode getPre() {
        return pre;
    }

}
