/*
 * CounterpartyDaoTest.java created on 3 Feb 2011 21:00:03 by suggitpe for project sandbox-spring-mvc-persistent-test
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.dao;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.Counterparty;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.CounterpartyContact;

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

    private static final String DELETE_COUNTERPARTY = "delete Counterparty where externalId = :intValue";
    private static final String DELETE_CONTACT_SQL = "delete from counterparty_contact cc where exists (select 1 from counterparty c where c.id = cc.counterparty_id and c.external_id = :intValue)";

    @Override
    protected void cleanUpData( EntityManager aEntityManager ) {
        Integer id = Integer.valueOf( 1234 );
        aEntityManager.createNativeQuery( DELETE_CONTACT_SQL ).setParameter( "intValue", id ).executeUpdate();
        aEntityManager.createQuery( DELETE_COUNTERPARTY ).setParameter( "intValue", id ).executeUpdate();
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#createKeyTemplate()
     */
    @Override
    protected Long createKeyTemplate() {
        return null;
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#createEntityTemplate(java.io.Serializable)
     */
    @Override
    protected Counterparty createEntityTemplate( Long aKey ) {
        CounterpartyContact contact = new CounterpartyContact( "Peter", "Suggitt", "020 7567 2449" );
        Counterparty entity = new Counterparty( "name", "legal name", Integer.valueOf( 1234 ) );
        entity.addCounterpartyContact( contact );
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
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#updateEntityForUpdateTest(org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase)
     */
    @Override
    protected void updateEntityForUpdateTest( Counterparty aEntity ) {
        aEntity.setCounterpartyLegalName( "New legal name for update" );
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#updateEntityForMergeTest(org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase)
     */
    @Override
    protected void updateEntityForMergeTest( Counterparty aEntity ) {
        aEntity.setCounterpartyLegalName( "New legal name for merge" );
    }

}
