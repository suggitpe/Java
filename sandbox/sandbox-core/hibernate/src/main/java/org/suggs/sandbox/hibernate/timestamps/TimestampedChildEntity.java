/*
 * TimestampedChildEntity.java created on 15 Apr 2010 07:00:01 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Simple child entity that will have an RI constraint onto the parent table.
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2010
 */
@Entity
@Table(name = "TIMESTAMPED_CHILD_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "TIMESTAMP_CHILD_ENTITY_SQ")
public class TimestampedChildEntity extends EntityBase {

    @Column(name = "CHILD_INTEGER", nullable = true)
    private Integer childInteger;

    @Column(name = "CHILD_STRING", nullable = true)
    private String childString;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "PARENT_ID", nullable = false, updatable = false)
    private TimestampedEntity parent;

    /**
     * Returns the value of childInteger.
     * 
     * @return Returns the childInteger.
     */
    public Integer getChildInteger() {
        return childInteger;
    }

    /**
     * Sets the childInteger field to the specified value.
     * 
     * @param aChildInteger
     *            The childInteger to set.
     */
    public void setChildInteger( Integer aChildInteger ) {
        childInteger = aChildInteger;
    }

    /**
     * Returns the value of childString.
     * 
     * @return Returns the childString.
     */
    public String getChildString() {
        return childString;
    }

    /**
     * Sets the childString field to the specified value.
     * 
     * @param aChildString
     *            The childString to set.
     */
    public void setChildString( String aChildString ) {
        childString = aChildString;
    }

    /**
     * Returns the value of parent.
     * 
     * @return Returns the parent.
     */
    public TimestampedEntity getParent() {
        return parent;
    }

    /**
     * Sets the parent field to the specified value.
     * 
     * @param aParent
     *            The parent to set.
     */
    public void setParent( TimestampedEntity aParent ) {
        parent = aParent;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + " TimestampedChildEntity [childInteger=" + childInteger + ", childString="
               + childString + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( childInteger == null ) ? 0 : childInteger.hashCode() );
        result = prime * result + ( ( childString == null ) ? 0 : childString.hashCode() );
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
        TimestampedChildEntity other = (TimestampedChildEntity) obj;
        if ( childInteger == null ) {
            if ( other.childInteger != null )
                return false;
        }
        else if ( !childInteger.equals( other.childInteger ) )
            return false;
        if ( childString == null ) {
            if ( other.childString != null )
                return false;
        }
        else if ( !childString.equals( other.childString ) )
            return false;
        return true;
    }

}
