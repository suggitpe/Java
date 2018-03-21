/*
 * ReallyBasicEntity.java created on 22 Dec 2010 18:55:55 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.basicentity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "BASIC_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "BASIC_ENTITY_SQ")
@SuppressWarnings("unused")
public class ReallyBasicEntity extends EntityBase {

    @Column(name = "SOME_STRING", length = 50)
    private String someString;

    @Column(name = "SOME_INT")
    private int someInteger;

    @Column(name = "SOME_DATE")
    private Date someDate;

    public ReallyBasicEntity() {
    }

    public ReallyBasicEntity(String aString, int aInt, Date aDate) {
        someString = aString;
        someInteger = aInt;
        someDate = aDate;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String aSomeString) {
        someString = aSomeString;
    }

    public int getSomeInteger() {
        return someInteger;
    }

    public void setSomeInteger(int aSomeInteger) {
        someInteger = aSomeInteger;
    }

    public Date getSomeDate() {
        return someDate;
    }

    public void setSomeDate(Date aSomeDate) {
        someDate = aSomeDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((someDate == null) ? 0 : someDate.hashCode());
        result = prime * result + someInteger;
        result = prime * result + ((someString == null) ? 0 : someString.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReallyBasicEntity other = (ReallyBasicEntity) obj;
        if (someDate == null) {
            if (other.someDate != null)
                return false;
        } else if (!someDate.equals(other.someDate))
            return false;
        if (someInteger != other.someInteger)
            return false;
        if (someString == null) {
            if (other.someString != null)
                return false;
        } else if (!someString.equals(other.someString))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " ReallyBasicEntity [someString=" + someString + ", someInteger="
                + someInteger + ", someDate=" + someDate + "]";
    }

}
