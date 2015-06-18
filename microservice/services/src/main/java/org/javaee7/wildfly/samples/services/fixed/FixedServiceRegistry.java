package org.javaee7.wildfly.samples.services.fixed;

import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.FixedServices;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;

/**
 * @author arungupta
 */
@FixedServices
@ApplicationScoped
public class FixedServiceRegistry implements ServiceRegistry {
    
    ConcurrentHashMap<String, String> serviceNameToURI = new ConcurrentHashMap<>();

    @Inject
    public FixedServiceRegistry() {
    }
    
    @PostConstruct
    public void init() {
        serviceNameToURI.put("catalog", "http://localhost:8080/catalog/resources/catalog");
        serviceNameToURI.put("user", "http://localhost:8080/user/resources/user");
        serviceNameToURI.put("order", "http://localhost:8080/order/resources/order");
    }

    @Override
    public void registerService(String name, String uri) {
        // Workaround until https://github.com/arun-gupta/microservices/issues/31 is fixed
    }

    @Override
    public void unregisterService(String name, String uri) {
//        if (serviceNameToURI.values().contains(name)) {
//            serviceNameToURI.remove(name);
//        }
    }

    @Override
    public String discoverServiceURI(String name) {
        if (serviceNameToURI.keySet().contains(name)) {
            return serviceNameToURI.get(name);
        }
        
        throw new RuntimeException("Service " + name + " not found");
    }
}
