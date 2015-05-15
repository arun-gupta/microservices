package org.javaee7.wildfly.samples.everest.cart;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author arungupta
 */
@Named
@SessionScoped
public class CartItem implements Serializable {
    private int itemId;
    private String itemName;
    private int itemCount;
    
    public CartItem() { }

    public CartItem(int itemId, String itemName, int itemCount) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCount = itemCount;
    }
    
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }
}
