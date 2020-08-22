package edu.youzg.balance;

import java.util.List;

/**
 * 负载均衡 功能接口<br/>
 * 用于 增、删、获取 网络节点
 */
public interface INetNodeBalance {
    void addNode(DefaultNetNode node);
    void addNodeList(List<DefaultNetNode> nodeList);
    INetNode removeNode(INetNode node);
    DefaultNetNode getNode();
}
