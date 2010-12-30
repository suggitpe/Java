/*
 * ReallyBasicEntity.java created on 22 Dec 2010 18:55:55 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

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
@Table(name = "BASIC_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "BASIC_ENTITY_SQ")
public class ReallyBasicEntity extends EntityBase {

    private static final long serialVersionUID = 3654518492764839027L;

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReallyBasicEntity.class );

    @Column(name = "STRING_FIELD", length = 50)
    private String stringField;

    @Column(name = "INT_FIELD")
    private int intField;

    public ReallyBasicEntity() {}

    public ReallyBasicEntity( String aString, int aInt ) {
        stringField = aString;
        intField = aInt;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + ": ReallyBasicEntity [stringField=" + stringField + ", intField="
               + intField + "]";
    }

    /**
     * Returns the value of stringField.
     * 
     * @return Returns the stringField.
     */
    public String getStringField() {
        return stringField;
    }

    /**
     * Sets the stringField field to the specified value.
     * 
     * @param aStringField
     *            The stringField to set.
     */
    public void setStringField( String aStringField ) {
        stringField = aStringField;
    }

    /**
     * Returns the value of intField.
     * 
     * @return Returns the intField.
     */
    public int getIntField() {
        return intField;
    }

    /**
     * Sets the intField field to the specified value.
     * 
     * @param aIntField
     *            The intField to set.
     */
    public void setIntField( int aIntField ) {
        intField = aIntField;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + intField;
        result = prime * result + ( ( stringField == null ) ? 0 : stringField.hashCode() );
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
        if ( intField != other.intField )
            return false;
        if ( stringField == null ) {
            if ( other.stringField != null )
                return false;
        }
        else if ( !stringField.equals( other.stringField ) )
            return false;
        return true;
    }

}
