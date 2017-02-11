package org.javaee7.wildfly.samples.everest.uzer;

import java.io.StringReader;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
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
import javax.ws.rs.core.Response;

/**
 * @author arungupta
 */
@Path("user")
@RequestScoped
public class UserREST {
    @PersistenceContext(unitName = "userPU")
    private EntityManager em;

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response create(String entity) {
        JsonObject jsonObject = Json.createReader(new StringReader(entity)).readObject();

        Uzer uzer = new Uzer();
        uzer.setLogin(jsonObject.getString("login"));
        uzer.setPassword(jsonObject.getString("password"));
        uzer.setUsername(jsonObject.getString("username"));
        uzer.setAddress1(jsonObject.getString("address1"));
        uzer.setAddress2(jsonObject.getString("address2"));
        uzer.setCity(jsonObject.getString("city"));
        uzer.setState(jsonObject.getString("state"));
        uzer.setCountry(jsonObject.getString("country"));
        uzer.setZip(jsonObject.getString("zip"));
        uzer.setCreditcard(jsonObject.getString("creditcard"));

        em.getTransaction().begin();

        em.persist(uzer);

        em.getTransaction().commit();

        return Response.ok(entity).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Uzer entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        em.getTransaction().begin();
        em.remove(find(id));
        em.getTransaction().commit();
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Uzer find(@PathParam("id") Integer id) {
        return em.createNamedQuery("Uzer.findById", Uzer.class).setParameter("id", id).getSingleResult();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Uzer> findAll() {
        return em.createNamedQuery("Uzer.findAll", Uzer.class).getResultList();
    }
}
