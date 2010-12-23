/*
 * TradingAccountRelationshipHibernateIntegrationTest.java created on 2 Oct 2010 18:42:49 by suggitpe for project jms-to-db-persister
 * 
 */
package com.ubs.reporting.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest;
import com.ubs.reporting.support.TestUtils;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;

/**
 * Hibernate integration test for a TradingAccountRelationship object.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2010
 */
@ContextConfiguration(locations = { "classpath:spring/hibernate-masterfiles.xml" })
public class TradingAccountRelationshipHibernateIntegrationTest extends AbstractSimpleHibernateIntegrationTest<TradingAccountKey, TradingAccountRelationship> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TradingAccountRelationshipHibernateIntegrationTest.class );

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from TradingAccountRelationship where tradingAccountKey.tradingAccountDomain ='TEST_DOMAIN'" )
            .executeUpdate();
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createKeyTemplate()
     */
    @Override
    protected TradingAccountKey createKeyTemplate() {
        return new TradingAccountKey( Integer.valueOf( 99999 ), "TEST_DOMAIN", Integer.valueOf( 1 ) );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable,
     *      org.hibernate.Session)
     */
    @Override
    protected TradingAccountRelationship createEntityTemplate( TradingAccountKey aKey, Session aSession ) {
        TradingAccountRelationship entityToReturn = new TradingAccountRelationship( aKey,
                                                                                    "whatever NK means",
                                                                                    "a relationship id",
                                                                                    "a relationship domain",
                                                                                    "a relationship type",
                                                                                    TestUtils.createDateFrom_ddmmyyyy( "04072006" ),
                                                                                    "a relationship status" );
        return entityToReturn;
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( TradingAccountRelationship aEntity ) {
        aEntity.setRelationshipStatus( "This is a new relationship status" );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from TradingAccountRelationship where tradingAccountKey.tradingAccountDomain = 'TEST_DOMAIN'";
    }
}
