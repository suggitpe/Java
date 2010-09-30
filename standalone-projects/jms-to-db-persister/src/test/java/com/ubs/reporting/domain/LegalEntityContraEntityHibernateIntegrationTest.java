/*
 * LegalEntityContraEntityHibernateIntegrationTest.java created on 29 Sep 2010 20:24:45 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;

/**
 * Hibernate integration test for a LegalEntityContraEntity domain object.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2010
 */
@ContextConfiguration(locations = { "classpath:spring/it-legalentitycontraentity-hibernate.xml" })
public class LegalEntityContraEntityHibernateIntegrationTest extends AbstractSimpleHibernateIntegrationTest<LegalEntityKey, LegalEntityContraEntity> {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LegalEntityContraEntityHibernateIntegrationTest.class );

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from LegalEntityContra where legalEntityDomain ='TEST_DOMAIN'" )
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
    protected LegalEntityContraEntity createEntityTemplate( LegalEntityKey aKey, Session aSession ) {
        return new LegalEntityContraEntity( aKey, "Test Legal Name" );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( LegalEntityContraEntity aEntity ) {
        aEntity.setLegalName( "Updated Legal Name" );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from LegalEntityContraEntity where legalEntityDomain = 'TEST_DOMAIN'";
    }
}
