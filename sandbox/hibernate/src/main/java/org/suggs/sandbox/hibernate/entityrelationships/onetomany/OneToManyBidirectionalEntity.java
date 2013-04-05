/*
 * OneToManyUnidirectionalEntity.java created on 20 Apr 2010 07:09:39 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetomany;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ONETOMANY_BI_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "ONETOMANY_BI_ENTITY_SQ")
@SuppressWarnings("unused")
public class OneToManyBidirectionalEntity extends EntityBase {

    private static final long serialVersionUID = 7473160465608010893L;

    @Column(name = "data", length = 64)
    private String data;

    @OneToMany(mappedBy = "parent")
    private Set<OneToManyBidirectionalOtherEntity> children = new HashSet<OneToManyBidirectionalOtherEntity>();

    public String getData() {
        return data;
    }

    public void setData(String aData) {
        data = aData;
    }

    public Set<OneToManyBidirectionalOtherEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<OneToManyBidirectionalOtherEntity> aChildren) {
        children = aChildren;
    }

    public void addChild(OneToManyBidirectionalOtherEntity aChild) {
        children.add(aChild);
    }

    @Override
    public String toString() {
        return super.toString() + "OneToManyUnidirectionalEntity [children=" + children + ", data=" + data + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((children == null) ? 0 : children.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
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
        OneToManyBidirectionalEntity other = (OneToManyBidirectionalEntity) obj;
        if (children == null) {
            if (other.children != null)
                return false;
        } else if (!children.equals(other.children))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        return true;
    }

}
