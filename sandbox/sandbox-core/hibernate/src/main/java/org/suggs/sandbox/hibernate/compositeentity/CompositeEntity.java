/*
 * CompositeEntity.java created on 22 Sep 2010 19:48:10 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositeentity;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Write javadoc for CompositeEntity
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2010
 */
@Embeddable
public class CompositeEntity extends EntityBase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CompositeEntity.class );

    @Column(name = "COMP_TEXT", nullable = false, length = 255)
    private String compositeText;

    @Column(name = "COMP_INT", nullable = false)
    private Integer compositeInteger;

    /**
     * Constructs a new instance.
     */
    public CompositeEntity() {}

    /**
     * Constructs a new instance.
     * 
     * @param aCompositeText
     * @param aCompositeInteger
     */
    public CompositeEntity( String aCompositeText, Integer aCompositeInteger ) {
        compositeText = aCompositeText;
        compositeInteger = aCompositeInteger;
    }

    /**
     * Returns the value of compositeText.
     * 
     * @return Returns the compositeText.
     */
    public String getCompositeText() {
        return compositeText;
    }

    /**
     * Sets the compositeText field to the specified value.
     * 
     * @param aCompositeText
     *            The compositeText to set.
     */
    public void setCompositeText( String aCompositeText ) {
        compositeText = aCompositeText;
    }

    /**
     * Returns the value of compositeInteger.
     * 
     * @return Returns the compositeInteger.
     */
    public Integer getCompositeInteger() {
        return compositeInteger;
    }

    /**
     * Sets the compositeInteger field to the specified value.
     * 
     * @param aCompositeInteger
     *            The compositeInteger to set.
     */
    public void setCompositeInteger( Integer aCompositeInteger ) {
        compositeInteger = aCompositeInteger;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( compositeInteger == null ) ? 0 : compositeInteger.hashCode() );
        result = prime * result + ( ( compositeText == null ) ? 0 : compositeText.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CompositeEntity other = (CompositeEntity) obj;
        if ( compositeInteger == null ) {
            if ( other.compositeInteger != null )
                return false;
        }
        else if ( !compositeInteger.equals( other.compositeInteger ) )
            return false;
        if ( compositeText == null ) {
            if ( other.compositeText != null )
                return false;
        }
        else if ( !compositeText.equals( other.compositeText ) )
            return false;
        return true;
    }

}
