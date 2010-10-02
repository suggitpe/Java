/*
 * TradingAccountContraEntityHibernateIntegrationTest.java created on 2 Oct 2010 18:42:21 by suggitpe for project jms-to-db-persister
 * 
 */
package com.ubs.reporting.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest;
import com.ubs.reporting.support.TestUtils;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;

/**
 * Hibernate integration test for a TradingAccountContraEntity object.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2010
 */
@ContextConfiguration(locations = { "classpath:spring/hibernate-masterfiles.xml" })
public class TradingAccountContraEntityHibernateIntegrationTest extends AbstractSimpleHibernateIntegrationTest<TradingAccountKey, TradingAccountContraEntity> {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TradingAccountContraEntityHibernateIntegrationTest.class );

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from TradingAccountContraEntity where tradingAccountKey.tradingAccountDomain ='TEST_DOMAIN'" )
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
    protected TradingAccountContraEntity createEntityTemplate( TradingAccountKey aKey, Session aSession ) {
        TradingAccountContraEntity entityToReturn = new TradingAccountContraEntity( aKey,
                                                                                    "whatever NK means",
                                                                                    Integer.valueOf( 56789 ),
                                                                                    "contra entity domain",
                                                                                    "contra entity name",
                                                                                    TestUtils.createDateFrom_ddmmyyyy( "01012009" ),
                                                                                    "a status for the contra entity" );

        return entityToReturn;
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( TradingAccountContraEntity aEntity ) {
        aEntity.setContraEntityStatus( "This is a new contra entity status" );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from TradingAccountContraEntity where tradingAccountKey.tradingAccountDomain = 'TEST_DOMAIN'";
    }
}
