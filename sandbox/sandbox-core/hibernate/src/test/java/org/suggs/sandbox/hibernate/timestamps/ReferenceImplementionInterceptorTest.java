/*
 * BadlyImplementedInterceptorTest.java created on 22 Dec 2010 08:10:56 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import org.suggs.sandbox.hibernate.support.TimestampAuditInfo;
import org.suggs.sandbox.hibernate.support.TimestampAuditable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * TODO Write javadoc for BadlyImplementedInterceptorTest
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-timestamps.xml" })
public class ReferenceImplementionInterceptorTest {

    private static final Logger LOG = LoggerFactory.getLogger( ReferenceImplementionInterceptorTest.class );

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionfactory;

    @SuppressWarnings("boxing")
    @Test
    public void newObjectVersionIsUpdatedAfterRefImplInterceptor() {
        Session session = sessionfactory.openSession( new ReferenceImplementionInterceptor() );

        Long id = Long.valueOf( 0L );
        try {
            Transaction transaction = session.beginTransaction();

            try {
                TimestampedEntity entity = TimestampTestHelper.createTimestampEntity();
                session.save( entity );
                id = entity.getId();

                LOG.debug( "............................." );
                LOG.debug( "Calling flush" );
                session.flush();
                assertThat( entity.getVersion(), equalTo( 0 ) );
                assertThat( entity.getTimestampAuditInfo().getCreateDate(),
                            equalTo( entity.getTimestampAuditInfo().getUpdateDate() ) );
                LOG.debug( "............................." );
                LOG.debug( "Calling commit" );
                transaction.commit();
                LOG.debug( "............................." );
                assertThat( entity.getVersion(), equalTo( 0 ) );
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

        session = sessionfactory.openSession( new ReferenceImplementionInterceptor() );

    }

    static class ReferenceImplementionInterceptor extends EmptyInterceptor {

        private static final long serialVersionUID = 4974715372205680154L;
        private static final String AUDIT_PROPERTY_NAME = "timestampAuditInfo";

        private enum AUDIT_TYPE {
            CREATED_ON, UPDATED_ON
        }

        /**
         * Constructs a new instance.
         */
        public ReferenceImplementionInterceptor() {
            LOG.info( "Initialising ref impl Timestamp Auditing Interceptor" );
        }

        /**
         * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object, java.io.Serializable,
         *      java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
         */
        @Override
        public boolean onFlushDirty( Object aEntity, Serializable aId, Object[] aCurrentState,
                                     Object[] aPreviousState, String[] aPropertyNames, Type[] aTypes ) {
            if ( isNotEntityAuditable( aEntity ) ) {
                return false;
            }
            Timestamp date = getDateTimeNow();

            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "GOOD GOOD GOOD: Auditing existing entity with update date of [" + date + "]" );
            }
            updateDateProperty( aCurrentState, aPropertyNames, AUDIT_TYPE.UPDATED_ON, date );
            return true;
        }

        /**
         * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object, java.io.Serializable,
         *      java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
         */
        @Override
        public boolean onSave( Object aEntity, Serializable aId, Object[] aCurrentState,
                               String[] aPropertyNames, Type[] aTypes ) {
            if ( isNotEntityAuditable( aEntity ) ) {
                return false;
            }
            Timestamp date = getDateTimeNow();
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "GOOD GOOD GOOD: Auditing new entity with create date and update date of [" + date
                           + "]" );
            }

            updateDateProperty( aCurrentState, aPropertyNames, AUDIT_TYPE.CREATED_ON, date );
            updateDateProperty( aCurrentState, aPropertyNames, AUDIT_TYPE.UPDATED_ON, date );
            return true;
        }

        private boolean isNotEntityAuditable( Object aEntity ) {
            return !( aEntity instanceof TimestampAuditable );
        }

        private Timestamp getDateTimeNow() {
            return new Timestamp( Calendar.getInstance().getTimeInMillis() );
        }

        private void updateDateProperty( Object[] aCurrentState, String[] aPropertyNames, AUDIT_TYPE type,
                                         Timestamp aValue ) {
            for ( int i = 0; i < aPropertyNames.length; ++i ) {
                if ( AUDIT_PROPERTY_NAME.equals( aPropertyNames[i] ) ) {
                    TimestampAuditInfo info = (TimestampAuditInfo) aCurrentState[i];
                    if ( type == AUDIT_TYPE.CREATED_ON ) {
                        info.setCreateDate( aValue );
                    }
                    else if ( type == AUDIT_TYPE.UPDATED_ON ) {
                        info.setUpdateDate( aValue );
                    }
                    return;
                }
            }
        }

    }
}
