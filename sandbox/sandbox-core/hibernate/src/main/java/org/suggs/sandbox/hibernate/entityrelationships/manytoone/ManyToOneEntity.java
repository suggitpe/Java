/*
 * ManyToOneEntity.java created on 20 Apr 2010 19:13:01 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytoone;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Core entity for the many to one example.
 * 
 * @author suggitpe
 * @version 1.0 20 Apr 2010
 */
@Entity
@Table(name = "MANYTOONE_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MANYTOONE_ENTITY_SQ")
public class ManyToOneEntity extends EntityBase {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( ManyToOneEntity.class );

    @Column(name = "DATA", length = 64)
    private String data;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "OTHER_ID")
    private ManyToOneOtherEntity otherEntity;

    /**
     * Returns the value of data.
     * 
     * @return Returns the data.
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data field to the specified value.
     * 
     * @param aData
     *            The data to set.
     */
    public void setData( String aData ) {
        data = aData;
    }

    /**
     * Returns the value of otherEntity.
     * 
     * @return Returns the otherEntity.
     */
    public ManyToOneOtherEntity getOtherEntity() {
        return otherEntity;
    }

    /**
     * Sets the otherEntity field to the specified value.
     * 
     * @param aOtherEntity
     *            The otherEntity to set.
     */
    public void setOtherEntity( ManyToOneOtherEntity aOtherEntity ) {
        otherEntity = aOtherEntity;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + "ManyToOneEntity [data=" + data + ", otherEntity=" + otherEntity + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
        result = prime * result + ( ( otherEntity == null ) ? 0 : otherEntity.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( !super.equals( obj ) )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        ManyToOneEntity other = (ManyToOneEntity) obj;
        if ( data == null ) {
            if ( other.data != null )
                return false;
        }
        else if ( !data.equals( other.data ) )
            return false;
        if ( otherEntity == null ) {
            if ( other.otherEntity != null )
                return false;
        }
        else if ( !otherEntity.equals( other.otherEntity ) )
            return false;
        return true;
    }

}
