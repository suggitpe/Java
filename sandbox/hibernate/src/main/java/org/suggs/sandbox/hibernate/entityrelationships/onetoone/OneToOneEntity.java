/*
 * OneToOneEntity.java created on 20 Apr 2010 07:16:57 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetoone;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Core entity for the one to one example.
 * 
 * @author suggitpe
 * @version 1.0 20 Apr 2010
 */
@Entity
@Table(name = "ONETOONE_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "ONETOONE_ENTITY_SQ")
public class OneToOneEntity extends EntityBase {

    private static final long serialVersionUID = 1508093850397301800L;

    @Column(name = "data", length = 64)
    private String data;

    // uses the other entity primary key (id) as a map
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OTHER_ENTITY_ID")
    private OneToOneOtherEntity otherEntity;

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
    public OneToOneOtherEntity getOtherEntity() {
        return otherEntity;
    }

    /**
     * Sets the otherEntity field to the specified value.
     * 
     * @param aOtherEntity
     *            The otherEntity to set.
     */
    public void setOtherEntity( OneToOneOtherEntity aOtherEntity ) {
        otherEntity = aOtherEntity;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + "OneToOneEntity [data=" + data + ", otherEntity=" + otherEntity + "]";
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
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( !super.equals( obj ) )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        OneToOneEntity other = (OneToOneEntity) obj;
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
