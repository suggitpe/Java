/*
 * ManyToOneOtherEntity.java created on 20 Apr 2010 19:15:01 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytoone;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MANYTOONE_OTHER_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MANYTOONE_OTHER_ENTITY_SQ")
@SuppressWarnings("unused")
public class ManyToOneOtherEntity extends EntityBase {

    @Column(name = "OTHER_DATA", length = 64)
    private String otherData;

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String aOtherData) {
        otherData = aOtherData;
    }

    @Override
    public String toString() {
        return super.toString() + "ManyToOneOtherEntity [otherData=" + otherData + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((otherData == null) ? 0 : otherData.hashCode());
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
        ManyToOneOtherEntity other = (ManyToOneOtherEntity) obj;
        if (otherData == null) {
            if (other.otherData != null)
                return false;
        } else if (!otherData.equals(other.otherData))
            return false;
        return true;
    }

}
