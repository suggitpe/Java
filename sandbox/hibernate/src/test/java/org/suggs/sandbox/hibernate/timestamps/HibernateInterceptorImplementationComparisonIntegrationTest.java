/*
 * HibernateInterceptorImplementationComparisonIntegrationTest.java created on 30 Dec 2010 07:47:16 by suggitpe for project sandbox-hibernate
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
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Test to show that the implementation of the Audit interceptor should not cause any problems, even if it is
 * actually a bad impl.
 * 
 * @author suggitpe
 * @version 1.0 30 Dec 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-timestamps.xml" })
public class HibernateInterceptorImplementationComparisonIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger( HibernateInterceptorImplementationComparisonIntegrationTest.class );

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionfactory;

    @Test
    public void testReferenceImplentation() {
        Long id = saveEntityWithFlush( sessionfactory.openSession( new ReferenceImplementionInterceptor() ) );
        updateEntityWithFlush( sessionfactory.openSession( new ReferenceImplementionInterceptor() ), id );
    }

    @Test
    public void testBadImplentation() {
        Long id = saveEntityWithFlush( sessionfactory.openSession( new BadlyImplementedInterceptor() ) );
        updateEntityWithFlush( sessionfactory.openSession( new BadlyImplementedInterceptor() ), id );
    }

    @Test
    public void testCombinationImplentation() {
        Long id = saveEntityWithFlush( sessionfactory.openSession( new CombinationImplementionInterceptor() ) );
        updateEntityWithFlush( sessionfactory.openSession( new CombinationImplementionInterceptor() ), id );
    }

    @SuppressWarnings("boxing")
    private Long saveEntityWithFlush( Session aSession ) {

        Long id = null;
        try {
            Transaction transaction = aSession.beginTransaction();

            try {
                TimestampedEntity entity = TimestampTestHelper.createTimestampEntity();
                aSession.save( entity );
                id = entity.getId();

                LOG.debug( "............................." );
                LOG.debug( "Calling flush" );
                aSession.flush();
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
            LOG.error( "Problems in the save process", e );
            Assert.fail( "Exception thrown in the save process somewhere" );
        }
        finally {
            if ( aSession.isOpen() ) {
                aSession.close();
            }
        }
        return id;
    }

    @SuppressWarnings("boxing")
    private void updateEntityWithFlush( Session aSession, Long aId ) {
        // this is added to ensure that the create and update timestamps are not the same
        try {
            Thread.sleep( 50 );
        }
        catch ( InterruptedException ie ) {
            // nadda
        }

        try {
            Transaction transaction = aSession.beginTransaction();

            try {
                TimestampedEntity entity = (TimestampedEntity) aSession.get( TimestampedEntity.class, aId );
                entity.setSomeString( "altered" );

                LOG.debug( "............................." );
                LOG.debug( "Calling flush" );
                aSession.flush();
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
            LOG.error( "Problems in the save process", e );
            Assert.fail( "Exception thrown in the update process somewhere" );
        }
        finally {
            if ( aSession.isOpen() ) {
                aSession.close();
            }
        }
    }

    // ######################################################
    // ### BADLY IMPLEMENTED IMPL
    // ######################################################
    static class BadlyImplementedInterceptor extends EmptyInterceptor {

        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = -4229418809121021638L;

        public BadlyImplementedInterceptor() {
            LOG.info( "Initialising badly implemented Timestamp Auditing Interceptor" );
        }

        @Override
        public boolean onFlushDirty( Object aEntity, Serializable aId, Object[] aCurrentState,
                                     Object[] aPreviousState, String[] aPropertyNames, Type[] aTypes ) {
            Timestamp date = getDateTimeNow();
            TimestampAuditInfo auditInfo = ( (TimestampAuditable) aEntity ).getTimestampAuditInfo();

            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "BAD BAD BAD: Auditing (onFlushDirty) existing entity with update date of ["
                           + date + "]" );
            }
            auditInfo.setUpdateDate( date );
            return false;
        }

        /**
         * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object, java.io.Serializable,
         *      java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
         */
        @Override
        public boolean onSave( Object aEntity, Serializable aId, Object[] aCurrentState,
                               String[] aPropertyNames, Type[] aTypes ) {
            Timestamp date = getDateTimeNow();
            TimestampAuditInfo auditInfo = ( (TimestampAuditable) aEntity ).getTimestampAuditInfo();
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "BAD BAD BAD: Auditing (onSave) new entity with create date and update date of ["
                           + date + "]" );
            }

            auditInfo.setCreateDate( date );
            auditInfo.setUpdateDate( date );
            return false;
        }

        private Timestamp getDateTimeNow() {
            return new Timestamp( Calendar.getInstance().getTimeInMillis() );
        }

    }

    // ######################################################
    // ### REFERENCE IMPL
    // ######################################################
    static class ReferenceImplementionInterceptor extends EmptyInterceptor {

        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 6091330599451897516L;
        private static final String AUDIT_PROPERTY_NAME = "timestampAuditInfo";

        private enum AUDIT_TYPE {
            CREATED_ON, UPDATED_ON
        }

        /**
         * Constructs a new instance.
         */
        public ReferenceImplementionInterceptor() {
            LOG.info( "Initialising reference impl Timestamp Auditing Interceptor" );
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
                LOG.debug( "GOOD GOOD GOOD: Auditing (onFlushDirty) existing entity with update date of ["
                           + date + "]" );
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
                LOG.debug( "GOOD GOOD GOOD: Auditing (onSave) new entity with create date and update date of ["
                           + date + "]" );
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

    // ######################################################
    // ### COMBINATION IMPL
    // ######################################################
    static class CombinationImplementionInterceptor extends EmptyInterceptor {

        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = -3420688643368885603L;
        private static final String AUDIT_PROPERTY_NAME = "timestampAuditInfo";

        private enum AUDIT_TYPE {
            CREATED_ON, UPDATED_ON
        }

        /**
         * Constructs a new instance.
         */
        public CombinationImplementionInterceptor() {
            LOG.info( "Initialising combination implementation Timestamp Auditing Interceptor" );
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
            TimestampAuditInfo auditInfo = ( (TimestampAuditable) aEntity ).getTimestampAuditInfo();
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "COMBO COMBO COMBO: Auditing (onFlushDirty) existing entity with update date of ["
                           + date + "]" );
            }
            auditInfo.setUpdateDate( date );
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
            TimestampAuditInfo auditInfo = ( (TimestampAuditable) aEntity ).getTimestampAuditInfo();
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "COMBO COMBO COMBO: Auditing (onSave) new entity with create date and update date of ["
                           + date + "]" );
            }

            auditInfo.setCreateDate( date );
            auditInfo.setUpdateDate( date );
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
