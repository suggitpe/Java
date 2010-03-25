/*
 * HibernateCompositeKeyPersistenceTest.java created on 18 Mar 2010 20:32:32 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositekeys;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Test to verify how Hibernate operates when using composite keys
 * with persistence and retrieval.
 * 
 * @author suggitpe
 * @version 1.0 18 Mar 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-annotation-compositekeys.xml" })
public class HibernateCompositeKeyPersistenceTest extends AbstractSimpleHibernateIntegrationTest {

    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from EntityObject" ).executeUpdate();
    }

    @Test
    public void createEntityObjectInTable() {

        runGenericTest( new HibernateTestCallback() {

            EntityKey key = buildEntityKey();
            EntityObject object = buildEntityObject( key );

            @Override
            public void beforeTest( Session aSession ) {}

            @Override
            public void executeTest( Session aSession ) {
                aSession.save( object );
            }

            @Override
            public void verifyTest( Session aSession ) {
                Long count = (Long) aSession.createQuery( "select count(key.keyOne) from EntityObject o where key.keyOne = '"
                                                          + key.getKeyOne() + "'" )
                    .uniqueResult();
                assertThat( Long.valueOf( 1 ), equalTo( count ) );
            }
        } );
    }

    @Test
    public void createEntityWithNullDataInTable() {
        runGenericTest( new HibernateTestCallback() {

            EntityKey key = buildEntityKey();
            EntityObject object = buildEntityObject( key );

            @Override
            public void beforeTest( Session aSession ) {}

            @Override
            public void executeTest( Session aSession ) {
                object.setDataTwo( null );
                aSession.save( object );
            }

            @Override
            public void verifyTest( Session aSession ) {
                Long count = (Long) aSession.createQuery( "select count(key.keyOne) from EntityObject o where key.keyOne = '"
                                                          + key.getKeyOne() + "'" )
                    .uniqueResult();
                assertThat( Long.valueOf( 1 ), equalTo( count ) );
            }
        } );
    }

    @Test
    public void createEntityWithNullKeyInTable() {
        runGenericTest( new HibernateTestCallback() {

            EntityKey key = buildEntityKey();
            EntityObject object = buildEntityObject( key );

            @Override
            public void beforeTest( Session aSession ) {}

            @Override
            public void executeTest( Session aSession ) {
                key.setKeyThree( null );
                aSession.save( object );
            }

            @Override
            public void verifyTest( Session aSession ) {
                Long count = (Long) aSession.createQuery( "select count(key.keyOne) from EntityObject o where key.keyOne = '"
                                                          + key.getKeyOne() + "'" )
                    .uniqueResult();
                assertThat( Long.valueOf( 1 ), equalTo( count ) );
            }
        } );
    }

    @Test
    public void retrieveEntityFromTableWithGet() {
        runGenericTest( new HibernateTestCallback() {

            EntityKey key = buildEntityKey();
            EntityObject object = buildEntityObject( key );
            EntityObject result;

            @Override
            public void beforeTest( Session aSession ) {
                aSession.save( object );
            }

            @Override
            public void executeTest( Session aSession ) {
                result = (EntityObject) aSession.get( EntityObject.class, key );
            }

            @Override
            public void verifyTest( Session aSession ) {
                verifyResult( object, result );
            }
        } );
    }

    @Test
    public void retrieveEntityWithNullDataFromTableWithGet() {
        runGenericTest( new HibernateTestCallback() {

            EntityKey key = buildEntityKey();
            EntityObject object = buildEntityObject( key );
            EntityObject result;

            @Override
            public void beforeTest( Session aSession ) {
                object.setDataTwo( null );
                aSession.save( object );
            }

            @Override
            public void executeTest( Session aSession ) {
                result = (EntityObject) aSession.get( EntityObject.class, key );
            }

            @Override
            public void verifyTest( Session aSession ) {
                verifyResult( object, result );
            }
        } );
    }

    /**
     * This test highlights and issue with the way that Hibernate
     * deals with null values in keys. If this test ever fails it
     * means that now Hibernate deals with null values in composite
     * keys.
     */
    @Test
    public void retrieveEntityWithNullKeyFromTableWithGetReturnsNull_HibernateIssue() {
        runGenericTest( new HibernateTestCallback() {

            EntityKey key = buildEntityKey();
            EntityObject object = buildEntityObject( key );
            EntityObject result;

            @Override
            public void beforeTest( Session aSession ) {
                key.setKeyThree( null );
                aSession.save( object );
            }

            @Override
            public void executeTest( Session aSession ) {
                result = (EntityObject) aSession.get( EntityObject.class, key );
            }

            @Override
            public void verifyTest( Session aSession ) {
                // DON'T BLINDLY CHANGE THIS - READ JAVADOC
                assertThat( result, nullValue() );
            }
        } );
    }

    /**
     * This test highlights and issue with the way that Hibernate
     * deals with null values in keys. If this test ever fails it
     * means that now Hibernate deals with null values in composite
     * keys.
     */
    @Test
    public void retrieveEntityWithNullKeyFromTableWithCriteriaReturnsNull_HibernateIssue() {
        runGenericTest( new HibernateTestCallback() {

            EntityKey key = buildEntityKey();
            EntityObject object = buildEntityObject( key );
            EntityObject result;

            @Override
            public void beforeTest( Session aSession ) {
                key.setKeyThree( null );
                aSession.save( object );
            }

            @Override
            public void executeTest( Session aSession ) {
                Criteria criteria = aSession.createCriteria( EntityObject.class );
                criteria.add( Restrictions.eq( "key.keyOne", key.getKeyOne() ) );
                criteria.add( Restrictions.eq( "key.keyTwo", key.getKeyTwo() ) );
                criteria.add( Restrictions.isNull( "key.keyThree" ) );
                result = (EntityObject) criteria.uniqueResult();
            }

            @Override
            public void verifyTest( Session aSession ) {
                // DON'T BLINDLY CHANGE THIS - READ JAVADOC
                assertThat( result, nullValue() );
            }
        } );
    }

    /**
     * This test highlights and issue with the way that Hibernate
     * deals with null values in keys. If this test ever fails it
     * means that now Hibernate deals with null values in composite
     * keys.
     */
    @Test
    public void retrieveEntityWithNullKeyFromTableWithQbeReturnsNull_HibernateIssue() {
        runGenericTest( new HibernateTestCallback() {

            EntityKey key = buildEntityKey();
            EntityObject object = buildEntityObject( key );
            EntityObject result;

            @Override
            public void beforeTest( Session aSession ) {
                key.setKeyThree( null );
                aSession.save( object );
            }

            @Override
            public void executeTest( Session aSession ) {
                EntityObject example = new EntityObject();
                example.setKey( key );
                Criteria criteria = aSession.createCriteria( EntityObject.class );
                criteria.add( Example.create( example ) );
                result = (EntityObject) criteria.uniqueResult();
            }

            @Override
            public void verifyTest( Session aSession ) {
                // DON'T BLINDLY CHANGE THIS - READ JAVADOC
                assertThat( result, nullValue() );
            }
        } );
    }

    /**
     * This test highlights and issue with the way that Hibernate
     * deals with null values in keys. If this test ever fails it
     * means that now Hibernate deals with null values in composite
     * keys.
     */
    @Test
    public void retrieveEntityWithNullKeyFromTableWithHqlReturnsNull_HibernateIssue() {
        runGenericTest( new HibernateTestCallback() {

            EntityKey key = buildEntityKey();
            EntityObject object = buildEntityObject( key );
            EntityObject result;

            @Override
            public void beforeTest( Session aSession ) {
                key.setKeyThree( null );
                aSession.save( object );
            }

            @Override
            public void executeTest( Session aSession ) {
                StringBuffer buff = new StringBuffer( "from EntityObject where " );
                buff.append( "key.keyOne = '" ).append( key.getKeyOne() ).append( "' " );
                buff.append( "and key.keyTwo = '" ).append( key.getKeyTwo() ).append( "' " );
                buff.append( "and key.keyThree is null" );
                Query query = aSession.createQuery( buff.toString() );
                result = (EntityObject) query.uniqueResult();
            }

            @Override
            public void verifyTest( Session aSession ) {
                // DON'T BLINDLY CHANGE THIS - READ JAVADOC
                assertThat( result, nullValue() );
            }
        } );
    }

    public void verifyResult( EntityObject expected, EntityObject result ) {
        assertThat( result, not( nullValue() ) );
        assertThat( result, not( sameInstance( expected ) ) );
        assertThat( result, equalTo( expected ) );

    }

    private EntityObject buildEntityObject( EntityKey aKey ) {
        EntityObject object = new EntityObject();
        object.setKey( aKey );
        object.setDataOne( "data1 1-1" );
        object.setDataTwo( "data1 2-1" );
        return object;
    }

    private EntityKey buildEntityKey() {
        EntityKey key = new EntityKey();
        key.setKeyOne( "key 1-1" );
        key.setKeyTwo( "key 2-1" );
        key.setKeyThree( "key 3-1" );
        return key;
    }

}
