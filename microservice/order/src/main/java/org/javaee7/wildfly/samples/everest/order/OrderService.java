package org.javaee7.wildfly.samples.everest.order;

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
public class OrderService {
    @Inject @ZooKeeperRegistry ServiceRegistry services;
    
    @PostConstruct
    public void registerService() {
        services.registerService("order", "http://localhost:8080/order/resources/order");
    }
    
    @PreDestroy
    public void stopService() {
        services.unregisterService("order", "http://localhost:8080/order/resources/order");
    }
}
