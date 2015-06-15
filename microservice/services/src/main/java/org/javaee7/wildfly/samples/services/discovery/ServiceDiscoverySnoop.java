package org.javaee7.wildfly.samples.services.discovery;

import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.javaee7.wildfly.samples.services.ServiceRegistry;
import org.javaee7.wildfly.samples.services.SnoopRegistry;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@SnoopRegistry
@ApplicationScoped
public class ServiceDiscoverySnoop implements ServiceDiscovery {

   @Inject
   @SnoopRegistry
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
