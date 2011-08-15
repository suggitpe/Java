/*
 * CoreEntity.java created on 22 Sep 2010 19:47:58 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositeentity;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity that contains a composite entity
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2010
 */
@Entity
@Table(name = "COMPOSITE_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "COMPOSITE_ENTITY_SQ")
public class CoreEntity extends EntityBase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CoreEntity.class );

    private static final long serialVersionUID = 4104349939216946228L;

    @Column(name = "SOME_TEXT", nullable = false, length = 255)
    private String someText;

    @Embedded
    private CompositeEntity compositeEntity;

    /**
     * Constructs a new instance.
     */
    public CoreEntity() {}

    /**
     * Constructs a new instance.
     * 
     * @param aTextString
     *            some text to drop into the entity
     * @param aCompositeEntity
     *            composit entity to verity
     */
    public CoreEntity( String aTextString, CompositeEntity aCompositeEntity ) {
        someText = aTextString;
        compositeEntity = aCompositeEntity;
    }

    /**
     * Returns the value of someText.
     * 
     * @return Returns the someText.
     */
    public String getSomeText() {
        return someText;
    }

    /**
     * Sets the someText field to the specified value.
     * 
     * @param aSomeText
     *            The someText to set.
     */
    public void setSomeText( String aSomeText ) {
        someText = aSomeText;
    }

    /**
     * Returns the value of compositeEntity.
     * 
     * @return Returns the compositeEntity.
     */
    public CompositeEntity getCompositeEntity() {
        return compositeEntity;
    }

    /**
     * Sets the compositeEntity field to the specified value.
     * 
     * @param aCompositeEntity
     *            The compositeEntity to set.
     */
    public void setCompositeEntity( CompositeEntity aCompositeEntity ) {
        compositeEntity = aCompositeEntity;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( compositeEntity == null ) ? 0 : compositeEntity.hashCode() );
        result = prime * result + ( ( someText == null ) ? 0 : someText.hashCode() );
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
        CoreEntity other = (CoreEntity) obj;
        if ( compositeEntity == null ) {
            if ( other.compositeEntity != null )
                return false;
        }
        else if ( !compositeEntity.equals( other.compositeEntity ) )
            return false;
        if ( someText == null ) {
            if ( other.someText != null )
                return false;
        }
        else if ( !someText.equals( other.someText ) )
            return false;
        return true;
    }

}
