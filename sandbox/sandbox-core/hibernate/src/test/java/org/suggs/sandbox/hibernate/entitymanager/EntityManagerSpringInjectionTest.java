/*
 * EntityManagerSpringInjectionTest.java created on 18 Jan 2011 07:15:25 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entitymanager;

import org.suggs.sandbox.hibernate.basicentity.ReallyBasicEntity;

import java.util.Calendar;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test suite that shows how we can spring inject an entity manager into our application
 * 
 * @author suggitpe
 * @version 1.0 18 Jan 2011
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-entitymanager-springinjection.xml" })
public class EntityManagerSpringInjectionTest {

    private static final Logger LOG = LoggerFactory.getLogger( EntityManagerSpringInjectionTest.class );

    @Resource
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void onSetup() {
        LOG.debug( "============================" );
        if ( entityManagerFactory == null ) {
            throw new IllegalStateException();
        }
    }

    @After
    public void onTeardown() {
        entityManagerFactory.close();
    }

    @Test
    public void injectedEntityManagerCanSaveObject() {
        EntityManager mgr = entityManagerFactory.createEntityManager();
        try {
            mgr.getTransaction().begin();
            ReallyBasicEntity entity = new ReallyBasicEntity( "foo", 999, Calendar.getInstance().getTime() );
            mgr.persist( entity );

            mgr.flush();

            mgr.getTransaction().commit();
        }
        finally {
            mgr.close();
        }

    }

    @Test
    public void injectedEntityManagerFactoryQueriesCorrectly() {
        EntityManager mgr = entityManagerFactory.createEntityManager();
        try {
            Query q = mgr.createQuery( "from ReallyBasicEntity", ReallyBasicEntity.class );
            Collection<?> result = q.getResultList();
            LOG.debug( result.toString() );
        }
        finally {
            mgr.close();
        }
    }

    @Test
    public void injectedEntityManagerCanDeleteTableContents() {
        EntityManager mgr = entityManagerFactory.createEntityManager();
        try {
            mgr.getTransaction().begin();
            Query q = mgr.createQuery( "from ReallyBasicEntity" );
            Collection<?> result = q.getResultList();
            LOG.debug( "Deleting [" + result.size() + "] entries from ReallyBasicEntity" );
            for ( Object e : result ) {
                LOG.debug( e.toString() );
                mgr.remove( e );
            }
            mgr.getTransaction().commit();
        }
        finally {
            mgr.close();
        }

    }

}
