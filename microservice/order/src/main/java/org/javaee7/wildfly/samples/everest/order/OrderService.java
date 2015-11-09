package org.javaee7.wildfly.samples.everest.order;

import org.javaee7.wildfly.samples.services.ZooKeeperServices;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author arungupta
 */
@ApplicationScoped
public class OrderService {
//    @Inject @FixedServices ServiceRegistry services;
//    @Inject @SnoopServices ServiceRegistry services;

    @Inject
    @ZooKeeperServices
    ServiceRegistry services;
    
    private static final String endpointURI = "http://localhost:8081/resources/order";
//    private final String endpointURI = "http://" + serverName + ":" + serverPort + "/order/resources/order";
//    private final String endpointURI = "http://" + WildFlyUtil.getHostName()+ ":" + WildFlyUtil.getHostPort() + "/order/resources/order";
    private static final String serviceName = "order";
//
    @PostConstruct
    public void registerService() {
        services.registerService(serviceName, endpointURI);
    }

    @PreDestroy
    public void unregisterService() {
        services.unregisterService(serviceName, endpointURI);
    }
}
