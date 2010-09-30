/*
 * LegalEntityHibernateIntegrationTest.java created on 28 Sep 2010 20:54:47 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest;

import org.hibernate.Session;

/**
 * TODO Write javadoc for LegalEntityHibernateIntegrationTest
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
public class LegalEntityHibernateIntegrationTest extends AbstractSimpleHibernateIntegrationTest<LegalEntityKey, LegalEntity> {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LegalEntityHibernateIntegrationTest.class );

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {}

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createKeyTemplate()
     */
    @Override
    protected LegalEntityKey createKeyTemplate() {
        return null;
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable,
     *      org.hibernate.Session)
     */
    @Override
    protected LegalEntity createEntityTemplate( LegalEntityKey aKey, Session aSession ) {
        return null;
    }

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( LegalEntity aEntity ) {}

    /**
     * @see com.ubs.reporting.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return null;
    }
}
