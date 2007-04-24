/*
 * CreditCard.java created on 22 Mar 2007 17:51:24 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CreditCard extends BillingDetails
{

    private static final Log LOG = LogFactory.getLog( CreditCard.class );

    private Integer mType_;
    private String mExpMonth_;
    private String mExpYear_;

    public CreditCard()
    {
        super();
        LOG.debug( "Creating a new CreditCard" );
    }

    public CreditCard( String aOwner, String aNumber, Date aCreateDate, Integer aType, String aExpMonth, String aExpYear )
    {
        super( aOwner, aNumber, aCreateDate );
        mType_ = aType;
        mExpMonth_ = aExpMonth;
        mExpYear_ = aExpYear;
    }

    public String getExpMonth()
    {
        return mExpMonth_;
    }

    public void setExpMonth( String expMonth )
    {
        mExpMonth_ = expMonth;
    }

    public String getExpYear()
    {
        return mExpYear_;
    }

    public void setExpYear( String expYear )
    {
        mExpYear_ = expYear;
    }

    public Integer getType()
    {
        return mType_;
    }

    public void setType( Integer type )
    {
        mType_ = type;
    }
}
