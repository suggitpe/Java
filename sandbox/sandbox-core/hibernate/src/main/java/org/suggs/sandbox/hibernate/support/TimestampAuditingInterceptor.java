/*
 * TimestampAuditingInterceptor.java created on 31 Mar 2010 07:20:00 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * Class to intercept all object persists and updates them with a create_date/update_date time stamps just
 * before the actual persistence process.
 * 
 * @author suggitpe
 * @version 1.0 31 Mar 2010
 */
public class TimestampAuditingInterceptor extends EmptyInterceptor {

    private static final Log LOG = LogFactory.getLog( TimestampAuditingInterceptor.class );

    private static final String AUDIT_PROPERTY_NAME = "timestampAuditInfo";

    private enum AUDIT_TYPE {
        CREATED_ON, UPDATED_ON
    };

    /**
     * Constructs a new instance.
     */
    public TimestampAuditingInterceptor() {
        LOG.info( "Initialising Timestamp Auditing Interceptor" );
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
            LOG.debug( "Auditing existing entity with update date of [" + date + "]" );
        }
        updateDateProperty( aCurrentState, aPropertyNames, AUDIT_TYPE.UPDATED_ON, date );
        auditInfo.setUpdateDate( date );
        return true;
    }

    /**
     * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object, java.io.Serializable, java.lang.Object[],
     *      java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onSave( Object aEntity, Serializable aId, Object[] aCurrentState, String[] aPropertyNames,
                           Type[] aTypes ) {
        if ( isNotEntityAuditable( aEntity ) ) {
            return false;
        }
        Timestamp date = getDateTimeNow();
        TimestampAuditInfo auditInfo = ( (TimestampAuditable) aEntity ).getTimestampAuditInfo();
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Auditing new entity with create date and update date of [" + date + "]" );
        }

        auditInfo.setCreateDate( date );
        updateDateProperty( aCurrentState, aPropertyNames, AUDIT_TYPE.CREATED_ON, date );

        auditInfo.setUpdateDate( date );
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
