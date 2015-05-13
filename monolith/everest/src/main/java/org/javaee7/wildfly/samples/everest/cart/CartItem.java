package org.javaee7.wildfly.samples.everest.cart;

import java.io.Serializable;

/**
 * @author arungupta
 */
public class CartItem implements Serializable {
    private int itemId;
    
    private int itemCount;

    public CartItem(int itemId, int itemCount) {
        this.itemId = itemId;
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
}
