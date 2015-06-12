package org.javaee7.wildfly.samples.everest.uzer;

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
public class UserService {
    Services service;
    
    @PostConstruct
    public void registerService() {
        service = Services.getInstance("192.168.99.103", 2181);
        service.registerService("user", "http://localhost:8080/user/resources/user");
    }
    
    @PreDestroy
    public void stopService() {
        service.unregisterService("user", "http://localhost:8080/user/resources/user");
    }
}
