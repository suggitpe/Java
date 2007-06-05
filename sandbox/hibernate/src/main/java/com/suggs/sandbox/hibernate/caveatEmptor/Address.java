/*
 * Address.java created on 21 Mar 2007 17:40:54 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address
{

    private String mStreet_;
    private String mCity_;
    private String mZipCode_;

    public Address()
    {
        super();
    }

    public Address( String aStreet, String aCity, String aZip )
    {
        super();
        mStreet_ = aStreet;
        mCity_ = aCity;
        mZipCode_ = aZip;
    }

    @Column(length = 64, nullable = false)
    public String getCity()
    {
        return mCity_;
    }

    public void setCity( String city )
    {
        mCity_ = city;
    }

    @Column(length = 64, nullable = false)
    public String getStreet()
    {
        return mStreet_;
    }

    public void setStreet( String street )
    {
        mStreet_ = street;
    }

    @Column(length = 25, nullable = false)
    public String getZipCode()
    {
        return mZipCode_;
    }

    public void setZipCode( String zipCode )
    {
        mZipCode_ = zipCode;
    }
}
