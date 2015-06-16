    package org.javaee7.wildfly.samples.services.snoop;

import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopDiscoveryClient;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;
import org.javaee7.wildfly.samples.services.SnoopServices;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@SnoopServices
@ApplicationScoped
public class SnoopServiceRegistry implements ServiceRegistry {

   private static final Logger LOGGER = Logger.getLogger(SnoopServiceRegistry.class.getName());
   
   @Inject
   @Snoop(applicationName = "user")
   private SnoopDiscoveryClient userService;

   @Inject
   @Snoop(applicationName = "catalog")
   private SnoopDiscoveryClient catalogService;

   @Inject
   @Snoop(applicationName = "order")
   private SnoopDiscoveryClient orderService;

   private final Map<String, WebTarget> services = new HashMap<>();

   @Override
   public void registerService(String name, String uri) {
      // noop, handled by snoop
   }

   @Override
   public void unregisterService(String name, String uri) {
      // noop, handled by snoop
   }

   @Override
   public String discoverService(String name) {
      final String endpoint = Optional.ofNullable(services.get(name))
              .orElseThrow(RuntimeException::new)
              .getUri()
              .toString();
      
      LOGGER.config(() -> "Returning service endpoint for '" + name + "': " + endpoint);
      return endpoint;
   }

   @PostConstruct
   public void init() {
      services.put("user", userService.getServiceRoot());
      services.put("catalog", catalogService.getServiceRoot());
      services.put("order", orderService.getServiceRoot());
   }
}
