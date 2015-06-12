package org.javaee7.wildfly.samples.everest.catalog;

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
public class CatalogService {
    Services service;
    
    @PostConstruct
    public void registerService() {
        service = Services.getInstance("192.168.99.103", 2181);
        service.registerService("catalog", "http://localhost:8080/catalog/resources/catalog");
    }
    
    @PreDestroy
    public void stopService() {
        service.registerService("catalog", "http://localhost:8080/catalog/resources/catalog");
    }
}
