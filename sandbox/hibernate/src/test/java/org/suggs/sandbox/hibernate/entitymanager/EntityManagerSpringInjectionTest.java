/*
 * EntityManagerSpringInjectionTest.java created on 18 Jan 2011 07:15:25 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entitymanager;

import org.suggs.sandbox.hibernate.basicentity.ReallyBasicEntity;

import java.util.Calendar;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.fail;

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

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @Before
    public void onSetup() {
        LOG.debug( "============================" );
        if ( entityManagerFactory == null ) {
            fail( "Failed to inject EntityManager" );
        }

        if ( entityManager == null ) {
            entityManager = entityManagerFactory.createEntityManager();
        }
    }

    @Test
    public void injectedEntityManagerCanSaveObject() {
        entityManager.getTransaction().begin();
        ReallyBasicEntity entity = new ReallyBasicEntity( "foo", 999, Calendar.getInstance().getTime() );
        entityManager.persist( entity );

        entityManager.flush();

        entityManager.getTransaction().commit();
    }

    @Test
    public void injectedEntityManagerFactoryQueriesCorrectly() {
        Query q = entityManager.createQuery( "from ReallyBasicEntity", ReallyBasicEntity.class );
        Collection<?> result = q.getResultList();
        LOG.debug( result.toString() );
    }

    @Test
    public void injectedEntityManagerCanDeleteTableContents() {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery( "from ReallyBasicEntity" );
        Collection<?> result = q.getResultList();
        LOG.debug( "Deleting [" + result.size() + "] entries from ReallyBasicEntity" );
        for ( Object e : result ) {
            LOG.debug( e.toString() );
            entityManager.remove( e );
        }
        entityManager.getTransaction().commit();
    }
}
