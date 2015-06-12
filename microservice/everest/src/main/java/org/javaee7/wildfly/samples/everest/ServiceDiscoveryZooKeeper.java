package org.javaee7.wildfly.samples.everest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.javaee7.wildfly.samples.zookeeper.ZooKeeper;

/**
 * @author arungupta
 */
@ApplicationScoped
public class ServiceDiscoveryZooKeeper implements ServiceDiscoveryURI {

    ZooKeeper zk;

    @PostConstruct
    public void init() {
        zk = ZooKeeper.getInstance("192.168.99.103", 2181);
    }

    @Override
    public String getUserServiceURI() {
        return zk.discoverService("user");
    }

    @Override
    public String getCatalogServiceURI() {
        return zk.discoverService("catalog");
    }

    @Override
    public String getOrderServiceURI() {
        return zk.discoverService("order");
    }
}
