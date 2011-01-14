/*
 * EntityManagerBasicsTest.java created on 12 Jan 2011 08:04:25 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entitymanager;

import org.suggs.sandbox.hibernate.basicentity.ReallyBasicEntity;

import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test suite that utilises Entity Manager in the most basic of ways.
 * 
 * @author suggitpe
 * @version 1.0 12 Jan 2011
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-entitymanager-basics.xml" })
public class EntityManagerBasicsTest {

    private static final Logger LOG = LoggerFactory.getLogger( EntityManagerBasicsTest.class );

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Resource
    private Map<?, ?> jpaProperties;

    @Before
    public void onSetup() {
        if ( jpaProperties == null ) {
            throw new IllegalStateException( "Null properties injected from spring" );
        }
        entityManagerFactory = Persistence.createEntityManagerFactory( "testPersistence", jpaProperties );
        entityManager = entityManagerFactory.createEntityManager();
        LOG.debug( "----------------------" );
    }

    @Test
    public void savesBasicEntityCorrectly() {
        EntityTransaction trans = entityManager.getTransaction();
        trans.begin();

        ReallyBasicEntity entity = new ReallyBasicEntity( "Test String", 9999, Calendar.getInstance()
            .getTime() );
        entityManager.persist( entity );

        trans.commit();
    }

    @After
    public void onTearDown() {
        LOG.debug( "----------------------" );
        entityManager.close();
        entityManagerFactory.close();
    }
}
