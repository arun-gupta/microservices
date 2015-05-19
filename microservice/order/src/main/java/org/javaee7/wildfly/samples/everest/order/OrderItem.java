package org.javaee7.wildfly.samples.everest.order;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * @author arungupta
 */
@Embeddable
public class OrderItem implements Serializable {
    int itemId;
    int itemCount;

    public OrderItem() {
    }
    
    public OrderItem(int itemId, int itemCount) {
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
