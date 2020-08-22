package edu.youzg.balance;

/**
 * 网络节点 基本功能接口
 */
public interface INetNode {
    String getIp();
    int getPort();
    void setIp(String ip);
    void setPort(int port);
}
