package org.javaee7.wildfly.samples.everest.uzer;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author arungupta
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Uzer.findAll", query = "SELECT u FROM Uzer u"),
    @NamedQuery(name = "Uzer.findByLogin", query = "SELECT u FROM Uzer u where u.login = :login")
})
public class Uzer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    
    @Column
    String login;

    @Column
    String password;

    @Column
    String username;

    @Column
    String address1;

    @Column
    String address2;

    @Column
    String city;

    @Column
    String state;

    @Column
    String zip;

    @Column
    String country;

    @Column
    String creditcard;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
