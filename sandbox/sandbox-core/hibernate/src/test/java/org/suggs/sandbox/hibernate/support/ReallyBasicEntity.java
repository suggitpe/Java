/*
 * ReallyBasicEntity.java created on 22 Dec 2010 18:55:55 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Really basic entity that allows us to verify how the persistence level works at a basic level.
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2010
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "BASIC_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "BASIC_ENTITY_SQ")
public class ReallyBasicEntity extends EntityBase {

    private static final long serialVersionUID = 3654518492764839027L;

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReallyBasicEntity.class );

    @Column(name = "SOME_STRING", length = 50)
    private String someString;

    @Column(name = "SOME_INT")
    private int someInteger;

    @Column(name = "SOME_DATE")
    private Date someDate;

    public ReallyBasicEntity() {}

    public ReallyBasicEntity( String aString, int aInt, Date aDate ) {
        someString = aString;
        someInteger = aInt;
        someDate = aDate;
    }

    /**
     * Returns the value of someString.
     * 
     * @return Returns the someString.
     */
    public String getSomeString() {
        return someString;
    }

    /**
     * Sets the someString field to the specified value.
     * 
     * @param aSomeString
     *            The someString to set.
     */
    public void setSomeString( String aSomeString ) {
        someString = aSomeString;
    }

    /**
     * Returns the value of someInteger.
     * 
     * @return Returns the someInteger.
     */
    public int getSomeInteger() {
        return someInteger;
    }

    /**
     * Sets the someInteger field to the specified value.
     * 
     * @param aSomeInteger
     *            The someInteger to set.
     */
    public void setSomeInteger( int aSomeInteger ) {
        someInteger = aSomeInteger;
    }

    /**
     * Returns the value of someDate.
     * 
     * @return Returns the someDate.
     */
    public Date getSomeDate() {
        return someDate;
    }

    /**
     * Sets the someDate field to the specified value.
     * 
     * @param aSomeDate
     *            The someDate to set.
     */
    public void setSomeDate( Date aSomeDate ) {
        someDate = aSomeDate;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( someDate == null ) ? 0 : someDate.hashCode() );
        result = prime * result + someInteger;
        result = prime * result + ( ( someString == null ) ? 0 : someString.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( !super.equals( obj ) )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        ReallyBasicEntity other = (ReallyBasicEntity) obj;
        if ( someDate == null ) {
            if ( other.someDate != null )
                return false;
        }
        else if ( !someDate.equals( other.someDate ) )
            return false;
        if ( someInteger != other.someInteger )
            return false;
        if ( someString == null ) {
            if ( other.someString != null )
                return false;
        }
        else if ( !someString.equals( other.someString ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + " ReallyBasicEntity [someString=" + someString + ", someInteger="
               + someInteger + ", someDate=" + someDate + "]";
    }

}
