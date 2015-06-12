package org.javaee7.wildfly.samples.everest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.javaee7.wildfly.samples.services.Services;

/**
 * @author arungupta
 */
@ApplicationScoped
public class ServiceDiscoveryZooKeeper implements ServiceDiscoveryURI {

    Services service;

    @PostConstruct
    public void init() {
        service = Services.getInstance("192.168.99.103", 2181);
    }

    @Override
    public String getUserServiceURI() {
        return service.discoverService("user");
    }

    @Override
    public String getCatalogServiceURI() {
        return service.discoverService("catalog");
    }

    @Override
    public String getOrderServiceURI() {
        return service.discoverService("order");
    }
}
