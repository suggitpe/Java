/*
 * OneToManyUnidirectionalEntity.java created on 20 Apr 2010 07:09:39 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetomany;

import org.suggs.sandbox.hibernate.support.EntityBase;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Core entity for the one to many unidirectional example.
 * 
 * @author suggitpe
 * @version 1.0 20 Apr 2010
 */
@Entity
@Table(name = "ONETOMANY_UNI_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "ONETOMANY_UNI_ENTITY_SQ")
public class OneToManyUnidirectionalEntity extends EntityBase {

    @Column(name = "data", length = 64)
    private String data;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Set<OneToManyUnidirectionalOtherEntity> children = new HashSet<OneToManyUnidirectionalOtherEntity>();

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
     * Returns the value of children.
     * 
     * @return Returns the children.
     */
    public Set<OneToManyUnidirectionalOtherEntity> getChildren() {
        return children;
    }

    /**
     * Sets the children field to the specified value.
     * 
     * @param aChildren
     *            The children to set.
     */
    public void setChildren( Set<OneToManyUnidirectionalOtherEntity> aChildren ) {
        children = aChildren;
    }

    /**
     * Convenience method to add a new child element.
     * 
     * @param aChild
     *            the child to add.
     */
    public void addChild( OneToManyUnidirectionalOtherEntity aChild ) {
        children.add( aChild );
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + "OneToManyUnidirectionalEntity [children=" + children + ", data=" + data
               + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( children == null ) ? 0 : children.hashCode() );
        result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
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
        OneToManyUnidirectionalEntity other = (OneToManyUnidirectionalEntity) obj;
        if ( children == null ) {
            if ( other.children != null )
                return false;
        }
        else if ( !children.equals( other.children ) )
            return false;
        if ( data == null ) {
            if ( other.data != null )
                return false;
        }
        else if ( !data.equals( other.data ) )
            return false;
        return true;
    }

}
