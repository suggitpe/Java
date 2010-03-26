/*
 * EntityBase.java created on 26 Mar 2010 19:09:39 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Abstract base class for IDs, optimistic locking version and
 * create/update dates.
 * 
 * @author suggitpe
 * @version 1.0 26 Mar 2010
 */
@MappedSuperclass
public class EntityBase {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ENTITY_SEQ_STR")
    private long id;

    @Version
    @Column(name = "VERSION", nullable = false)
    private long version;

    private Date createDate;

    private Date updateDate;

    /**
     * Returns the value of id.
     * 
     * @return Returns the id.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id field to the specified value.
     * 
     * @param aId
     *            The id to set.
     */
    public void setId( long aId ) {
        id = aId;
    }

    /**
     * Returns the value of version.
     * 
     * @return Returns the version.
     */
    public long getVersion() {
        return version;
    }

    /**
     * Sets the version field to the specified value.
     * 
     * @param aVersion
     *            The version to set.
     */
    public void setVersion( long aVersion ) {
        version = aVersion;
    }

    /**
     * Returns the value of createDate.
     * 
     * @return Returns the createDate.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the createDate field to the specified value.
     * 
     * @param aCreateDate
     *            The createDate to set.
     */
    public void setCreateDate( Date aCreateDate ) {
        createDate = aCreateDate;
    }

    /**
     * Returns the value of updateDate.
     * 
     * @return Returns the updateDate.
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the updateDate field to the specified value.
     * 
     * @param aUpdateDate
     *            The updateDate to set.
     */
    public void setUpdateDate( Date aUpdateDate ) {
        updateDate = aUpdateDate;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EntityBase [id=" + Long.valueOf( id ).toString() + " , version=" + version
               + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( id ^ ( id >>> 32 ) );
        result = prime * result + (int) ( version ^ ( version >>> 32 ) );
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
        EntityBase other = (EntityBase) obj;
        if ( id != other.id )
            return false;
        if ( version != other.version )
            return false;
        return true;
    }

}
