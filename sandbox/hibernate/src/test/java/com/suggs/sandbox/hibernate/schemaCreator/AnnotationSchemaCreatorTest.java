/*
 * AnnottaionSchemaCreatorTest.java created on 5 Jun 2007 06:19:35 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.schemaCreator;

import com.suggs.sandbox.hibernate.caveatEmptor.Address;
import com.suggs.sandbox.hibernate.caveatEmptor.BankAccount;
import com.suggs.sandbox.hibernate.caveatEmptor.Bid;
import com.suggs.sandbox.hibernate.caveatEmptor.BillingDetails;
import com.suggs.sandbox.hibernate.caveatEmptor.Category;
import com.suggs.sandbox.hibernate.caveatEmptor.Comment;
import com.suggs.sandbox.hibernate.caveatEmptor.CreditCard;
import com.suggs.sandbox.hibernate.caveatEmptor.Item;
import com.suggs.sandbox.hibernate.caveatEmptor.User;
import com.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;
import com.suggs.sandbox.hibernate.chapter2.Message;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class AnnotationSchemaCreatorTest extends AbstractSchemaCreatorTest
{

    @Override
    protected Configuration createCfg()
    {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure( "hibernate.cfg.xml" );
        cfg.addAnnotatedClass( AbstractPersistentBaseClass.class );
        cfg.addAnnotatedClass( Address.class );
        cfg.addAnnotatedClass( BankAccount.class );
        cfg.addAnnotatedClass( Bid.class );
        cfg.addAnnotatedClass( BillingDetails.class );
        cfg.addAnnotatedClass( Category.class );
        cfg.addAnnotatedClass( Comment.class );
        cfg.addAnnotatedClass( CreditCard.class );
        cfg.addAnnotatedClass( Item.class );
        cfg.addAnnotatedClass( User.class );
        cfg.addAnnotatedClass( Message.class );

        return cfg;
    }
}
