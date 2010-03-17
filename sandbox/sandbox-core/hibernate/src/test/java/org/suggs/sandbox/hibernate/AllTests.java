/*
 * AllTetds.java created on 13 Apr 2007 06:21:23 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate;

import org.suggs.sandbox.hibernate.caveatEmptor.CaveatEmptorAnnotationObjectTest;
import org.suggs.sandbox.hibernate.caveatEmptor.CaveatEmptorAnnotationRelationshipTest;
import org.suggs.sandbox.hibernate.caveatEmptor.CaveatEmptorHbmObjectTest;
import org.suggs.sandbox.hibernate.caveatEmptor.CaveatEmptorHbmRelationshipTest;
import org.suggs.sandbox.hibernate.chapter2.MessageHibernateAnnotationDaoTest;
import org.suggs.sandbox.hibernate.chapter2.MessageHibernateHbmDaoTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for Hibernate sandbox
 * 
 * @author suggitpe
 * @version 1.0 13 Apr 2007
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { MessageHibernateHbmDaoTest.class, MessageHibernateAnnotationDaoTest.class,
                      CaveatEmptorHbmObjectTest.class, CaveatEmptorHbmRelationshipTest.class,
                      CaveatEmptorAnnotationObjectTest.class,
                      CaveatEmptorAnnotationRelationshipTest.class })
public class AllTests
{}
