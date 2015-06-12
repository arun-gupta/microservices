package org.javaee7.wildfly.samples.everest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Vetoed;

/**
 * @author arungupta
 */
@Vetoed
@ApplicationScoped
public class ServiceDiscoveryStatic implements ServiceDiscoveryURI {

    private final String USER_SERVICE = "http://localhost:8080/user/resources/user";
    private final String CATALOG_SERVICE = "http://localhost:8080/catalog/resources/catalog";
    private final String ORDER_SERVICE = "http://localhost:8080/order/resources/order";

    @Override
    public String getUserServiceURI() {
        return USER_SERVICE;
    }

    @Override
    public String getCatalogServiceURI() {
        return CATALOG_SERVICE;
    }

    @Override
    public String getOrderServiceURI() {
        return ORDER_SERVICE;
    }
}
