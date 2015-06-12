package org.javaee7.wildfly.samples.everest.uzer;

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
public class UserService {
    ZooKeeper zk;
    
    @PostConstruct
    public void registerService() {
        zk = ZooKeeper.getInstance("192.168.99.103", 2181);
        zk.registerService("user", "http://localhost:8080/user/resources/user");
    }
    
    @PreDestroy
    public void stopService() {
        zk.unregisterService("user", "http://localhost:8080/user/resources/user");
    }
}
