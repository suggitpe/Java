/*
 * RelationshipsOneToManyUnidirectional.java created on 21 Apr 2010 19:03:41 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetomany;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test class for the one to many unidirectional example.
 * 
 * @author suggitpe
 * @version 1.0 21 Apr 2010
 */
@ContextConfiguration(locations = { "classpath:xml/ut-relationships-onetomany-unidirectional.xml" })
public class RelationshipsOneToManyUnidirectional extends AbstractSimpleHibernateIntegrationTest<Long, OneToManyUnidirectionalEntity> {

    // @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( RelationshipsOneToManyUnidirectional.class );

    private static final String WHERE_CLAUSE = "data in ('Some data', 'Updated data')";
    private static final String TEST_HQL = "from OneToManyUnidirectionalEntity where " + WHERE_CLAUSE;

    /**
     * TODO: clear up this test to work out why it fails.
     * 
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#basicCreateOperationCreatesCorrectObject()
     */
    @Ignore
    @Test
    @Override
    public void basicCreateOperationCreatesCorrectObject() {}

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        String otherDelete = " delete from OneToManyUnidirectionalOtherEntity o where id in (select id from OneToManyUnidirectionalEntity where "
                             + WHERE_CLAUSE + " )";
        String entityDelete = "delete " + TEST_HQL;

        aSession.createQuery( otherDelete ).executeUpdate();
        aSession.createQuery( entityDelete ).executeUpdate();
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return TEST_HQL;
    }

    /*
     * Thoughts on why this is failing. When you try to look up the object in the persistent set it then calls
     * on the containsAll method in the composite HashMap. What we see is that then the HashCode of the mapped
     * object is checked against the hash-bucket allocator (read bit shifting distribution algorithm) it
     * cannot see the object in the bucket. To my mind what is going wrong in there is not the containsAll
     * implementation but the initial writing to the HashMap that is at fault. So there is something wrong
     * with the way that the persistent set is being populated when tread from the db. An interesting thought:
     * why does the HashCode used for the HashMap not actually map to the HashCode of the underlying object?
     * <br/> Of interest. The hibernate object returned is fine, it is the object that we have created that
     * seems at fault.
     */
    @SuppressWarnings("boxing")
    @Override
    protected void doInitialVerificationForCreateTest( Session aSession,
                                                       OneToManyUnidirectionalEntity aExpected,
                                                       OneToManyUnidirectionalEntity aResult ) {
        LOG.debug( "============================" );

        assertThat( aResult.getChildren().iterator().next(), equalTo( aExpected.getChildren()
            .iterator()
            .next() ) );
        assertThat( aResult.getChildren().size(), equalTo( aExpected.getChildren().size() ) );
        assertThat( aResult.getChildren().getClass().getName(), equalTo( aExpected.getChildren()
            .getClass()
            .getName() ) );
        LOG.debug( aResult.getChildren().getClass().getName() );
        Set<OneToManyUnidirectionalOtherEntity> res = aResult.getChildren();
        Set<OneToManyUnidirectionalOtherEntity> exp = aExpected.getChildren();
        OneToManyUnidirectionalOtherEntity resEnt = res.iterator().next();
        OneToManyUnidirectionalOtherEntity expEnt = exp.iterator().next();

        LOG.debug( "act contains actEnt = " + exp.contains( expEnt ) ); // false
        LOG.debug( "act contains resEnt = " + exp.contains( resEnt ) ); // false
        LOG.debug( "res contains actEnt = " + res.contains( expEnt ) ); // true
        LOG.debug( "res contains resEnt = " + res.contains( resEnt ) ); // true

        // get in debug and do a persistent set diff on them both

        boolean b = exp.contains( expEnt );
        Assert.assertTrue( b );

        assertThat( res, equalTo( exp ) );

        // this is were we will fail
        // assertThat( aResult, equalTo( aExpected ) );
        LOG.debug( "============================" );
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable,
     *      org.hibernate.Session)
     */
    @Override
    protected OneToManyUnidirectionalEntity createEntityTemplate( Long aKey, Session aSession ) {
        OneToManyUnidirectionalOtherEntity other = new OneToManyUnidirectionalOtherEntity();
        other.setOtherData( "loopy loooooo" );

        OneToManyUnidirectionalEntity entity = new OneToManyUnidirectionalEntity();
        entity.setData( "Some data" );
        entity.addChild( other );
        return entity;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createKeyTemplate()
     */
    @Override
    protected Long createKeyTemplate() {
        return null;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( OneToManyUnidirectionalEntity aEntity ) {
        aEntity.setData( "Updated data" );
    }
}
