/*
 * TimestampAuditingInterceptor.java created on 31 Mar 2010 07:20:00 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * Class to intercept all object persists and updates them with a
 * create_date/update_date time stamps just before the actual
 * persistence process.
 * 
 * @author suggitpe
 * @version 1.0 31 Mar 2010
 */
public class TimestampAuditingInterceptor extends EmptyInterceptor {

    private static final Log LOG = LogFactory.getLog( TimestampAuditingInterceptor.class );

    /**
     * Constructs a new instance.
     */
    public TimestampAuditingInterceptor() {
        LOG.info( "Initialising Timestamp Auditing Interceptor" );
    }

    /**
     * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object,
     *      java.io.Serializable, java.lang.Object[],
     *      java.lang.Object[], java.lang.String[],
     *      org.hibernate.type.Type[])
     */
    @Override
    public boolean onFlushDirty( Object entity, Serializable id, Object[] currentState,
                                 Object[] previousState, String[] propertyNames, Type[] types ) {
        if ( isEntityNotAuditable( entity ) ) {
            return false;
        }
        Date date = getDateTimeNow();
        TimestampAuditInfo auditInfo = ( (TimestampAuditable) entity ).getTimestampAuditInfo();

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Auditing existing entity with update date of [" + date + "]" );
        }
        auditInfo.setUpdateDate( date );
        return true;
    }

    /**
     * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object,
     *      java.io.Serializable, java.lang.Object[],
     *      java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onSave( Object entity, Serializable id, Object[] state, String[] propertyNames,
                           Type[] types ) {
        if ( isEntityNotAuditable( entity ) ) {
            return false;
        }
        Date date = getDateTimeNow();
        TimestampAuditInfo auditInfo = ( (TimestampAuditable) entity ).getTimestampAuditInfo();
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Auditing new entity with create date and update date of [" + date + "]" );
        }
        auditInfo.setCreateDate( date );
        auditInfo.setUpdateDate( date );
        return true;
    }

    private boolean isEntityNotAuditable( Object aEntity ) {
        return !( aEntity instanceof TimestampAuditable );
    }

    private Date getDateTimeNow() {
        return Calendar.getInstance().getTime();
    }

}
