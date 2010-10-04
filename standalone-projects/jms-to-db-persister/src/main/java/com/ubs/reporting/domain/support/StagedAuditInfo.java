/*
 * StagedAuditInfo.java created on 4 Oct 2010 07:16:37 by suggitpe for project jms-to-db-persister
 * 
 */
package com.ubs.reporting.domain.support;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Auditable information used in conjunction with a Staged Entity.
 * 
 * @author suggitpe
 * @version 1.0 4 Oct 2010
 */
@Embeddable
public class StagedAuditInfo {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( StagedAuditInfo.class );

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STG_LOAD_DATE")
    private Date stagingLoadDate;

    /**
     * Returns the value of stagingLoadDate.
     * 
     * @return Returns the stagingLoadDate.
     */
    public Date getStagingLoadDate() {
        return stagingLoadDate;
    }

    /**
     * Sets the stagingLoadDate field to the specified value.
     * 
     * @param aStagingLoadDate
     *            The stagingLoadDate to set.
     */
    public void setStagingLoadDate( Date aStagingLoadDate ) {
        stagingLoadDate = aStagingLoadDate;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( stagingLoadDate == null ) ? 0 : stagingLoadDate.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        StagedAuditInfo other = (StagedAuditInfo) obj;
        if ( stagingLoadDate == null ) {
            if ( other.stagingLoadDate != null )
                return false;
        }
        else if ( !stagingLoadDate.equals( other.stagingLoadDate ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StagedAuditInfo [stagingLoadDate=" + stagingLoadDate + "]";
    }

}
