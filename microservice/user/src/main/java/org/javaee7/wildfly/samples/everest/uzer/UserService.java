package org.javaee7.wildfly.samples.everest.uzer;

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
public class UserService {
//        @Inject @FixedServices ServiceRegistry services;
//    @Inject @SnoopServices ServiceRegistry services;

    @Inject
    @ZooKeeperServices
    ServiceRegistry services;
//
    private static final String endpointURI = "http://localhost:8082/resources/user";
////    private final String endpointURI = "http://" + serverName + ":" + serverPort + "/user/resources/user";
//    private final String endpointURI = "http://" + WildFlyUtil.getHostName()+ ":" + WildFlyUtil.getHostPort() +
// "/user/resources/user";
//
    private static final String serviceName = "user";

    @PostConstruct
    public void registerService() {
        services.registerService(serviceName, endpointURI);
    }

    @PreDestroy
    public void unregisterService() {
        services.unregisterService(serviceName, endpointURI);
    }
}
