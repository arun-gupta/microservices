package org.javaee7.wildfly.samples.everest.catalog;

import java.io.Serializable;
import java.io.StringReader;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.javaee7.wildfly.samples.services.discovery.ServiceDiscovery;

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

    public String catalogServiceEndpoint() {
        String itemId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemId");

        String response = services.getCatalogService().path(itemId).request().get(String.class);
        
        JsonObject jsonObject = Json.createReader(new StringReader(response)).readObject();
        catalogItem.setId(jsonObject.getInt("id"));
        catalogItem.setName(jsonObject.getString("name"));
        catalogItem.setDescription(jsonObject.getString("description"));
        
        return response;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
