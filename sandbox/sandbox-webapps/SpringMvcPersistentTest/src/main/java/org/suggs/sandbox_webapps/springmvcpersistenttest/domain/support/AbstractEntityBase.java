/*
 * AbstractEntityBase.java created on 26 Mar 2010 19:09:39 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support;


import java.io.Serializable;
import javax.persistence.*;

/**
 * Abstract base class for IDs, optimistic locking version and create/update dates.
 *
 * @author suggitpe
 * @version 1.0 26 Mar 2010
 */
@MappedSuperclass
public abstract class AbstractEntityBase implements TimestampAuditable, Serializable {

    private static final long serialVersionUID = -3169530263675122564L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ENTITYBASE_SEQ_STR")
    private Long id;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Embedded
    private TimestampAuditInfo timestampAuditInfo = new TimestampAuditInfo();

    /**
     * Returns the value of id.
     *
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id field to the specified value.
     *
     * @param aId The id to set.
     */
    public void setId( Long aId ) {
        id = aId;
    }

    /**
     * Returns the value of version.
     *
     * @return Returns the version.
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the version field to the specified value.
     *
     * @param aVersion The version to set.
     */
    public void setVersion( Integer aVersion ) {
        version = aVersion;
    }

    public boolean isNew() {
        return ( id == null );
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.TimestampAuditable#getTimestampAuditInfo()
     */
    @Override
    public TimestampAuditInfo getTimestampAuditInfo() {
        return timestampAuditInfo;
    }

    /**
     * Sets the timestampAuditInfo field to the specified value.
     *
     * @param aTimestampAuditInfo The timestampAuditInfo to set.
     */
    public void setTimestampAuditInfo( TimestampAuditInfo aTimestampAuditInfo ) {
        timestampAuditInfo = aTimestampAuditInfo;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AbstractEntityBase [id=" + id + ", version=" + version + ", timestampAuditInfo="
                + timestampAuditInfo + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        result = prime * result + ( ( version == null ) ? 0 : version.hashCode() );
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
        AbstractEntityBase other = ( AbstractEntityBase ) obj;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        }
        else if ( !id.equals( other.id ) )
            return false;
        if ( timestampAuditInfo == null ) {
            if ( other.timestampAuditInfo != null )
                return false;
        }
        else if ( !timestampAuditInfo.equals( other.timestampAuditInfo ) )
            return false;
        if ( version == null ) {
            if ( other.version != null )
                return false;
        }
        else if ( !version.equals( other.version ) )
            return false;
        return true;
    }

}
