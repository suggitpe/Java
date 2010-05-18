/*
 * ManyToManyUnidirectionalEntity.java created on 22 Apr 2010 19:04:14 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytomany;

import org.suggs.sandbox.hibernate.support.EntityBase;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Core entity for the many to many unidirectional example.
 * 
 * @author suggitpe
 * @version 1.0 22 Apr 2010
 */
@Entity
@Table(name = "MANYTOMANY_UNI_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MANYTOMANY_UNI_ENTITY_SQ")
public class ManyToManyUnidirectionalEntity extends EntityBase {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( ManyToManyUnidirectionalEntity.class );

    @Column(name = "DATA", length = 64)
    private String data;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "MANYTOMANY_UNI_JOIN")
    private Set<ManyToManyUnidirectionalOtherEntity> otherEnties = new HashSet<ManyToManyUnidirectionalOtherEntity>();

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
     * Returns the value of otherEnties.
     * 
     * @return Returns the otherEnties.
     */
    public Set<ManyToManyUnidirectionalOtherEntity> getOtherEnties() {
        return otherEnties;
    }

    /**
     * Sets the otherEnties field to the specified value.
     * 
     * @param aOtherEnties
     *            The otherEnties to set.
     */
    public void setOtherEnties( Set<ManyToManyUnidirectionalOtherEntity> aOtherEnties ) {
        otherEnties = aOtherEnties;
    }

    /**
     * Convenience method to allow easy addition of other entities to the set.
     * 
     * @param aOther
     *            the other entity to add.
     */
    public void addOtherEntity( ManyToManyUnidirectionalOtherEntity aOther ) {
        otherEnties.add( aOther );
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + "ManyToManyUnidirectionalEntity [data=" + data + ", otherEnties="
               + otherEnties + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
        result = prime * result + ( ( otherEnties == null ) ? 0 : otherEnties.hashCode() );
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
        ManyToManyUnidirectionalEntity other = (ManyToManyUnidirectionalEntity) obj;
        if ( data == null ) {
            if ( other.data != null )
                return false;
        }
        else if ( !data.equals( other.data ) )
            return false;
        if ( otherEnties == null ) {
            if ( other.otherEnties != null )
                return false;
        }
        else if ( !otherEnties.equals( other.otherEnties ) )
            return false;
        return true;
    }

}
