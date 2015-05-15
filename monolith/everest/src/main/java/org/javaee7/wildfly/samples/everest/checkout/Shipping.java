package org.javaee7.wildfly.samples.everest.checkout;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.javaee7.wildfly.samples.everest.uzer.Uzer;

/**
 * @author arungupta
 */
@Named
@SessionScoped
public class Shipping implements Serializable {
    
    @PersistenceContext EntityManager em;
    
    String login;
    
    String name;
    String address1;
    String address2;
    String city;
    String zip;
    String country;
    String creditcard;
    
    public Shipping() { }
    
    public void updateShippingDetail() {
        Uzer uzer = em.createNamedQuery("Uzer.findByLogin", Uzer.class).setParameter("login", login).getSingleResult();
        name = uzer.getUsername();
        address1 = uzer.getAddress1();
        address2 = uzer.getAddress2();
        city = uzer.getCity();
        zip = uzer.getZip();
        country = uzer.getCountry();
        creditcard = uzer.getCreditCard();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }
}
