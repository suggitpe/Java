/*
 * OneToManyUnidirectionalOtherEntity.java created on 20 Apr 2010 07:09:57 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetomany;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Child entity for the one to many bidirectional example.
 * 
 * @author suggitpe
 * @version 1.0 20 Apr 2010
 */
@Entity
@Table(name = "ONETOMANY_BI_OTHER_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "ONETOMANY_BI_OTHER_ENTITY_SQ")
public class OneToManyBidirectionalOtherEntity extends EntityBase {

    private static final long serialVersionUID = -1413555811127334820L;

    @Column(name = "OTHER_DATA", length = 64)
    private String otherData;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private OneToManyBidirectionalOtherEntity parent;

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
     * Returns the value of parent.
     * 
     * @return Returns the parent.
     */
    public OneToManyBidirectionalOtherEntity getParent() {
        return parent;
    }

    /**
     * Sets the parent field to the specified value.
     * 
     * @param aParent
     *            The parent to set.
     */
    public void setParent( OneToManyBidirectionalOtherEntity aParent ) {
        parent = aParent;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + "OneToManyBidirectionalOtherEntity [otherData=" + otherData + ", parent="
               + parent + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( otherData == null ) ? 0 : otherData.hashCode() );
        result = prime * result + ( ( parent == null ) ? 0 : parent.hashCode() );
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
        OneToManyBidirectionalOtherEntity other = (OneToManyBidirectionalOtherEntity) obj;
        if ( otherData == null ) {
            if ( other.otherData != null )
                return false;
        }
        else if ( !otherData.equals( other.otherData ) )
            return false;
        if ( parent == null ) {
            if ( other.parent != null )
                return false;
        }
        else if ( !parent.equals( other.parent ) )
            return false;
        return true;
    }

}
