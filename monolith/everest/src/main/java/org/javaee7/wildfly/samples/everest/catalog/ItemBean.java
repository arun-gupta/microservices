package org.javaee7.wildfly.samples.everest.catalog;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author arungupta
 */
@Named
@Stateless
public class ItemBean implements Serializable {
    @PersistenceContext EntityManager em;
    
    public List<Item> getItems() {
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
    }
}
