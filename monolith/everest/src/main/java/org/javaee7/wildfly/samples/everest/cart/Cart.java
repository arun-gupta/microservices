package org.javaee7.wildfly.samples.everest.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.javaee7.wildfly.samples.everest.catalog.ItemBean;

/**
 * @author arungupta
 */
@Named
@ConversationScoped
public class Cart implements Serializable {
    private List<CartItem> items;
    
    @Inject CartItem currentCartItem;
    
    @Inject ItemBean itemBean;
    
    @PostConstruct
    private void init() {
        items = new ArrayList<>();
    }

    public List<CartItem> getItem() {
        return items;
    }
    
    public void addItemToCart(ActionEvent event) {
        System.out.println("addToCart");
        boolean exists = false;
        
        int itemId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemId"));
        
        for (CartItem item : items) {
            if (itemId == item.getItemId()) {
                exists = true;
                CartItem cartItem = new CartItem();
                cartItem.setItemId(item.getItemId());
                cartItem.setItemCount(item.getItemCount() + currentCartItem.getItemCount());
                items.remove(item);
                items.add(cartItem);
            }
        }
        if (!exists) {
            CartItem cartItem = new CartItem();
            cartItem.setItemId(itemId);
            cartItem.setItemCount(currentCartItem.getItemCount());
            items.add(cartItem);
        }
    }
}
