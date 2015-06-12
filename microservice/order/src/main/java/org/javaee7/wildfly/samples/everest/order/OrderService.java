package org.javaee7.wildfly.samples.everest.order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.javaee7.wildfly.samples.services.Services;

/**
 * @author arungupta
 */
@Startup
@Singleton
public class OrderService {
    Services service;
    
    @PostConstruct
    public void registerService() {
        service = Services.getInstance("192.168.99.103", 2181);
        service.registerService("order", "http://localhost:8080/order/resources/order");
    }
    
    @PreDestroy
    public void stopService() {
        service.unregisterService("order", "http://localhost:8080/order/resources/order");
    }
}
