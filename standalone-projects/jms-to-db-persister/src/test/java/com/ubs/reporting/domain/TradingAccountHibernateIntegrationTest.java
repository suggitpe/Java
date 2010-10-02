/*
 * TradingAccountHibernateIntegrationTest.java created on 2 Oct 2010 18:41:47 by suggitpe for project jms-to-db-persister
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
 * Hibernate integration test for a TradingAccount object.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2010
 */
@ContextConfiguration(locations = { "classpath:spring/hibernate-masterfiles.xml" })
public class TradingAccountHibernateIntegrationTest extends AbstractSimpleHibernateIntegrationTest<TradingAccountKey, TradingAccount> {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TradingAccountHibernateIntegrationTest.class );

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from TradingAccount where tradingAccountKey.tradingAccountDomain ='TEST_DOMAIN'" )
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
    protected TradingAccount createEntityTemplate( TradingAccountKey aKey, Session aSession ) {
        TradingAccount entityToReturn = new TradingAccount( aKey,
                                                            "whatever NK means",
                                                            TestUtils.createDateFrom_ddmmyyyy( "06081999" ),
                                                            "a status",
                                                            TestUtils.createDateFrom_ddmmyyyy( "03022007" ),
                                                            "a client class",
                                                            "a risk location",
                                                            Integer.valueOf( 123456 ),
                                                            "a simple name",
                                                            "a short name",
                                                            "a communication type",
                                                            "address line",
                                                            "a city",
                                                            "a country",
                                                            "a function reference",
                                                            "addres line 2",
                                                            "addres line 3",
                                                            "addres line 4" );
        return entityToReturn;
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( TradingAccount aEntity ) {
        aEntity.setStatus( "This is a new status value" );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from TradingAccount where tradingAccountKey.tradingAccountDomain = 'TEST_DOMAIN'";
    }
}
