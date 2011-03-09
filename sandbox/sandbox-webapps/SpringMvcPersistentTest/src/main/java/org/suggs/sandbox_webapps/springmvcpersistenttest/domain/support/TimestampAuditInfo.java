/*
 * TimestampAuditInfo.java created on 31 Mar 2010 07:29:17 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Embeddable audit bean that holds all of the timestamp audit information.
 *
 * @author suggitpe
 * @version 1.0 31 Mar 2010
 */
@Embeddable
public class TimestampAuditInfo {

    @Column(name = "create_date", updatable = false)
    private Timestamp createDate;

    @Column(name = "update_date")
    private Timestamp updateDate;

    /**
     * Returns the value of createDate.
     *
     * @return Returns the createDate.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Sets the createDate field to the specified value.
     *
     * @param aCreateDate The createDate to set.
     */
    public void setCreateDate( Timestamp aCreateDate ) {
        createDate = aCreateDate;
    }

    /**
     * Returns the value of updateDate.
     *
     * @return Returns the updateDate.
     */
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the updateDate field to the specified value.
     *
     * @param aUpdateDate The updateDate to set.
     */
    public void setUpdateDate( Timestamp aUpdateDate ) {
        updateDate = aUpdateDate;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TimestampAuditInfo [createDate=" + createDate + ", updateDate=" + updateDate + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( createDate == null ) ? 0 : createDate.hashCode() );
        result = prime * result + ( ( updateDate == null ) ? 0 : updateDate.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        TimestampAuditInfo other = ( TimestampAuditInfo ) obj;
        if ( createDate == null ) {
            if ( other.createDate != null )
                return false;
        }
        else if ( !createDate.equals( other.createDate ) )
            return false;
        if ( updateDate == null ) {
            if ( other.updateDate != null )
                return false;
        }
        else if ( !updateDate.equals( other.updateDate ) )
            return false;
        return true;
    }

}
