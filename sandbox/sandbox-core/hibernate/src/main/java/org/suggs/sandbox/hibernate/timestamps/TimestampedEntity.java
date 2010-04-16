/*
 * TimestampedEntity.java created on 25 Mar 2010 06:51:47 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import org.suggs.sandbox.hibernate.support.EntityBase;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Simple entity that will show how to use timestamps in a Hibernate entity. This entity has a surrogate key
 * built from a sequence and a set of basic data values.
 * 
 * @author suggitpe
 * @version 1.0 25 Mar 2010
 */
@Entity
@Table(name = "TIMESTAMPED_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "TIMESTAMP_ENTITY_SEQ")
public class TimestampedEntity extends EntityBase {

    @Column(name = "STRING_DATA", nullable = false, length = 16)
    private String someString;

    @Column(name = "DATE_DATA", nullable = false)
    private Date someDate;

    @Column(name = "INTEGER_DATA", nullable = true)
    private Integer someInteger;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<TimestampedChildEntity> children = new HashSet<TimestampedChildEntity>();

    /**
     * Constructs a new instance.
     */
    public TimestampedEntity() {}

    /**
     * Constructs a new instance.
     * 
     * @param aString
     *            a string
     * @param aDate
     *            a data
     * @param aInteger
     *            an integer
     */
    public TimestampedEntity( String aString, Date aDate, Integer aInteger ) {
        someString = aString;
        someDate = aDate;
        someInteger = aInteger;
    }

    /**
     * Returns the value of someString.
     * 
     * @return Returns the someString.
     */
    public String getSomeString() {
        return someString;
    }

    /**
     * Sets the someString field to the specified value.
     * 
     * @param aSomeString
     *            The someString to set.
     */
    public void setSomeString( String aSomeString ) {
        someString = aSomeString;
    }

    /**
     * Returns the value of someDate.
     * 
     * @return Returns the someDate.
     */
    public Date getSomeDate() {
        return someDate;
    }

    /**
     * Sets the someDate field to the specified value.
     * 
     * @param aSomeDate
     *            The someDate to set.
     */
    public void setSomeDate( Date aSomeDate ) {
        someDate = aSomeDate;
    }

    /**
     * Returns the value of someInteger.
     * 
     * @return Returns the someInteger.
     */
    public Integer getSomeInteger() {
        return someInteger;
    }

    /**
     * Sets the someInteger field to the specified value.
     * 
     * @param aSomeInteger
     *            The someInteger to set.
     */
    public void setSomeInteger( Integer aSomeInteger ) {
        someInteger = aSomeInteger;
    }

    /**
     * Returns the value of children.
     * 
     * @return Returns the children.
     */
    public Set<TimestampedChildEntity> getChildren() {
        return children;
    }

    /**
     * Sets the children field to the specified value.
     * 
     * @param aChildren
     *            The children to set.
     */
    public void setChildren( Set<TimestampedChildEntity> aChildren ) {
        children = aChildren;
    }

    /**
     * Adds a child to the existing children.
     * 
     * @param aChild
     *            the child to add.
     */
    public void addChild( TimestampedChildEntity aChild ) {
        aChild.setParent( this );
        children.add( aChild );
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + " TimestampedEntity [children=" + children + ", someDate=" + someDate
               + ", someInteger=" + someInteger + ", someString=" + someString + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( children == null ) ? 0 : children.hashCode() );
        result = prime * result + ( ( someDate == null ) ? 0 : someDate.hashCode() );
        result = prime * result + ( ( someInteger == null ) ? 0 : someInteger.hashCode() );
        result = prime * result + ( ( someString == null ) ? 0 : someString.hashCode() );
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
        TimestampedEntity other = (TimestampedEntity) obj;
        if ( children == null ) {
            if ( other.children != null )
                return false;
        }
        else if ( !children.equals( other.children ) )
            return false;
        if ( someDate == null ) {
            if ( other.someDate != null )
                return false;
        }
        else if ( !someDate.equals( other.someDate ) )
            return false;
        if ( someInteger == null ) {
            if ( other.someInteger != null )
                return false;
        }
        else if ( !someInteger.equals( other.someInteger ) )
            return false;
        if ( someString == null ) {
            if ( other.someString != null )
                return false;
        }
        else if ( !someString.equals( other.someString ) )
            return false;
        return true;
    }

}
