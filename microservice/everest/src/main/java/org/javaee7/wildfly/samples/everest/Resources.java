package org.javaee7.wildfly.samples.everest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.ZooKeeperServices;
import org.javaee7.wildfly.samples.services.discovery.ServiceDiscovery;

/**
 * @author arungupta
 */
@ApplicationScoped
public class Resources {
//    @Inject @FixedServices ServiceDiscovery services;
//    @Inject @SnoopServices ServiceDiscovery services;
    @Inject @ZooKeeperServices ServiceDiscovery services;

    @Produces
    public ServiceDiscovery getService() {
        return services;
    }
}
