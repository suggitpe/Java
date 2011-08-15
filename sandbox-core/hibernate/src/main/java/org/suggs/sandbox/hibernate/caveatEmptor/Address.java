/*
 * Address.java created on 21 Mar 2007 17:40:54 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Address class .. to be used within other classes
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Embeddable
public class Address {

    @Column(length = 64, nullable = false)
    private String street;

    @Column(length = 64, nullable = false)
    private String city;

    @Column(length = 25, nullable = false)
    private String zipCode;

    /**
     * Constructs a new instance.
     */
    public Address() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aStreet
     * @param aCity
     * @param aZip
     */
    public Address( String aStreet, String aCity, String aZip ) {
        super();
        street = aStreet;
        city = aCity;
        zipCode = aZip;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param aCity
     */
    public void setCity( String aCity ) {
        city = aCity;
    }

    /**
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param aStreet
     */
    public void setStreet( String aStreet ) {
        street = aStreet;
    }

    /**
     * @return zip code`
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param aZipCode
     */
    public void setZipCode( String aZipCode ) {
        zipCode = aZipCode;
    }
}
