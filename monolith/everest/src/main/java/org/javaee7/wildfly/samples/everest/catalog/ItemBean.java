package org.javaee7.wildfly.samples.everest.catalog;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author arungupta
 */
@Named
@SessionScoped
public class ItemBean implements Serializable {
    @PersistenceContext EntityManager em;
    
    public List<Item> getItems() {
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
    }
    
    public Item getItem() {
        int itemId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemId"));
        return em.createNamedQuery("Item.findById", Item.class).setParameter("id", itemId).getSingleResult();
    }
    
    public Item getItemWithId(int itemId) {
        return em.createNamedQuery("Item.findById", Item.class).setParameter("id", itemId).getSingleResult();
    }
}
