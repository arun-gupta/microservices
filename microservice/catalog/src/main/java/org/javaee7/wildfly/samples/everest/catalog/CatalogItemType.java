package org.javaee7.wildfly.samples.everest.catalog;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author arungupta
 */
@Entity
public class CatalogItemType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int id;

    @Column
    private String name;

    public CatalogItemType() {
    }

    public CatalogItemType(int id, String name) {
        this.id = id;
        this.name = name;
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
}