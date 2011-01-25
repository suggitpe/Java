/*
 * ReallyBasicEntityIntegrationTest.java created on 24 Jan 2011 19:10:44 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entitymanager;

import org.suggs.sandbox.hibernate.basicentity.ReallyBasicEntity;
import org.suggs.sandbox.hibernate.support.AbstractEntityManagerIntegrationTest;

import java.util.Calendar;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;

/**
 * TODO Write javadoc for ReallyBasicEntityIntegrationTest
 * 
 * @author suggitpe
 * @version 1.0 24 Jan 2011
 */
@ContextConfiguration(locations = { "classpath:xml/ut-entitymanager-springinjection.xml" })
public class ReallyBasicEntityIntegrationTest extends AbstractEntityManagerIntegrationTest<Long, ReallyBasicEntity> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReallyBasicEntityIntegrationTest.class );
    private static final String DELETE_SQL = "delete ReallyBasicEntity where someInteger = :intValue";

    @Override
    protected void cleanUpData( EntityManager aEntityManager ) {
        aEntityManager.createQuery( DELETE_SQL )
            .setParameter( "intValue", Integer.valueOf( 8899 ) )
            .executeUpdate();
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractEntityManagerIntegrationTest#createKeyTemplate()
     */
    @Override
    protected Long createKeyTemplate() {
        return null;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractEntityManagerIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from ReallyBasicEntity where someInteger = 8899";
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractEntityManagerIntegrationTest#createEntityTemplate(java.io.Serializable)
     */
    @Override
    protected ReallyBasicEntity createEntityTemplate( Long aKey ) {
        return new ReallyBasicEntity( "foo", 8899, Calendar.getInstance().getTime() );
    }
}
