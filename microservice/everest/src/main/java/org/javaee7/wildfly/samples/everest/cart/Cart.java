package org.javaee7.wildfly.samples.everest.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author arungupta
 */
@Named
@SessionScoped
public class Cart implements Serializable {
    private List<CartItem> items;
    
    @Inject CartItem currentCartItem;
    
    @PostConstruct
    private void init() {
        items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }
    
    public void addItemToCart(ActionEvent event) {
        boolean exists = false;
        
        int itemId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemId"));
        String itemName = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemName");
        
        for (CartItem item : items) {
            if (itemId == item.getItemId()) {
                exists = true;
                CartItem cartItem = new CartItem(item.getItemId(), itemName, item.getItemCount() + currentCartItem.getItemCount());
                items.remove(item);
                items.add(cartItem);
            }
        }
        if (!exists) {
            CartItem cartItem = new CartItem(itemId, itemName, currentCartItem.getItemCount());
            items.add(cartItem);
        }
    }
    
    public void clearCart() {
        items.clear();
    }
}
