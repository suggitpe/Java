/*
 * CounterpartyDaoTest.java created on 3 Feb 2011 21:00:03 by suggitpe for project sandbox-spring-mvc-persistent-test
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.dao;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.CounterpartyContact;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;

/**
 * Test for the Counterparty Contact DAO object.
 *
 * @author suggitpe
 * @version 1.0 3 Feb 2011
 */
@ContextConfiguration(locations = { "classpath:xml/it-dao-counterpartycontact.xml" })
public class CounterpartyContactDaoTest extends AbstractJpaDaoIntegrationTest<Long, CounterpartyContact> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyContactDaoTest.class );

    private static final String DELETE_CONTACT_SQL = "delete from CounterpartyContact where contactFirstName = :nameValue";

    @Override
    protected void cleanUpData( EntityManager aEntityManager ) {
        String name = "FooBar";
        aEntityManager.createQuery( DELETE_CONTACT_SQL ).setParameter( "nameValue", name ).executeUpdate();
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
    protected CounterpartyContact createEntityTemplate( Long aKey ) {
        return new CounterpartyContact( "FooBar", "SomeoneCool", "CoolAddress", "LU7 9GH", "020 7865 3456" );
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from CounterpartyContact where contactFirstName = 'FooBar'";
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#updateEntityForUpdateTest(org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase)
     */
    @Override
    protected void updateEntityForUpdateTest( CounterpartyContact aEntity ) {
        aEntity.setContactAddress( "This is my new address" );
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.dao.support.AbstractJpaDaoIntegrationTest#updateEntityForMergeTest(org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase)
     */
    @Override
    protected void updateEntityForMergeTest( CounterpartyContact aEntity ) {
        aEntity.setContactPostcode( "This is my new postcode" );
    }

}
