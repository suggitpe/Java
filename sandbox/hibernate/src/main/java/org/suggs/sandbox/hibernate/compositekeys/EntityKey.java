/*
 * EntityKey.java created on 18 Mar 2010 20:11:29 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositekeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@SuppressWarnings("unused")
public class EntityKey implements Serializable {

    private static final long serialVersionUID = -7663697310519488607L;

    @Column(name = "KEY_ONE", length = 64, nullable = false)
    private String keyOne;

    @Column(name = "KEY_TWO", length = 64, nullable = false)
    private String keyTwo;

    @Column(name = "KEY_THREE", length = 64, nullable = false)
    private String keyThree;

    public String getKeyOne() {
        return keyOne;
    }

    public void setKeyOne(String aKeyOne) {
        keyOne = aKeyOne;
    }

    public String getKeyTwo() {
        return keyTwo;
    }

    public void setKeyTwo(String aKeyTwo) {
        keyTwo = aKeyTwo;
    }

    public String getKeyThree() {
        return keyThree;
    }

    public void setKeyThree(String aKeyThree) {
        keyThree = aKeyThree;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((keyOne == null) ? 0 : keyOne.hashCode());
        result = prime * result + ((keyThree == null) ? 0 : keyThree.hashCode());
        result = prime * result + ((keyTwo == null) ? 0 : keyTwo.hashCode());
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
        EntityKey other = (EntityKey) obj;
        if (keyOne == null) {
            if (other.keyOne != null)
                return false;
        } else if (!keyOne.equals(other.keyOne))
            return false;
        if (keyThree == null) {
            if (other.keyThree != null)
                return false;
        } else if (!keyThree.equals(other.keyThree))
            return false;
        if (keyTwo == null) {
            if (other.keyTwo != null)
                return false;
        } else if (!keyTwo.equals(other.keyTwo))
            return false;
        return true;
    }

}
