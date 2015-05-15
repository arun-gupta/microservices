package org.javaee7.wildfly.samples.everest.uzer;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author arungupta
 */
@Named
@SessionScoped
public class UzerBean implements Serializable {
    @PersistenceContext EntityManager em;
    
    @Inject UzerItem uzerItem;
    
    String status;
    
    @Transactional
    public void addUzer() {
        Uzer uzer = new Uzer();
        uzer.login = uzerItem.login;
        uzer.password = uzerItem.password;
        uzer.address1 = uzerItem.address1;
        uzer.address2 = uzerItem.address2;
        uzer.city = uzerItem.city;
        uzer.state = uzerItem.state;
        uzer.country = uzerItem.country;
        uzer.zip = uzerItem.zip;
        uzer.creditCard = uzerItem.creditcard;
        
        try {
            em.persist(uzer);
        } catch (Exception e) {
            status = e.getLocalizedMessage();
            return;
        }
        
        status = "User added successfully";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
