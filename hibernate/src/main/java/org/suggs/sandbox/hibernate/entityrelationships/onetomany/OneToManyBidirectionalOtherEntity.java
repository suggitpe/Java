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

@Entity
@Table(name = "ONETOMANY_BI_OTHER_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "ONETOMANY_BI_OTHER_ENTITY_SQ")
@SuppressWarnings("unused")
public class OneToManyBidirectionalOtherEntity extends EntityBase {

    @Column(name = "OTHER_DATA", length = 64)
    private String otherData;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private OneToManyBidirectionalEntity parent;

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData( String aOtherData ) {
        otherData = aOtherData;
    }

    public OneToManyBidirectionalEntity getParent() {
        return parent;
    }

    public void setParent( OneToManyBidirectionalEntity aParent ) {
        parent = aParent;
    }

    @Override
    public String toString() {
        return super.toString() + "OneToManyBidirectionalOtherEntity [otherData=" + otherData + ", parent="
               + parent + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( otherData == null ) ? 0 : otherData.hashCode() );
        result = prime * result + ( ( parent == null ) ? 0 : parent.hashCode() );
        return result;
    }

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
