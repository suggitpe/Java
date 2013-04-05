/*
 * TimestampedChildEntity.java created on 15 Apr 2010 07:00:01 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.*;

@Entity
@Table(name = "TIMESTAMPED_CHILD_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "TIMESTAMP_CHILD_ENTITY_SQ")
@SuppressWarnings("unused")
public class TimestampedChildEntity extends EntityBase {

    private static final long serialVersionUID = 30140143038112595L;

    @Column(name = "CHILD_INTEGER", nullable = true)
    private Integer childInteger;

    @Column(name = "CHILD_STRING", nullable = true)
    private String childString;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PARENT_ID", nullable = false, updatable = false)
    private TimestampedEntity parent;

    public Integer getChildInteger() {
        return childInteger;
    }

    public void setChildInteger(Integer aChildInteger) {
        childInteger = aChildInteger;
    }

    public String getChildString() {
        return childString;
    }

    public void setChildString(String aChildString) {
        childString = aChildString;
    }

    public TimestampedEntity getParent() {
        return parent;
    }

    public void setParent(TimestampedEntity aParent) {
        parent = aParent;
    }

    @Override
    public String toString() {
        return super.toString() + " TimestampedChildEntity [childInteger=" + childInteger + ", childString="
                + childString + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((childInteger == null) ? 0 : childInteger.hashCode());
        result = prime * result + ((childString == null) ? 0 : childString.hashCode());
        return result;
    }


    @Override
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimestampedChildEntity other = (TimestampedChildEntity) obj;
        if (childInteger == null) {
            if (other.childInteger != null)
                return false;
        } else if (!childInteger.equals(other.childInteger))
            return false;
        if (childString == null) {
            if (other.childString != null)
                return false;
        } else if (!childString.equals(other.childString))
            return false;
        return true;
    }

}
