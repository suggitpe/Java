/*
 * ManyToManyUnidirectionalOtherEntity.java created on 22 Apr 2010 19:06:52 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytomany;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MANYTOMANY_UNI_OTHER_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MANYTOMANY_UNI_OTHER_ENTITY_SQ")
@SuppressWarnings("unused")
public class ManyToManyUnidirectionalOtherEntity extends EntityBase {

    private static final long serialVersionUID = 3433617405071711428L;

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
        return super.toString() + "ManyToManyUnidirectionalOtherEntity [otherData=" + otherData + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((otherData == null) ? 0 : otherData.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ManyToManyUnidirectionalOtherEntity other = (ManyToManyUnidirectionalOtherEntity) obj;
        if (otherData == null) {
            if (other.otherData != null)
                return false;
        } else if (!otherData.equals(other.otherData))
            return false;
        return true;
    }

}
