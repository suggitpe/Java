/*
 * EntityManagerSpringInjectionTest.java created on 18 Jan 2011 07:15:25 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entitymanager;

import org.suggs.sandbox.hibernate.basicentity.ReallyBasicEntity;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
 * Test suite that shows how we can spring inject an entity manager into our application and then use it
 * through the various JPA annotations.
 * 
 * @author suggitpe
 * @version 1.0 18 Jan 2011
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-entitymanager-springinjection.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class EntityManagerSpringInjectionWithAnnotationsTest {

    private static final Logger LOG = LoggerFactory.getLogger( EntityManagerSpringInjectionWithAnnotationsTest.class );

    private static final String DELETE_SQL = "delete ReallyBasicEntity where someInteger = :intValue";
    private static final Integer TEST_INT = Integer.valueOf( 9999 );

    @PersistenceContext
    protected EntityManager entityManager;

    private static Long ID;

    @Before
    public void onSetup() {
        LOG.debug( "---------------------" );
    }

    @Test
    public void executeDeleteSqlInTransaction() {
        LOG.debug( "Bulk delete operation" );
        entityManager.createQuery( DELETE_SQL ).setParameter( "intValue", TEST_INT ).executeUpdate();
    }

    @Test
    public void persistsInTransaction() {
        LOG.debug( "Single insert operation" );
        ReallyBasicEntity entity = new ReallyBasicEntity( "foo", TEST_INT.intValue(), Calendar.getInstance()
            .getTime() );
        entityManager.persist( entity );
        LOG.debug( "Entity persisted with ID [" + entity.getId() + "]" );
        ID = entity.getId();
    }

    @Test
    public void updatesInTransaction() {
        if ( ID == null ) {
            throw new IllegalArgumentException( "Expecting ID to have been populated prior to this test execution" );
        }
        LOG.debug( "Single Update operation with ID [" + ID + "]" );
        ReallyBasicEntity entity = entityManager.find( ReallyBasicEntity.class, ID );
        entity.setSomeString( "oooaaarrrggghhh" );
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deletesInTransaction() {
        LOG.debug( "Single delete operation" );
        Query q = entityManager.createQuery( "from ReallyBasicEntity where someInteger = 9999" );

        Collection<ReallyBasicEntity> entities = q.getResultList();
        for ( ReallyBasicEntity e : entities ) {
            entityManager.remove( e );
        }
    }

}
