package org.javaee7.wildfly.samples.services.discovery;

import java.net.URI;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * @author arungupta
 */
public abstract class ServiceDiscovery {

    private WebTarget userService;
    private WebTarget catalogService;
    private WebTarget orderService;

    public abstract String getUserServiceURI();

    public abstract String getCatalogServiceURI();

    public abstract String getOrderServiceURI();

    public WebTarget getUserService() {
        if (null == userService) {
            userService = ClientBuilder
                    .newClient()
                    .target(URI.create(getUserServiceURI()));
        }

        return userService;
    }

    public WebTarget getCatalogService() {
        if (null == catalogService) {
            catalogService = ClientBuilder
                    .newClient()
                    .target(URI.create(getCatalogServiceURI()));

        }
        
        return catalogService;
    }

    public WebTarget getOrderService() {
        if (null == orderService) {
            orderService = ClientBuilder
                    .newClient()
                    .target(URI.create(getOrderServiceURI()));

        }

        return orderService;
    }

}
