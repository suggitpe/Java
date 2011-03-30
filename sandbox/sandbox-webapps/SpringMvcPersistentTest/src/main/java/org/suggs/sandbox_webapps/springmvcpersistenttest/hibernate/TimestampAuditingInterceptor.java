/*
 * TimestampAuditingInterceptor.java created on 31 Mar 2010 07:20:00 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.hibernate;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.TimestampAuditInfo;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.TimestampAuditable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * Class to intercept all object persists and updates them with a create_date/update_date time stamps just before the
 * actual persistence process.
 *
 * @author suggitpe
 * @version 1.0 31 Mar 2010
 */
public class TimestampAuditingInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = 4974715372205680154L;
    private static final Logger LOG = LoggerFactory.getLogger( TimestampAuditingInterceptor.class );
    private static final String AUDIT_PROPERTY_NAME = "timestampAuditInfo";

    private enum AUDIT_TYPE {
        CREATED_ON, UPDATED_ON
    }

    /**
     * Constructs a new instance.
     */
    public TimestampAuditingInterceptor() {
        LOG.info( "Initialising Timestamp Auditing Interceptor" );
    }

    /**
     * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[],
     *      java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onFlushDirty( Object aEntity, Serializable aId, Object[] aCurrentState,
                                 Object[] aPreviousState, String[] aPropertyNames, Type[] aTypes ) {
        if ( !isEntityAuditable( aEntity ) ) {
            return false;
        }
        Timestamp date = getDateTimeNow();
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Auditing (onFlushDirty) existing [" + aEntity.getClass().getSimpleName() + "] entity with update date of [" + date + "]" );
        }
        updateDateProperty( aCurrentState, aPropertyNames, AUDIT_TYPE.UPDATED_ON, date );
        return true;
    }

    /**
     * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object, java.io.Serializable, java.lang.Object[],
     *      java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onSave( Object aEntity, Serializable aId, Object[] aCurrentState, String[] aPropertyNames,
                           Type[] aTypes ) {
        if ( !isEntityAuditable( aEntity ) ) {
            return false;
        }
        Timestamp date = getDateTimeNow();
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Auditing (onSave) new [" + aEntity.getClass().getSimpleName() + "] entity with create date and update date of [" + date + "]" );
        }
        updateDateProperty( aCurrentState, aPropertyNames, AUDIT_TYPE.CREATED_ON, date );
        updateDateProperty( aCurrentState, aPropertyNames, AUDIT_TYPE.UPDATED_ON, date );
        return true;
    }

    private boolean isEntityAuditable( Object aEntity ) {
        return aEntity instanceof TimestampAuditable;
    }

    private Timestamp getDateTimeNow() {

        return new Timestamp( Calendar.getInstance().getTimeInMillis() );
    }

    private void updateDateProperty( Object[] aCurrentState, String[] aPropertyNames, AUDIT_TYPE type,
                                     Timestamp aValue ) {
        for ( int i = 0; i < aPropertyNames.length; ++i ) {
            if ( AUDIT_PROPERTY_NAME.equals( aPropertyNames[i] ) ) {
                TimestampAuditInfo info = ( TimestampAuditInfo ) aCurrentState[i];
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
