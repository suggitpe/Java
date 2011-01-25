/*
 * AbstractEntityManagerIntegrationTest.java created on 24 Jan 2011 18:53:19 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
public abstract class AbstractEntityManagerIntegrationTest<K extends Serializable, E> {

    private static final Logger LOG = LoggerFactory.getLogger( AbstractEntityManagerIntegrationTest.class );

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract void cleanUpData( EntityManager aEntityManager );

    protected abstract K createKeyTemplate();

    protected abstract String createEntitySearchHql();

    protected abstract E createEntityTemplate( K aKey );

    protected void doInitialVerificationForCreateTest( E aExpected, E aResult ) {}

    @Before
    public void onSetup() {
        LOG.debug( "---------------------" );
        cleanUpData( entityManager );
    }

    @Test
    public void basicCreateOperationCreatesCorrectObject() {
        LOG.info( "Testing the create CRUD function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            K key = createKeyTemplate();
            E entity = null;

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
                E result = (E) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                doInitialVerificationForCreateTest( entity, result );
                verifyResult( entity, result );
            }
        } );
    }

    protected void runGenericTest( EntityManagerIntegrationTestCallback aCallback ) {
        aCallback.beforeTest();

        entityManager.flush();
        entityManager.clear();

        aCallback.executeTest();

        entityManager.flush();
        entityManager.clear();

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

    protected void verifyResult( E expected, E result ) {
        assertThat( result, not( nullValue() ) );
        assertThat( result, not( sameInstance( expected ) ) );
        assertThat( result, equalTo( expected ) );
        LOG.debug( "Objects of type [" + result.getClass().getSimpleName() + "] match up ... good" );
        LOG.debug( "Object debug:" + result );
    }

}
