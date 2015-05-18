package org.javaee7.wildfly.samples.everest.checkout;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author arungupta
 */
@Named
@SessionScoped
@XmlRootElement
public class OrderItem implements Serializable {
    int itemId;
    int itemCount;

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
