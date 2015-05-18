package org.javaee7.wildfly.samples.everest.uzer;

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
public class UzerBean implements Serializable {
    @Inject UzerItem uzerItem;
    
    @Inject ServiceDiscovery services;
    
    String status;
    
    public void addUzer() {
        UzerItem2 uzer2 = new UzerItem2();
        uzer2.setLogin(uzerItem.getLogin());
        uzer2.setPassword(uzerItem.getPassword());
        uzer2.setUsername(uzerItem.getUsername());
        uzer2.setPassword(uzerItem.getPassword());
        uzer2.setAddress1(uzerItem.getAddress1());
        uzer2.setAddress2(uzerItem.getAddress2());
        uzer2.setCity(uzerItem.getCity());
        uzer2.setState(uzerItem.getState());
        uzer2.setCountry(uzerItem.getCountry());
        uzer2.setZip(uzerItem.getZip());
        uzer2.setCreditcard(uzerItem.getCreditcard());
        
        Response response = services.getUserService().register(UzerItem2.class).request().post(Entity.xml(uzer2));

        Response.StatusType statusInfo = response.getStatusInfo();
        
        System.out.println("code: " + statusInfo.getStatusCode());
        if (statusInfo.getStatusCode() == Response.Status.CREATED.getStatusCode())
            status = "User added successfully";
        else
            status = statusInfo.getReasonPhrase();
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
