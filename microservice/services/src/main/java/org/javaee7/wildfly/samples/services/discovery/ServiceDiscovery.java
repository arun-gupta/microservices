package org.javaee7.wildfly.samples.services.discovery;

import javax.ws.rs.client.WebTarget;

/**
 * @author arungupta
 */
public interface ServiceDiscovery {
    public WebTarget getUserService();

    public WebTarget getCatalogService();

    public WebTarget getOrderService();
    
}
