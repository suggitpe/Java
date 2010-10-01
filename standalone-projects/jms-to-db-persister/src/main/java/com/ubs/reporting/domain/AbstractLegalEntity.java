/*
 * AbstractLegalEntity.java created on 28 Sep 2010 19:24:27 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract persistable domain class for all Legal Entity classes.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@MappedSuperclass
public abstract class AbstractLegalEntity {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( AbstractLegalEntity.class );
    private static final long serialVersionUID = -3860102942085240160L;

    @Id
    private LegalEntityKey legalEntityKey;

    @Column(name = "LEGAL_NAME")
    private String legalName;

    /**
     * Constructs a new instance. Created for Hibernate only.
     */
    protected AbstractLegalEntity() {}

    /**
     * Constructs a new instance.
     */
    public AbstractLegalEntity( LegalEntityKey aLegalEntityKey, String aLegalName ) {
        legalEntityKey = aLegalEntityKey;
        legalName = aLegalName;
    }

    /**
     * Returns the value of legalEntityKey.
     * 
     * @return Returns the legalEntityKey.
     */
    public LegalEntityKey getLegalEntityKey() {
        return legalEntityKey;
    }

    /**
     * Sets the legalEntityKey field to the specified value.
     * 
     * @param aLegalEntityKey
     *            The legalEntityKey to set.
     */
    public void setLegalEntityKey( LegalEntityKey aLegalEntityKey ) {
        legalEntityKey = aLegalEntityKey;
    }

    /**
     * Returns the value of legalName.
     * 
     * @return Returns the legalName.
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * Sets the legalName field to the specified value.
     * 
     * @param aLegalName
     *            The legalName to set.
     */
    public void setLegalName( String aLegalName ) {
        legalName = aLegalName;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( legalEntityKey == null ) ? 0 : legalEntityKey.hashCode() );
        result = prime * result + ( ( legalName == null ) ? 0 : legalName.hashCode() );
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
        AbstractLegalEntity other = (AbstractLegalEntity) obj;
        if ( legalEntityKey == null ) {
            if ( other.legalEntityKey != null )
                return false;
        }
        else if ( !legalEntityKey.equals( other.legalEntityKey ) )
            return false;
        if ( legalName == null ) {
            if ( other.legalName != null )
                return false;
        }
        else if ( !legalName.equals( other.legalName ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AbstractLegalEntity [legalEntityKey=" + legalEntityKey + ", legalName=" + legalName + "]";
    }

}
