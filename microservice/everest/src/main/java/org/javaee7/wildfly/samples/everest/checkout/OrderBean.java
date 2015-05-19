package org.javaee7.wildfly.samples.everest.checkout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.javaee7.wildfly.samples.everest.ServiceDiscovery;
import org.javaee7.wildfly.samples.everest.cart.Cart;
import org.javaee7.wildfly.samples.everest.cart.CartItem;

/**
 * @author arungupta
 */
@Named
@SessionScoped
public class OrderBean implements Serializable {
    @Inject Order order;
    
    @Inject Cart cart;
    
    String status;
    
    @Inject ServiceDiscovery services;
    
    public void saveOrder() {
        List<OrderItem> items = new ArrayList<>();
        List<CartItem> cartItems = cart.getItems();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.itemId = cartItem.getItemId();
            orderItem.itemCount = cartItem.getItemCount();
            items.add(orderItem);
        }
        order.setOrderItems(items);
        
        try {
            Response response = services.getOrderService().request().post(Entity.xml(order));

            Response.StatusType statusInfo = response.getStatusInfo();

            if (statusInfo.getFamily() == Response.Status.Family.SUCCESSFUL) {
                status = "Order successful, order number: " + response.readEntity(Order.class).orderId;
            } else
                status = statusInfo.getReasonPhrase();

            cart.clearCart();
        } catch (Exception e) {
            status = e.getLocalizedMessage();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
