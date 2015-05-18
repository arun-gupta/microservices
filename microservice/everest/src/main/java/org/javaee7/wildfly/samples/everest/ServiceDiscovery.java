package org.javaee7.wildfly.samples.everest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * @author arungupta
 */
@ApplicationScoped
public class ServiceDiscovery {

    final String USER_SERVICE = "http://localhost:8080/user-1.0-SNAPSHOT/resources/user";
    final String CATALOG_SERVICE = "http://localhost:8080/catalog-1.0-SNAPSHOT/resources/catalog";
    final String ORDER_SERVICE = "http://localhost:8080/order-1.0-SNAPSHOT/resources/order";

    private WebTarget userService;
    private WebTarget catalogService;
    private WebTarget orderService;

    @PostConstruct
    public void init() {
        try {
            Client client = ClientBuilder.newClient();
            userService = client.target(URI.create(new URL(USER_SERVICE).toExternalForm()));
            
            catalogService = client.target(URI.create(new URL(CATALOG_SERVICE).toExternalForm()));
            
            orderService = client.target(URI.create(new URL(ORDER_SERVICE).toExternalForm()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServiceDiscovery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public WebTarget getUserService() {
        return userService;
    }

    public WebTarget getCatalogService() {
        return catalogService;
    }

    public WebTarget getOrderService() {
        return orderService;
    }
}
