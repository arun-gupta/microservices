package org.javaee7.wildfly.samples.everest.catalog;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;
import org.javaee7.wildfly.samples.services.ZooKeeperServices;

/**
 * @author arungupta
 */
@Startup
@Singleton
public class CatalogService {
    @Inject @ZooKeeperServices ServiceRegistry services;
//    @Inject @FixedRegistry ServiceRegistry services;
//    @Inject @SnoopRegistry ServiceRegistry services;
    
    private static final String endpointURI = "http://localhost:8080/catalog/resources/catalog";
    private static final String serviceName = "catalog";
    
    @PostConstruct
    public void registerService() {
        services.registerService(serviceName, endpointURI);
    }
    
    @PreDestroy
    public void unregisterService() {
        services.unregisterService(serviceName, endpointURI);
    }
}
