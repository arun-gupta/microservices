package org.javaee7.wildfly.samples.everest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.ServiceRegistry;
import org.javaee7.wildfly.samples.services.ZooKeeperRegistry;

/**
 * @author arungupta
 */
@ApplicationScoped
public class ServiceDiscoveryZooKeeper implements ServiceDiscoveryURI {

    @Inject @ZooKeeperRegistry ServiceRegistry services;

    @Override
    public String getUserServiceURI() {
        return services.discoverService("user");
    }

    @Override
    public String getCatalogServiceURI() {
        return services.discoverService("catalog");
    }

    @Override
    public String getOrderServiceURI() {
        return services.discoverService("order");
    }
}
