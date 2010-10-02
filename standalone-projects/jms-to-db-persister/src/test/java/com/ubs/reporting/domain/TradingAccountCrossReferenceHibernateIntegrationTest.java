/*
 * TradingAccountCrossReferenceHibernateIntegrationTest.java created on 2 Oct 2010 18:42:34 by suggitpe for project jms-to-db-persister
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
 * Hibernate integration test for a TradingAccountCrossReference object.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2010
 */
@ContextConfiguration(locations = { "classpath:spring/hibernate-masterfiles.xml" })
public class TradingAccountCrossReferenceHibernateIntegrationTest extends AbstractSimpleHibernateIntegrationTest<TradingAccountKey, TradingAccountCrossReference> {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TradingAccountCrossReferenceHibernateIntegrationTest.class );

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from TradingAccountCrossReference where tradingAccountKey.tradingAccountDomain ='TEST_DOMAIN'" )
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
    protected TradingAccountCrossReference createEntityTemplate( TradingAccountKey aKey, Session aSession ) {
        TradingAccountCrossReference entityToReturn = new TradingAccountCrossReference( aKey,
                                                                                        "whatever NK means",
                                                                                        "alt identifier id",
                                                                                        "alt identifier domain",
                                                                                        "alt domain code",
                                                                                        TestUtils.createDateFrom_ddmmyyyy( "01022008" ),
                                                                                        "alt id status",
                                                                                        "am I blocked" );

        return entityToReturn;
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( TradingAccountCrossReference aEntity ) {
        aEntity.setAltIdentifierStatus( "This is a new alt identifier status" );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from TradingAccountCrossReference where tradingAccountKey.tradingAccountDomain = 'TEST_DOMAIN'";
    }
}
