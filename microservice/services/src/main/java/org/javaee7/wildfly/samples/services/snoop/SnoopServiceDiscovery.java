package org.javaee7.wildfly.samples.services.snoop;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.discovery.ServiceDiscovery;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;
import org.javaee7.wildfly.samples.services.SnoopServices;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@SnoopServices
@ApplicationScoped
public class SnoopServiceDiscovery extends ServiceDiscovery {

   @Inject
   @SnoopServices
   private ServiceRegistry services;

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
