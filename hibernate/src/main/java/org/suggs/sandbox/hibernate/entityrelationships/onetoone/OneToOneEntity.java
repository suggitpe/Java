/*
 * OneToOneEntity.java created on 20 Apr 2010 07:16:57 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetoone;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.*;

@Entity
@Table(name = "ONETOONE_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "ONETOONE_ENTITY_SQ")
@SuppressWarnings("unused")
public class OneToOneEntity extends EntityBase {

    @Column(name = "data", length = 64)
    private String data;

    // uses the other entity primary key (id) as a map
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OTHER_ENTITY_ID")
    private OneToOneOtherEntity otherEntity;

    public String getData() {
        return data;
    }

    public void setData(String aData) {
        data = aData;
    }

    public OneToOneOtherEntity getOtherEntity() {
        return otherEntity;
    }

    public void setOtherEntity(OneToOneOtherEntity aOtherEntity) {
        otherEntity = aOtherEntity;
    }

    @Override
    public String toString() {
        return super.toString() + "OneToOneEntity [data=" + data + ", otherEntity=" + otherEntity + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((otherEntity == null) ? 0 : otherEntity.hashCode());
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
        OneToOneEntity other = (OneToOneEntity) obj;
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
