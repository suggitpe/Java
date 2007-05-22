/*
 * AllTetds.java created on 13 Apr 2007 06:21:23 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for Hibernate sandbox
 * 
 * @author suggitpe
 * @version 1.0 13 Apr 2007
 */
public class AllTests
{

    public static Test suite()
    {
        TestSuite s = new TestSuite();
        s.addTestSuite( com.suggs.sandbox.hibernate.chapter2.MessageHibernateHbmDaoTest.class );
        s.addTestSuite( com.suggs.sandbox.hibernate.chapter2.MessageHibernateAnnotationDaoTest.class );
        s.addTestSuite( com.suggs.sandbox.hibernate.caveatEmptor.CaveatEmptorHbmObjectTest.class );
        s.addTestSuite( com.suggs.sandbox.hibernate.caveatEmptor.CaveatEmptorHbmRelationshipTest.class );
        s.addTestSuite( com.suggs.sandbox.hibernate.caveatEmptor.CaveatEmptorAnnotationObjectTest.class );
        s.addTestSuite( com.suggs.sandbox.hibernate.caveatEmptor.CaveatEmptorAnnotationRelationshipTest.class );
        return s;
    }
}
