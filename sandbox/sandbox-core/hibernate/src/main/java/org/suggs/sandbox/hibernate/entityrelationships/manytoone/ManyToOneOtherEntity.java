/*
 * ManyToOneOtherEntity.java created on 20 Apr 2010 19:15:01 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytoone;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Other entity for the many to one example.
 * 
 * @author suggitpe
 * @version 1.0 20 Apr 2010
 */
@Entity
@Table(name = "MANYTOONE_OTHER_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MANYTOONE_OTHER_ENTITY_SEQ")
public class ManyToOneOtherEntity extends EntityBase {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( ManyToOneOtherEntity.class );

    @Column(name = "OTHER_DATA", length = 64)
    private String otherData;

    /**
     * Returns the value of otherData.
     * 
     * @return Returns the otherData.
     */
    public String getOtherData() {
        return otherData;
    }

    /**
     * Sets the otherData field to the specified value.
     * 
     * @param aOtherData
     *            The otherData to set.
     */
    public void setOtherData( String aOtherData ) {
        otherData = aOtherData;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + "ManyToOneOtherEntity [otherData=" + otherData + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( otherData == null ) ? 0 : otherData.hashCode() );
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
        ManyToOneOtherEntity other = (ManyToOneOtherEntity) obj;
        if ( otherData == null ) {
            if ( other.otherData != null )
                return false;
        }
        else if ( !otherData.equals( other.otherData ) )
            return false;
        return true;
    }

}
