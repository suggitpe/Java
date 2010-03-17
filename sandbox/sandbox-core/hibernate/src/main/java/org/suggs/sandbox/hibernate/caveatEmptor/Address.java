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
public class Address
{

    private String mStreet_;
    private String mCity_;
    private String mZipCode_;

    /**
     * Constructs a new instance.
     */
    public Address()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aStreet
     * @param aCity
     * @param aZip
     */
    public Address( String aStreet, String aCity, String aZip )
    {
        super();
        mStreet_ = aStreet;
        mCity_ = aCity;
        mZipCode_ = aZip;
    }

    /**
     * @return city
     */
    @Column(length = 64, nullable = false)
    public String getCity()
    {
        return mCity_;
    }

    /**
     * @param city
     */
    public void setCity( String city )
    {
        mCity_ = city;
    }

    /**
     * @return street
     */
    @Column(length = 64, nullable = false)
    public String getStreet()
    {
        return mStreet_;
    }

    /**
     * @param street
     */
    public void setStreet( String street )
    {
        mStreet_ = street;
    }

    /**
     * @return zip code`
     */
    @Column(length = 25, nullable = false)
    public String getZipCode()
    {
        return mZipCode_;
    }

    /**
     * @param zipCode
     */
    public void setZipCode( String zipCode )
    {
        mZipCode_ = zipCode;
    }
}
