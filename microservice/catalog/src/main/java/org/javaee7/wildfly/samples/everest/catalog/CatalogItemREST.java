package org.javaee7.wildfly.samples.everest.catalog;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @author arungupta
 */
@Stateless
@Path("catalog")
public class CatalogItemREST {
    @PersistenceContext
    private EntityManager em;
    
    @Context UriInfo uriInfo;
    
    @PostConstruct
    public void init() {
        System.out.println("base URI: " + uriInfo.getBaseUri());
        System.out.println("request URI: " + uriInfo.getRequestUri());
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(CatalogItem entity) {
        em.persist(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, CatalogItem entity) {
        em.merge(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        em.remove(find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml; qs=0.50", "application/json"})
    public CatalogItem find(@PathParam("id") Integer id) {
        return em.createNamedQuery("Item.findById", CatalogItem.class).setParameter("id", id).getSingleResult();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<CatalogItem> findAll() {
        return em.createNamedQuery("Item.findAll", CatalogItem.class).getResultList();
    }
}
