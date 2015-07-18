package org.javaee7.wildfly.samples.everest.uzer;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.javaee7.wildfly.samples.services.discovery.ServiceDiscovery;

/**
 * @author arungupta
 */
@Named
@SessionScoped
public class UzerBean implements Serializable {
    @Inject UzerItem uzerItem;
    
    @Inject ServiceDiscovery services;
    
    String status;
    
    public void addUzer() {
        
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("login", uzerItem.getLogin())
                .add("password", uzerItem.getPassword())
                .add("username", uzerItem.getUsername())
                .add("address1", uzerItem.getAddress1())
                .add("address2", uzerItem.getAddress2())
                .add("city", uzerItem.getCity())
                .add("state", uzerItem.getState())
                .add("country", uzerItem.getCountry())
                .add("zip", uzerItem.getZip())
                .add("creditcard", uzerItem.getCreditcard())
                .build();
        StringWriter w = new StringWriter();
        try (JsonWriter writer = Json.createWriter(w)) {
            writer.write(jsonObject);
        }
        
        Response response = services.getUserService().request().post(Entity.xml(w.toString()));

        Response.StatusType statusInfo = response.getStatusInfo();
        
        if (statusInfo.getFamily() == Response.Status.Family.SUCCESSFUL) {
            JsonObject jsonResponse = Json.createReader(new StringReader(response.readEntity(String.class))).readObject();
            status = "User added successfully," +
                    " login: " + jsonResponse.get("login") +
                    " password: " + jsonResponse.get("password");
        } else
            status = statusInfo.getReasonPhrase();
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
