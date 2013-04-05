/*
 * Entity.java created on 18 Mar 2010 20:13:45 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositekeys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ENTITY_OBJECT")
@SuppressWarnings("unused")
public class EntityObject implements Serializable {

    private static final long serialVersionUID = 4390402943883172225L;

    @Id
    private EntityKey key;

    @Column(name = "DATA_ONE", nullable = true, length = 64)
    private String dataOne;

    @Column(name = "DATA_TWO", nullable = true, length = 64)
    private String dataTwo;

    public EntityKey getKey() {
        return key;
    }

    public void setKey(EntityKey aKey) {
        key = aKey;
    }

    public String getDataOne() {
        return dataOne;
    }

    public void setDataOne(String aDataOne) {
        dataOne = aDataOne;
    }

    public String getDataTwo() {
        return dataTwo;
    }

    public void setDataTwo(String aDataTwo) {
        dataTwo = aDataTwo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataOne == null) ? 0 : dataOne.hashCode());
        result = prime * result + ((dataTwo == null) ? 0 : dataTwo.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
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
        EntityObject other = (EntityObject) obj;
        if (dataOne == null) {
            if (other.dataOne != null)
                return false;
        } else if (!dataOne.equals(other.dataOne))
            return false;
        if (dataTwo == null) {
            if (other.dataTwo != null)
                return false;
        } else if (!dataTwo.equals(other.dataTwo))
            return false;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }

}
