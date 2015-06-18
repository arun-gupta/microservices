package org.javaee7.wildfly.samples.services.zookeeper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.discovery.ServiceDiscovery;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;
import org.javaee7.wildfly.samples.services.ZooKeeperServices;

/**
 * @author arungupta
 */
@ZooKeeperServices
@ApplicationScoped
public class ZooKeeperServiceDiscovery extends ServiceDiscovery {

    @Inject
    @ZooKeeperServices
    ServiceRegistry services;

    @Override
    public String getUserServiceURI() {
        return services.discoverServiceURI("user");
    }

    @Override
    public String getCatalogServiceURI() {
        return services.discoverServiceURI("catalog");
    }

    @Override
    public String getOrderServiceURI() {
        return services.discoverServiceURI("order");
    }
}
