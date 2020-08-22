package edu.youzg.balance;

import java.util.Objects;

/**
 * 网络节点 默认实现类
 */
public class DefaultNetNode implements INetNode {
    private String ip;  // 当前网络节点 的ip
    private int port;   // 当前网络节点 的port

    public DefaultNetNode() {
    }

    public DefaultNetNode(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultNetNode that = (DefaultNetNode) o;
        return port == that.port &&
                Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }

    @Override
    public String toString() {
        return "[" + this.ip + ":" + this.port + "]";
    }

}
