package org.javaee7.wildfly.samples.everest.uzer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.FixedServices;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;

/**
 * @author arungupta
 */
@Startup
@Singleton
public class UserService {
//    @Inject @ZooKeeperServices ServiceRegistry services;
    @Inject @FixedServices ServiceRegistry services;
//    @Inject @SnoopServices ServiceRegistry services;

    private static final String endpointURI = "http://localhost:8080/user/resources/user";
    private static final String serviceName = "user";
    
    @PostConstruct
    public void registerService() {
        services.registerService(serviceName, endpointURI);
    }
    
    @PreDestroy
    public void unregisterService() {
        services.unregisterService(serviceName, endpointURI);
    }
}
