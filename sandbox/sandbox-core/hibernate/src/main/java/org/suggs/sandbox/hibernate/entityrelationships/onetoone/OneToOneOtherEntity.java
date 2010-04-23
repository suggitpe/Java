/*
 * OneToOneOtherEntity.java created on 20 Apr 2010 07:17:19 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetoone;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Other entity for the one to one example.
 * 
 * @author suggitpe
 * @version 1.0 20 Apr 2010
 */
@Entity
@Table(name = "ONETOONE_OTHER_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "ONETOONE_OTHER_ENTITY_SQ")
public class OneToOneOtherEntity extends EntityBase {

    @Column(name = "OTHER_DATA", length = 64, nullable = false)
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
        return super.toString() + "OneToOneOtherEntity [otherData=" + otherData + "]";
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
        OneToOneOtherEntity other = (OneToOneOtherEntity) obj;
        if ( otherData == null ) {
            if ( other.otherData != null )
                return false;
        }
        else if ( !otherData.equals( other.otherData ) )
            return false;
        return true;
    }

}
