/*
 * BankAccount.java created on 22 Mar 2007 17:51:48 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BankAccount extends BillingDetails
{

    private static final Log LOG = LogFactory.getLog( BankAccount.class );

    private String mBankName_;
    private String mBankSwift_;

    public BankAccount()
    {
        super();
        LOG.debug( "Creating a new BankAccount" );
    }

    public BankAccount( String aOwner, String aNumber, Date aCreateDate, String aBankName, String aBankSwift )
    {
        super( aOwner, aNumber, aCreateDate );
        mBankName_ = aBankName;
        mBankSwift_ = aBankSwift;

    }

    public String getBankName()
    {
        return mBankName_;
    }

    public void setBankName( String bankName )
    {
        mBankName_ = bankName;
    }

    public String getBankSwift()
    {
        return mBankSwift_;
    }

    public void setBankSwift( String bankSwift )
    {
        mBankSwift_ = bankSwift;
    }
}
