/*
 * EntityManagerSpringInjectionTest.java created on 18 Jan 2011 07:15:25 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entitymanager;

import org.suggs.sandbox.hibernate.basicentity.ReallyBasicEntity;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test suite that shows how we can spring inject an entity manager into our application
 * 
 * @author suggitpe
 * @version 1.0 18 Jan 2011
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-entitymanager-springinjection.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class EntityManagerSpringInjectionWithAnnotationsTest {

    private static final Logger LOG = LoggerFactory.getLogger( EntityManagerSpringInjectionWithAnnotationsTest.class );

    @Resource
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void onSetup() {
        LOG.debug( "============================" );
        if ( entityManagerFactory == null ) {
            throw new IllegalStateException();
        }
    }

    @Test
    public void persistsFromAnnotation() {
        EntityManager mgr = entityManagerFactory.createEntityManager();
        mgr.persist( new ReallyBasicEntity( "boo", 9999, Calendar.getInstance().getTime() ) );
    }

    @After
    public void onTeardown() {
        entityManagerFactory.close();
    }

}
