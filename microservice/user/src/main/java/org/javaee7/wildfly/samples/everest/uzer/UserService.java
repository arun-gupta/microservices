package org.javaee7.wildfly.samples.everest.uzer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.ServiceRegistry;
import org.javaee7.wildfly.samples.services.ZooKeeperRegistry;

/**
 * @author arungupta
 */
@Startup
@Singleton
public class UserService {
    @Inject @ZooKeeperRegistry ServiceRegistry service;
    
    @PostConstruct
    public void registerService() {
        service.registerService("user", "http://localhost:8080/user/resources/user");
    }
    
    @PreDestroy
    public void stopService() {
        service.unregisterService("user", "http://localhost:8080/user/resources/user");
    }
}
