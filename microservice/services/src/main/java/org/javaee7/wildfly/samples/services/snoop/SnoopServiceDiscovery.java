package org.javaee7.wildfly.samples.services.snoop;

import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.javaee7.wildfly.samples.services.discovery.ServiceDiscovery;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;
import org.javaee7.wildfly.samples.services.SnoopServices;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@SnoopServices
@ApplicationScoped
public class SnoopServiceDiscovery implements ServiceDiscovery {

   @Inject
   @SnoopServices
   private ServiceRegistry services;

   private WebTarget userService;
   private WebTarget catalogService;
   private WebTarget orderService;

   @Override
   public WebTarget getUserService() {
      if (null == userService) {
         userService = ClientBuilder
                 .newClient()
                 .target(URI.create(services.discoverService("user")));
      }

      return userService;
   }

   @Override
   public WebTarget getCatalogService() {
      if (null == catalogService) {
         catalogService = ClientBuilder
                 .newClient()
                 .target(URI.create(services.discoverService("catalog")));

      }
      return catalogService;
   }

   @Override
   public WebTarget getOrderService() {
      if (null == orderService) {
         orderService = ClientBuilder
                 .newClient()
                 .target(URI.create(services.discoverService("order")));

      }

      return orderService;
   }
}
