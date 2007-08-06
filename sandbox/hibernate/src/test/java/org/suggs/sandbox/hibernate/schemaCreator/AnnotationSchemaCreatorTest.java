/*
 * AnnottaionSchemaCreatorTest.java created on 5 Jun 2007 06:19:35 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.schemaCreator;

import org.suggs.sandbox.hibernate.caveatEmptor.Address;
import org.suggs.sandbox.hibernate.caveatEmptor.BankAccount;
import org.suggs.sandbox.hibernate.caveatEmptor.Bid;
import org.suggs.sandbox.hibernate.caveatEmptor.BillingDetails;
import org.suggs.sandbox.hibernate.caveatEmptor.Category;
import org.suggs.sandbox.hibernate.caveatEmptor.Comment;
import org.suggs.sandbox.hibernate.caveatEmptor.CreditCard;
import org.suggs.sandbox.hibernate.caveatEmptor.Item;
import org.suggs.sandbox.hibernate.caveatEmptor.User;
import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;
import org.suggs.sandbox.hibernate.chapter2.Message;


import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * 
 * Test object
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
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
