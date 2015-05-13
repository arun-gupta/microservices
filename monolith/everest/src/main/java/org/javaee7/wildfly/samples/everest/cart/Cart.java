package org.javaee7.wildfly.samples.everest.cart;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 * @author arungupta
 */
@Named
@ConversationScoped
public class Cart implements Serializable {
    private List<CartItem> item;

    public List<CartItem> getItem() {
        return item;
    }
}
