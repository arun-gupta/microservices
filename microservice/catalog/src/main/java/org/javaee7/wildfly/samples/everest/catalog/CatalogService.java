package org.javaee7.wildfly.samples.everest.catalog;

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
public class CatalogService {
    @Inject @ZooKeeperRegistry ServiceRegistry services;
    
    @PostConstruct
    public void registerService() {
        services.registerService("catalog", "http://localhost:8080/catalog/resources/catalog");
    }
    
    @PreDestroy
    public void unregisterService() {
        services.unregisterService("catalog", "http://localhost:8080/catalog/resources/catalog");
    }
}
