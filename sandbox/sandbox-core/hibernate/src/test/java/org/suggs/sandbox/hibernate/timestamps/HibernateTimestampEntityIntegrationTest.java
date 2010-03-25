/*
 * HibernateTimestampEntityIntegrationTest.java created on 25 Mar 2010 07:03:30 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.hibernate.Session;

/**
 * Integration test to demonstrate how to use timestampes in a
 * Hibernate entity.
 * 
 * @author suggitpe
 * @version 1.0 25 Mar 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-annotation-timestamps.xml" })
public class HibernateTimestampEntityIntegrationTest extends AbstractSimpleHibernateIntegrationTest {

    private static final Log LOG = LogFactory.getLog( HibernateTimestampEntityIntegrationTest.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from TimestampedEntity" ).executeUpdate();
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#getEntityList()
     */
    @Override
    protected List<Class<?>> getEntityList() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( TimestampedEntity.class );
        return entityClassses;
    }

}
