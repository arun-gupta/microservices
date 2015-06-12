package org.javaee7.wildfly.samples.everest.order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.javaee7.wildfly.samples.zookeeper.ZooKeeper;

/**
 * @author arungupta
 */
@Startup
@Singleton
public class OrderService {
    ZooKeeper zk;
    
    @PostConstruct
    public void registerService() {
        zk = ZooKeeper.getInstance("192.168.99.103", 2181);
        zk.registerService("order", "http://localhost:8080/order/resources/order");
    }
    
    @PreDestroy
    public void stopService() {
        zk.unregisterService("order", "http://localhost:8080/order/resources/order");
    }
}
