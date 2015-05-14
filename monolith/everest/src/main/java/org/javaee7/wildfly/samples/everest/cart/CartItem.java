package org.javaee7.wildfly.samples.everest.cart;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.javaee7.wildfly.samples.everest.catalog.ItemBean;

/**
 * @author arungupta
 */
@Named
@RequestScoped
public class CartItem implements Serializable {
    private int itemId;
    
    private String itemName;
    
    private int itemCount;
    
    @Inject ItemBean itemBean;
    
    public CartItem() { }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
        this.itemName = itemBean.getItemWithId(itemId).getName();
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

//    public void setName(String description) {
//        this.name = description;
//    }
}
