/*
 * AbstractEntityManagerIntegrationTest.java created on 24 Jan 2011 18:53:19 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Abstract test to be used in conjuction with any Entity Manager entoty test.
 * 
 * @author suggitpe
 * @version 1.0 24 Jan 2011
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@Transactional
public abstract class AbstractEntityManagerIntegrationTest<PK extends Serializable, T> {

    private static final Logger LOG = LoggerFactory.getLogger( AbstractEntityManagerIntegrationTest.class );

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract void cleanUpData( EntityManager aEntityManager );

    protected abstract PK createKeyTemplate();

    protected abstract String createEntitySearchHql();

    protected abstract T createEntityTemplate( PK aKey );

    protected abstract void updateEntityForUpdateTest( T aEntity );

    @Before
    public void onSetup() {
        LOG.debug( "--------------------- Before setup" );
        cleanUpData( entityManager );
        LOG.debug( "--------------------- After setup" );
    }

    @After
    public void onTeardown() {
        LOG.debug( "--------------------- After" );
    }

    @Test
    public void basicCreateOperationCreatesCorrectObject() {
        LOG.info( "Testing the create CRUD function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;

            @Override
            public void beforeTest() {
                entity = createEntityTemplate( key );
                verifyEntityCount( 0L );
            }

            @Override
            public void executeTest() {
                entityManager.persist( entity );
            }

            @Override
            public void verifyTest() {
                verifyEntityCount( 1L );
                @SuppressWarnings("unchecked")
                T result = (T) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                verifyResult( entity, result );
            }
        } );
    }

    @Test
    public void basicReadOperationsInstantiatesCorrectObject() {
        LOG.info( "Testing the read CRUD function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;
            T readEntity = null;

            @Override
            public void beforeTest() {
                entity = createEntityTemplate( key );
                verifyEntityCount( 0L );

                entityManager.persist( entity );
                verifyEntityCount( 1L );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void executeTest() {
                if ( entity instanceof EntityBase ) {
                    readEntity = (T) entityManager.find( entity.getClass(), ( (EntityBase) entity ).getId() );
                }
                else {
                    readEntity = (T) entityManager.find( entity.getClass(), key );
                }
            }

            @Override
            public void verifyTest() {
                verifyEntityCount( 1L );
                verifyResult( entity, readEntity );
            }
        } );
    }

    @Test
    public void basicUpdateOperationsUpdatesCorrectObject() {
        LOG.info( "Testing the update CRUD function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;
            T clone = null;

            @Override
            public void beforeTest() {
                entity = createEntityTemplate( key );
                clone = createEntityTemplate( key );
                verifyEntityCount( 0L );

                entityManager.persist( entity );
                verifyEntityCount( 1L );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void executeTest() {
                entity = (T) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                updateEntityForUpdateTest( entity );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void verifyTest() {
                entity = (T) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                assertThat( entity, not( nullValue() ) );
                assertThat( entity, not( sameInstance( clone ) ) );
                assertThat( entity, not( equalTo( clone ) ) );
            }

        } );

    }

    @Test
    public void basicDeleteOperationsDeletesCorrectObject() {

        LOG.info( "Testing the delete CRUD function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;

            @Override
            public void beforeTest() {
                entity = createEntityTemplate( key );
                verifyEntityCount( 0L );

                entityManager.persist( entity );
                verifyEntityCount( 1L );
            }

            @Override
            public void executeTest() {
                @SuppressWarnings("unchecked")
                T entityToDelete = (T) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                entityManager.remove( entityToDelete );
            }

            @Override
            public void verifyTest() {
                verifyEntityCount( 0L );
            }
        } );
    }

    protected void runGenericTest( EntityManagerIntegrationTestCallback aCallback ) {
        LOG.debug( "==== Before test" );
        aCallback.beforeTest();

        entityManager.flush();
        entityManager.clear();

        LOG.debug( "==== Execute test" );
        aCallback.executeTest();

        entityManager.flush();
        entityManager.clear();

        LOG.debug( "==== Verify test" );
        aCallback.verifyTest();
    }

    public interface EntityManagerIntegrationTestCallback {

        void beforeTest();

        void executeTest();

        void verifyTest();
    }

    protected void verifyEntityCount( long aCountOfEntities ) {
        Long count = (Long) entityManager.createQuery( "select count(*) " + createEntitySearchHql() )
            .getSingleResult();
        assertThat( count, equalTo( Long.valueOf( aCountOfEntities ) ) );
        LOG.debug( aCountOfEntities + " rows in the database ... good" );
    }

    protected void verifyResult( T expected, T result ) {
        assertThat( result, not( nullValue() ) );
        assertThat( result, not( sameInstance( expected ) ) );
        assertThat( result, equalTo( expected ) );
        LOG.debug( "Objects of type [" + result.getClass().getSimpleName() + "] match up ... good" );
        LOG.debug( "Object debug:" + result );
    }

}
