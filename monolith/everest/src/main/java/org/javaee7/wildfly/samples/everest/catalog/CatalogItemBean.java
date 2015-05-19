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
public class CatalogItemBean implements Serializable {
    @PersistenceContext EntityManager em;
    
    public List<CatalogItem> getItems() {
        return em.createNamedQuery("CatalogItem.findAll", CatalogItem.class).getResultList();
    }
    
    public CatalogItem getItem() {
        int itemId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemId"));
        return getItemWithId(itemId);
    }
    
    public CatalogItem getItemWithId(int itemId) {
        return em.createNamedQuery("CatalogItem.findById", CatalogItem.class).setParameter("id", itemId).getSingleResult();
    }
}
