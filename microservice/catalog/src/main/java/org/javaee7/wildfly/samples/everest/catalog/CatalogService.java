package org.javaee7.wildfly.samples.everest.catalog;

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
public class CatalogService {
    ZooKeeper zk;
    
    @PostConstruct
    public void registerService() {
        zk = ZooKeeper.getInstance("192.168.99.103", 2181);
        zk.registerService("catalog", "http://localhost:8080/catalog/resources/catalog");
    }
    
    @PreDestroy
    public void unregisterService() {
        zk.unregisterService("catalog", "http://localhost:8080/catalog/resources/catalog");
    }
}
