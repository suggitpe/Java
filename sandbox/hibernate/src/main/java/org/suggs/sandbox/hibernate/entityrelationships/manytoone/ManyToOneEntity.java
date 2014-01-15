/*
 * ManyToOneEntity.java created on 20 Apr 2010 19:13:01 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytoone;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.*;

@Entity
@Table(name = "MANYTOONE_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MANYTOONE_ENTITY_SQ")
@SuppressWarnings("unused")
public class ManyToOneEntity extends EntityBase {

    @Column(name = "DATA", length = 64)
    private String data;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "OTHER_ID")
    private ManyToOneOtherEntity otherEntity;

    public String getData() {
        return data;
    }

    public void setData(String aData) {
        data = aData;
    }

    public ManyToOneOtherEntity getOtherEntity() {
        return otherEntity;
    }

    public void setOtherEntity(ManyToOneOtherEntity aOtherEntity) {
        otherEntity = aOtherEntity;
    }

    @Override
    public String toString() {
        return super.toString() + "ManyToOneEntity [data=" + data + ", otherEntity=" + otherEntity + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((otherEntity == null) ? 0 : otherEntity.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ManyToOneEntity other = (ManyToOneEntity) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (otherEntity == null) {
            if (other.otherEntity != null)
                return false;
        } else if (!otherEntity.equals(other.otherEntity))
            return false;
        return true;
    }

}
