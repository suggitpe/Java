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

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * TODO Write javadoc for BadlyImplementedInterceptorTest
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2010
 */
public class BadlyImplementedInterceptorTest extends HibernateTimestampEntityIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger( BadlyImplementedInterceptorTest.class );

    @SuppressWarnings("boxing")
    @Test
    public void newObjectVersionIsUpdatedAfterBadInterceptor() {
        Session session = sessionfactory.openSession( new BadlyImplementedInterceptor() );

        Long id = Long.valueOf( 0L );
        try {
            Transaction transaction = session.beginTransaction();

            try {
                TimestampedEntity entity = createEntityTemplate( createKeyTemplate(), session );
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

        session = sessionfactory.openSession( new BadlyImplementedInterceptor() );

        try {
            Transaction transaction = session.beginTransaction();

            try {
                TimestampedEntity entity = (TimestampedEntity) session.get( TimestampedEntity.class, id );
                entity.setSomeString( "altered" );

                LOG.debug( "............................." );
                LOG.debug( "Calling flush" );
                session.flush();
                assertThat( entity.getVersion(), equalTo( 1 ) );
                assertThat( entity.getTimestampAuditInfo().getCreateDate(),
                            not( equalTo( entity.getTimestampAuditInfo().getUpdateDate() ) ) );
                LOG.debug( "............................." );
                LOG.debug( "Calling commit" );
                transaction.commit();
                LOG.debug( "............................." );
                assertThat( entity.getVersion(), equalTo( 1 ) );
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

    static class BadlyImplementedInterceptor extends EmptyInterceptor {

        private static final long serialVersionUID = -8869088007826115180L;

        @Override
        public boolean onFlushDirty( Object aEntity, Serializable aId, Object[] aCurrentState,
                                     Object[] aPreviousState, String[] aPropertyNames, Type[] aTypes ) {
            if ( isNotEntityAuditable( aEntity ) ) {
                return false;
            }
            Timestamp date = getDateTimeNow();
            TimestampAuditInfo auditInfo = ( (TimestampAuditable) aEntity ).getTimestampAuditInfo();

            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "BAD BAD BAD: Auditing existing entity with update date of [" + date + "]" );
            }
            auditInfo.setUpdateDate( date );
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
            TimestampAuditInfo auditInfo = ( (TimestampAuditable) aEntity ).getTimestampAuditInfo();
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "BAD BAD BAD: Auditing new entity with create date and update date of [" + date
                           + "]" );
            }

            auditInfo.setCreateDate( date );
            auditInfo.setUpdateDate( date );
            return true;
        }

        private boolean isNotEntityAuditable( Object aEntity ) {
            return !( aEntity instanceof TimestampAuditable );
        }

        private Timestamp getDateTimeNow() {
            return new Timestamp( Calendar.getInstance().getTimeInMillis() );
        }

    }
}
