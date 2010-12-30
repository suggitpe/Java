/*
 * TimestampInterceptorTest.java created on 24 Dec 2010 16:04:12 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * TODO Write javadoc for TimestampInterceptorTest
 * 
 * @author suggitpe
 * @version 1.0 24 Dec 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-timestamps.xml" })
public final class TimestampInterceptorTest {

    private static final Logger LOG = LoggerFactory.getLogger( TimestampInterceptorTest.class );

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionfactory;

    /**
     * This test has been written from scratch to try and isolate a specific problem with interceptor
     * execution between flush and commit.
     */
    @SuppressWarnings({ "unused", "boxing" })
    @Test
    public void interceptorIsCalledDuringFlush() {
        LOG.debug( "............................." );
        LOG.debug( "WRITING OBJECT TO DATABASE" );
        LOG.debug( "............................." );

        Long id = null;
        Session session = sessionfactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            try {
                TimestampedEntity entity = TimestampTestHelper.createTimestampEntity();

                assertThat( entity.getVersion(), nullValue() );
                LOG.debug( "--- Calling save [" + entity + "]" );
                // we expect that the interceptor is called here
                session.save( entity );
                assertThat( entity.getVersion(), equalTo( 0 ) );
                LOG.debug( "--- Called save [" + entity + "]" );
                LOG.debug( entity.toString() );

                id = entity.getId();

                LOG.debug( "............................." );
                LOG.debug( "--- Calling flush [" + entity + "]" );
                session.flush();
                assertThat( entity.getVersion(), equalTo( 0 ) );
                LOG.debug( "............................." );
                LOG.debug( "--- Calling commit [" + entity + "]" );
                transaction.commit();
                assertThat( entity.getVersion(), equalTo( 0 ) );
                LOG.debug( "............................." );
            }
            finally {
                if ( !transaction.wasCommitted() ) {
                    transaction.rollback();
                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
            Assert.fail( "Exception caught somewhere" );
        }
        finally {
            if ( session.isOpen() ) {
                session.close();
            }
        }

        LOG.debug( "............................." );
        LOG.debug( "READING and FLUSHING UNCHANGED OBJECT" );
        LOG.debug( "............................." );

        session = sessionfactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            try {
                TimestampedEntity entity = (TimestampedEntity) session.get( TimestampedEntity.class, id );

                LOG.debug( "............................." );
                LOG.debug( "Calling flush" );
                session.flush();
                LOG.debug( "............................." );
                LOG.debug( "Calling commit" );
                transaction.commit();
                LOG.debug( "............................." );

            }
            finally {
                if ( !transaction.wasCommitted() ) {
                    transaction.rollback();
                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
            Assert.fail( "failed in the update part of teh test" );
        }
        finally {
            if ( session.isOpen() ) {
                session.close();
            }
        }

        LOG.debug( "............................." );
        LOG.debug( "UPDATING OBJECT FROM DATABASE" );
        LOG.debug( "............................." );

        session = sessionfactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            try {
                TimestampedEntity entity = (TimestampedEntity) session.get( TimestampedEntity.class, id );
                entity.setSomeString( "altered" );

                LOG.debug( "............................." );
                LOG.debug( "Calling flush" );
                session.flush();
                assertThat( entity.getVersion(), equalTo( Integer.valueOf( 1 ) ) );

                entity.setSomeInteger( Integer.valueOf( 23 ) );
                session.flush();

                LOG.debug( "............................." );
                LOG.debug( "Calling commit" );
                transaction.commit();
                LOG.debug( "............................." );
                assertThat( entity.getVersion(), equalTo( Integer.valueOf( 2 ) ) );

                session.flush();
                assertThat( entity.getVersion(), equalTo( Integer.valueOf( 2 ) ) );

            }
            finally {
                if ( !transaction.wasCommitted() ) {
                    transaction.rollback();
                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
            Assert.fail( "failed in the update part of teh test" );
        }
        finally {
            if ( session.isOpen() ) {
                session.close();
            }
        }

    }

}
