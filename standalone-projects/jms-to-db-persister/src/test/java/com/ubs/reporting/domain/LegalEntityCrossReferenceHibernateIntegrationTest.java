/*
 * LegalEntityCrossReferenceHibernateIntegrationTest.java created on 1 Oct 2010 19:18:24 by suggitpe for project jms-to-db-persister
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
 * Hibernate integration test for a LegalEntityCrossReference domain object.
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2010
 */
@ContextConfiguration(locations = { "classpath:spring/hibernate-masterfiles.xml" })
public class LegalEntityCrossReferenceHibernateIntegrationTest extends AbstractSimpleHibernateIntegrationTest<LegalEntityKey, LegalEntityCrossReference> {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LegalEntityCrossReferenceHibernateIntegrationTest.class );

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from LegalEntityCrossReference where legalEntityKey.legalEntityDomain ='TEST_DOMAIN'" )
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
    protected LegalEntityCrossReference createEntityTemplate( LegalEntityKey aKey, Session aSession ) {
        LegalEntityCrossReference entityToReturn = new LegalEntityCrossReference( aKey, "Some legal name" );
        entityToReturn.setAltIdId( "An alternate ID" );
        entityToReturn.setAltIdDomain( "alternate domain" );
        entityToReturn.setAltIdDomainCode( "alt id domain code" );
        entityToReturn.setAltIdStatusDate( TestUtils.createDateFrom_ddmmyyyy( "01012008" ) );
        entityToReturn.setAltIdStatus( "some status" );
        return entityToReturn;
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( LegalEntityCrossReference aEntity ) {
        aEntity.setAltIdStatus( "This is an altered status" );
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from LegalEntityCrossReference where legalEntityKey.legalEntityDomain = 'TEST_DOMAIN'";
    }
}
