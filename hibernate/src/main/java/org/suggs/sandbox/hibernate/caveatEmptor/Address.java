/*
 * Address.java created on 21 Mar 2007 17:40:54 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("unused")
public class Address {

    @Column(length = 64, nullable = false)
    private String street;

    @Column(length = 64, nullable = false)
    private String city;

    @Column(length = 25, nullable = false)
    private String zipCode;

    public Address() {
    }

    public Address(String aStreet, String aCity, String aZip) {
        street = aStreet;
        city = aCity;
        zipCode = aZip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String aCity) {
        city = aCity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String aStreet) {
        street = aStreet;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String aZipCode) {
        zipCode = aZipCode;
    }
}
