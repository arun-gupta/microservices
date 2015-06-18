package org.javaee7.wildfly.samples.services.fixed;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.discovery.ServiceDiscovery;
import org.javaee7.wildfly.samples.services.FixedServices;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;

/**
 * @author arungupta
 */
@FixedServices
@ApplicationScoped
public class FixedServiceDiscovery extends ServiceDiscovery {

    @Inject
    @FixedServices
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
