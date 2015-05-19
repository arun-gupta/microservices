package org.javaee7.wildfly.samples.everest.catalog;

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
public class CatalogItem implements Serializable {
    private int id;
    private String name;
    private int type;
    private String description;
    
    public CatalogItem() { }

    public CatalogItem(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
