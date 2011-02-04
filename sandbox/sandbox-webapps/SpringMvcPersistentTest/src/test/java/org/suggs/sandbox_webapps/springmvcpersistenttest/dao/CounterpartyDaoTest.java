/*
 * CounterpartyDaoTest.java created on 3 Feb 2011 21:00:03 by suggitpe for project sandbox-spring-mvc-persistent-test
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.dao;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.Counterparty;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;

/**
 * Test for the Counterparty DAO object.
 * 
 * @author suggitpe
 * @version 1.0 3 Feb 2011
 */
@ContextConfiguration(locations = { "classpath:xml/it-dao-counterparty.xml" })
public class CounterpartyDaoTest extends AbstractJpaDaoIntegrationTest<Long, Counterparty> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyDaoTest.class );

    private static final String DELETE_SQL = "delete Counterparty where externalId = :intValue";

    @Override
    protected void cleanUpData( EntityManager aEntityManager ) {
        aEntityManager.createQuery( DELETE_SQL )
            .setParameter( "intValue", Integer.valueOf( 1234 ) )
            .executeUpdate();
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#createKeyTemplate()
     */
    @Override
    protected Long createKeyTemplate() {
        return null;
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#createEntityTemplate(java.lang.Object)
     */
    @Override
    protected Counterparty createEntityTemplate( Long aKey ) {
        Counterparty entity = new Counterparty( "name", "legal name", Integer.valueOf( 1234 ) );
        return entity;
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from Counterparty where externalId = 1234";
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( Counterparty aEntity ) {
        aEntity.setCounterpartyLegalName( "New legal name" );
    }
}
