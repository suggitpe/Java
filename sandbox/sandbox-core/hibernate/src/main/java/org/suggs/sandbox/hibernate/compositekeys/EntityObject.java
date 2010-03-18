/*
 * Entity.java created on 18 Mar 2010 20:13:45 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositekeys;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class to represent a data object, using EntityKey as it's composite
 * key.
 * 
 * @author suggitpe
 * @version 1.0 18 Mar 2010
 */
@Entity
@Table(name = "MESSAGES")
public class EntityObject implements Serializable
{

    EntityKey key;
    String dataOne;
    String dataTwo;

    /**
     * Returns the value of key.
     * 
     * @return Returns the key.
     */
    @Id
    @AttributeOverrides( {
                          @AttributeOverride(name = "keyOne", column = @Column(name = "KEY_ONE")),
                          @AttributeOverride(name = "keyTwo", column = @Column(name = "KEY_TWO")),
                          @AttributeOverride(name = "keyThree", column = @Column(name = "KET_THREE")) })
    public EntityKey getKey()
    {
        return key;
    }

    /**
     * Sets the key field to the specified value.
     * 
     * @param aKey
     *            The key to set.
     */
    public void setKey( EntityKey aKey )
    {
        key = aKey;
    }

    /**
     * Returns the value of dataOne.
     * 
     * @return Returns the dataOne.
     */
    @Column(name = "DATA_ONE", nullable = true, length = 64)
    public String getDataOne()
    {
        return dataOne;
    }

    /**
     * Sets the dataOne field to the specified value.
     * 
     * @param aDataOne
     *            The dataOne to set.
     */
    public void setDataOne( String aDataOne )
    {
        dataOne = aDataOne;
    }

    /**
     * Returns the value of dataTwo.
     * 
     * @return Returns the dataTwo.
     */
    @Column(name = "DATA_TWO", nullable = true, length = 64)
    public String getDataTwo()
    {
        return dataTwo;
    }

    /**
     * Sets the dataTwo field to the specified value.
     * 
     * @param aDataTwo
     *            The dataTwo to set.
     */
    public void setDataTwo( String aDataTwo )
    {
        dataTwo = aDataTwo;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( dataOne == null ) ? 0 : dataOne.hashCode() );
        result = prime * result + ( ( dataTwo == null ) ? 0 : dataTwo.hashCode() );
        result = prime * result + ( ( key == null ) ? 0 : key.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        EntityObject other = (EntityObject) obj;
        if ( dataOne == null )
        {
            if ( other.dataOne != null )
                return false;
        }
        else if ( !dataOne.equals( other.dataOne ) )
            return false;
        if ( dataTwo == null )
        {
            if ( other.dataTwo != null )
                return false;
        }
        else if ( !dataTwo.equals( other.dataTwo ) )
            return false;
        if ( key == null )
        {
            if ( other.key != null )
                return false;
        }
        else if ( !key.equals( other.key ) )
            return false;
        return true;
    }

}
