/*
 * ManyToManyUnidirectionalOtherEntity.java created on 22 Apr 2010 19:06:52 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytomany;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Child entity for the one to many unidirectional example.
 * 
 * @author suggitpe
 * @version 1.0 22 Apr 2010
 */
@Entity
@Table(name = "MANYTOMANY_UNI_OTHER_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MANYTOMANY_UNI_OTHER_ENTITY_SQ")
public class ManyToManyUnidirectionalOtherEntity extends EntityBase {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( ManyToManyUnidirectionalOtherEntity.class );

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
        return super.toString() + "ManyToManyUnidirectionalOtherEntity [otherData=" + otherData + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        ManyToManyUnidirectionalOtherEntity other = (ManyToManyUnidirectionalOtherEntity) obj;
        if ( otherData == null ) {
            if ( other.otherData != null )
                return false;
        }
        else if ( !otherData.equals( other.otherData ) )
            return false;
        return true;
    }

}
