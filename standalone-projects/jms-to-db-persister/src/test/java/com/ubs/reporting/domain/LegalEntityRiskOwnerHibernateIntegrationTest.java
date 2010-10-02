/*
 * LegalEntityRiskOwnerHibernateIntegrationTest.java created on 1 Oct 2010 19:19:02 by suggitpe for project jms-to-db-persister
 * 
 */
package com.ubs.reporting.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;

/**
 * Hibernate integration test for a LegalEntityRiskOwner domain object.
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2010
 */
@ContextConfiguration(locations = { "classpath:spring/it-legalentityriskowner-hibernate.xml" })
public class LegalEntityRiskOwnerHibernateIntegrationTest extends AbstractSimpleHibernateIntegrationTest<LegalEntityKey, LegalEntityRiskOwner> {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LegalEntityRiskOwnerHibernateIntegrationTest.class );

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from LegalEntityRiskOwner where legalEntityKey.legalEntityDomain ='TEST_DOMAIN'" )
            .executeUpdate();
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createKeyTemplate()
     */
    @Override
    protected LegalEntityKey createKeyTemplate() {
        return new LegalEntityKey( Integer.valueOf( 99999 ), "TEST_DOMAIN", Integer.valueOf( 1 ) );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable,
     *      org.hibernate.Session)
     */
    @Override
    protected LegalEntityRiskOwner createEntityTemplate( LegalEntityKey aKey, Session aSession ) {
        return new LegalEntityRiskOwner( aKey,
                                         "Some legal name",
                                         "type",
                                         "role",
                                         "code",
                                         "description",
                                         "contact",
                                         "owning group code" );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( LegalEntityRiskOwner aEntity ) {
        aEntity.setPrimaryContact( "Some other primary contact" );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from LegalEntityRiskOwner where legalEntityKey.legalEntityDomain = 'TEST_DOMAIN'";
    }
}
