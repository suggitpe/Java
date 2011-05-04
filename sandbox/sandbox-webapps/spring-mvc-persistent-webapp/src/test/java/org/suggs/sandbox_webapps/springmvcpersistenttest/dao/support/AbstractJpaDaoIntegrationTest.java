/*
 * AbstractJpaDaoIntegrationTest.java created on 24 Jan 2011 18:53:19 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.GenericDao;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Abstract test that provides tests for the basic DAO operations. The key thing that this class also does is to ensure
 * that any persistent classes are verified for correctness between persistence and reading.
 *
 * @author suggitpe
 * @version 1.0 24 Jan 2011
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "txManager")
@Transactional
public abstract class AbstractJpaDaoIntegrationTest<PK extends Serializable, T extends AbstractEntityBase> {

    private static final Logger LOG = LoggerFactory.getLogger( AbstractJpaDaoIntegrationTest.class );

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource(name = "dao")
    protected GenericDao<PK, T> daoUnderTest;

    protected abstract void cleanUpData( EntityManager aEntityManager );

    protected abstract PK createKeyTemplate();

    protected abstract String createEntitySearchHql();

    protected abstract T createEntityTemplate( PK aKey );

    protected abstract void updateEntityForUpdateTest( T aEntity );

    protected abstract void updateEntityForMergeTest( T aEntity );

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
    public void persistsObjects() {
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
                LOG.debug( ">>>> Calling save" );
                daoUnderTest.save( entity );
                LOG.debug( "Called save for object [" + entity + "]" );
            }

            @Override
            @SuppressWarnings("unchecked")
            public void verifyTest() {
                verifyEntityCount( 1L );
                T result = ( T ) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                verifyResult( entity, result );
            }
        } );
    }


    @Test
    public void readsObjects() {
        LOG.info( "Testing the read CRUD function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;
            T readEntity = null;

            @SuppressWarnings("unchecked")
            @Override
            public void beforeTest() {
                entity = createEntityTemplate( key );
                verifyEntityCount( 0L );

                entityManager.persist( entity );
                // if using a surrogate key, then we need to keep a ref to the key
                if ( key == null ) {
                    Serializable id = entity.getId();
                    key = ( PK ) id;
                }
                verifyEntityCount( 1L );
            }

            @Override
            public void executeTest() {
                LOG.debug( ">>>> Calling get with key [" + key + "]" );
                readEntity = daoUnderTest.get( key );
                LOG.debug( "Read object [" + readEntity + "] from database" );
            }

            @Override
            public void verifyTest() {
                verifyEntityCount( 1L );
                verifyResult( entity, readEntity );
            }
        } );
    }

    @Test
    public void identifiesExistingObjects() {
        LOG.info( "Testing the exists CRUD function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;
            T readEntity = null;

            @Override
            @SuppressWarnings("unchecked")
            public void beforeTest() {
                entity = createEntityTemplate( key );
                verifyEntityCount( 0L );

                entityManager.persist( entity );
                // if using a surrogate key, then we need to keep a ref to the key
                if ( key == null ) {
                    Serializable id = entity.getId();
                    key = ( PK ) id;
                }
                verifyEntityCount( 1L );
            }

            @Override
            public void executeTest() {
                LOG.debug( ">>>> Calling exists" );
                Boolean existsKey = Boolean.valueOf( daoUnderTest.exists( key ) );
                assertThat( existsKey, is( equalTo( Boolean.TRUE ) ) );
            }

            @Override
            public void verifyTest() {
            }
        } );

    }

    @Test
    public void retrievesAllObjects() {
        LOG.info( "Testing the getAll function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            List<T> results = null;

            @Override
            public void beforeTest() {
                verifyEntityCount( 0L );
                entityManager.persist( createEntityTemplate( createKeyTemplate() ) );
                verifyEntityCount( 1L );
            }

            @Override
            public void executeTest() {
                LOG.debug( ">>>> Calling getAll" );
                results = daoUnderTest.getAll();
            }

            @Override
            @SuppressWarnings("boxing")
            public void verifyTest() {
                assertThat( results.size(), is( greaterThan( 0 ) ) );
            }
        } );
    }


    @Test
    public void updatesObjects() {
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
                entity = ( T ) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                updateEntityForUpdateTest( entity );
                LOG.debug( "Updated object [" + entity + "]" );
                LOG.debug( ">>>> Allowing persistence mechanism to figure out that it needs an update" );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void verifyTest() {
                entity = ( T ) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                assertThat( entity, is( not( nullValue() ) ) );
                assertThat( entity, is( not( sameInstance( clone ) ) ) );
                assertThat( entity, is( not( equalTo( clone ) ) ) );
            }

        } );

    }

    @Test
    public void mergesObjectsWithPersistentObjects() {
        LOG.debug( "Testing the merge CRUD function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T originalEntity = null;
            T resultOfMerge = null;
            T resultInDatabase = null;

            @Override
            public void beforeTest() {
                originalEntity = createEntityTemplate( key );
                verifyEntityCount( 0L );

                entityManager.persist( originalEntity );
                verifyEntityCount( 1L );
            }

            @Override
            @SuppressWarnings("unchecked")
            public void executeTest() {
                T entityToMerge = ( T ) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                entityManager.detach( entityToMerge );
                updateEntityForMergeTest( entityToMerge );
                LOG.debug( ">>>> Calling merge" );
                resultOfMerge = daoUnderTest.merge( entityToMerge );
            }

            @Override
            @SuppressWarnings("unchecked")
            public void verifyTest() {
                resultInDatabase = ( T ) entityManager.createQuery( createEntitySearchHql() ).getSingleResult();
                assertThat( resultInDatabase, is( not( nullValue() ) ) );
                assertThat( originalEntity, is( not( sameInstance( resultInDatabase ) ) ) );
                assertThat( originalEntity, is( not( equalTo( resultInDatabase ) ) ) );

                assertThat( resultOfMerge, is( not( sameInstance( resultInDatabase ) ) ) );
                assertThat( resultOfMerge, is( equalTo( resultInDatabase ) ) );
            }

        } );

    }

    @Test
    public void deletesObjects() {

        LOG.info( "Testing the delete CRUD function" );
        runGenericTest( new EntityManagerIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;

            @Override
            @SuppressWarnings("unchecked")
            public void beforeTest() {
                entity = createEntityTemplate( key );
                verifyEntityCount( 0L );

                entityManager.persist( entity );
                if ( key == null ) {
                    Serializable id = entity.getId();
                    key = ( PK ) id;
                }
                verifyEntityCount( 1L );
            }

            @Override
            public void executeTest() {
                LOG.debug( ">>>> Calling remove for key [" + key + "]" );
                daoUnderTest.remove( key );
                LOG.debug( "Removed object with id [" + key + "]" );
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
        Long count = ( Long ) entityManager.createQuery( "select count(*) " + createEntitySearchHql() )
                .getSingleResult();
        assertThat( count, is( equalTo( Long.valueOf( aCountOfEntities ) ) ) );
        LOG.debug( aCountOfEntities + " rows in the database ... good" );
    }

    protected void verifyResult( T expected, T result ) {
        assertThat( "Resultant object is null", result, is( not( nullValue() ) ) );
        assertThat( "Resultant object is same instance as expected", result, is( not( sameInstance( expected ) ) ) );
        assertThat( "resultant object is not equal to expected object", result, is( equalTo( expected ) ) );
        LOG.debug( "Objects of type [" + result.getClass().getSimpleName() + "] match up ... good" );
        LOG.debug( "Object debug:" + result );
    }

}
