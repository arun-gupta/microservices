package org.javaee7.wildfly.samples.everest.catalog;

import org.javaee7.wildfly.samples.services.ZooKeeperServices;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author arungupta
 */
@ApplicationScoped
public class CatalogService {
//    @Inject @FixedServices ServiceRegistry services;
//    @Inject @SnoopServices ServiceRegistry services;

    @Inject
    @ZooKeeperServices
    ServiceRegistry services;

    private static final String endpointURI = "http://localhost:8080/resources/catalog";
//    private final String endpointURI = "http://" + serverName + ":" + serverPort + "/catalog/resources/catalog";
//    private final String endpointURI = "http://" + WildFlyUtil.getHostName() + ":" + WildFlyUtil.getHostPort() + "/catalog/resources/catalog";
    private static final String serviceName = "catalog";

    @PostConstruct
    public void registerService() {
        services.registerService(serviceName, endpointURI);
    }

    @PreDestroy
    public void unregisterService() {
        services.unregisterService(serviceName, endpointURI);
    }

    public void doNothing() {}
}
