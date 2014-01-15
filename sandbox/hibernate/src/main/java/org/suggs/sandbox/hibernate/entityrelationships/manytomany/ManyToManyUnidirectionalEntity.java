/*
 * ManyToManyUnidirectionalEntity.java created on 22 Apr 2010 19:04:14 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytomany;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MANYTOMANY_UNI_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MANYTOMANY_UNI_ENTITY_SQ")
@SuppressWarnings("unused")
public class ManyToManyUnidirectionalEntity extends EntityBase {

    @Column(name = "DATA", length = 64)
    private String data;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "MANYTOMANY_UNI_JOIN")
    private Set<ManyToManyUnidirectionalOtherEntity> otherEnties = new HashSet<ManyToManyUnidirectionalOtherEntity>();

    public String getData() {
        return data;
    }

    public void setData(String aData) {
        data = aData;
    }

    public Set<ManyToManyUnidirectionalOtherEntity> getOtherEnties() {
        return otherEnties;
    }

    public void setOtherEnties(Set<ManyToManyUnidirectionalOtherEntity> aOtherEnties) {
        otherEnties = aOtherEnties;
    }

    public void addOtherEntity(ManyToManyUnidirectionalOtherEntity aOther) {
        otherEnties.add(aOther);
    }

    @Override
    public String toString() {
        return super.toString() + "ManyToManyUnidirectionalEntity [data=" + data + ", otherEnties="
                + otherEnties + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((otherEnties == null) ? 0 : otherEnties.hashCode());
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
        ManyToManyUnidirectionalEntity other = (ManyToManyUnidirectionalEntity) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (otherEnties == null) {
            if (other.otherEnties != null)
                return false;
        } else if (!otherEnties.equals(other.otherEnties))
            return false;
        return true;
    }

}
