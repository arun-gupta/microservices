package org.javaee7.wildfly.samples.everest.order;

import java.io.StringReader;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
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
@Stateless
@Path("order")
public class OrderREST {
    @PersistenceContext
    private EntityManager em;

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response create(String entity) {
        JsonObject jsonObject = Json.createReader(new StringReader(entity)).readObject();
        
        Order order = new Order();
//        order.setOrderId(jsonObject.getInt("orderId"));
        JsonArray orderItems = jsonObject.getJsonArray("orderItems");
        for (int i = 0; i< orderItems.size(); i++) {
            OrderItem orderItem = new OrderItem(orderItems.getJsonObject(i).getInt("itemId"), 
                    orderItems.getJsonObject(i).getInt("itemCount"));
            
            order.getOrderItems().add(orderItem);
        }
        
        em.persist(order);
        
        JsonObject response = Json.createObjectBuilder().add("orderId", order.getOrderId()).build();
        
        return Response.ok(response).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Order entity) {
        em.merge(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        em.remove(find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Order find(@PathParam("id") Integer id) {
        return em.createNamedQuery("Uzer.findById", Order.class).setParameter("id", id).getSingleResult();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Order> findAll() {
        return em.createNamedQuery("Uzer.findAll", Order.class).getResultList();
    }
}
