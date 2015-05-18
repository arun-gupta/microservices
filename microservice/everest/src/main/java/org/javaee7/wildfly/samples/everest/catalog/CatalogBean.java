package org.javaee7.wildfly.samples.everest.catalog;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.javaee7.wildfly.samples.everest.ServiceDiscovery;

/**
 * @author arungupta
 */
@Named
@SessionScoped
public class CatalogBean implements Serializable {
    @Inject CatalogItem catalogItem;
    
    @Inject ServiceDiscovery services;
    
    String status;
    
    public void addCatalog() {
        Response response = services.getCatalogService().request().post(Entity.xml(catalogItem));

        Response.StatusType statusInfo = response.getStatusInfo();
        
        if (statusInfo.getStatusCode() == Response.Status.CREATED.getStatusCode())
            status = "User added successfully";
        else
            status = statusInfo.getReasonPhrase();
    }
    
    public CatalogItem[] getAllItems() {
        return services.getCatalogService().register(CatalogItem.class).request().get(CatalogItem[].class);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
