/*
 * LegalEntityKey.java created on 29 Sep 2010 08:39:36 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate the composite key for a Legal Entity.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2010
 */
@Embeddable
public class LegalEntityKey implements Serializable {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LegalEntityKey.class );
    private static final long serialVersionUID = -668455016806443178L;

    protected static final String ID_COLUMN_NAME = "LE_ID";
    protected static final String DOMAIN_COLUMN_NAME = "LE_DOMAIN";
    protected static final String VERSION_COLUMN_NAME = "LE_VERSION";

    @Column(name = ID_COLUMN_NAME)
    private Integer legalEntityId;

    @Column(name = DOMAIN_COLUMN_NAME)
    private String legalEntityDomain;

    @Column(name = VERSION_COLUMN_NAME)
    private Integer legalEntityVersion;

    /**
     * Constructs a new instance. Created for Hibernate only.
     */
    protected LegalEntityKey() {}

    /**
     * Constructs a new instance.
     */
    public LegalEntityKey( Integer aLegalEntityId, String aLegalEntityDomain, Integer alegalEntityVersion ) {
        legalEntityId = aLegalEntityId;
        legalEntityDomain = aLegalEntityDomain;
        legalEntityVersion = alegalEntityVersion;
    }

    /**
     * Returns the value of legalEntityId.
     * 
     * @return Returns the legalEntityId.
     */
    public Integer getLegalEntityId() {
        return legalEntityId;
    }

    /**
     * Sets the legalEntityId field to the specified value.
     * 
     * @param aLegalEntityId
     *            The legalEntityId to set.
     */
    public void setLegalEntityId( Integer aLegalEntityId ) {
        legalEntityId = aLegalEntityId;
    }

    /**
     * Returns the value of legalEntityDomain.
     * 
     * @return Returns the legalEntityDomain.
     */
    public String getLegalEntityDomain() {
        return legalEntityDomain;
    }

    /**
     * Sets the legalEntityDomain field to the specified value.
     * 
     * @param aLegalEntityDomain
     *            The legalEntityDomain to set.
     */
    public void setLegalEntityDomain( String aLegalEntityDomain ) {
        legalEntityDomain = aLegalEntityDomain;
    }

    /**
     * Returns the value of legalEntityVersion.
     * 
     * @return Returns the legalEntityVersion.
     */
    public Integer getLegalEntityVersion() {
        return legalEntityVersion;
    }

    /**
     * Sets the legalEntityVersion field to the specified value.
     * 
     * @param aLegalEntityVersion
     *            The legalEntityVersion to set.
     */
    public void setLegalEntityVersion( Integer aLegalEntityVersion ) {
        legalEntityVersion = aLegalEntityVersion;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( legalEntityDomain == null ) ? 0 : legalEntityDomain.hashCode() );
        result = prime * result + ( ( legalEntityId == null ) ? 0 : legalEntityId.hashCode() );
        result = prime * result + ( ( legalEntityVersion == null ) ? 0 : legalEntityVersion.hashCode() );
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
        LegalEntityKey other = (LegalEntityKey) obj;
        if ( legalEntityDomain == null ) {
            if ( other.legalEntityDomain != null )
                return false;
        }
        else if ( !legalEntityDomain.equals( other.legalEntityDomain ) )
            return false;
        if ( legalEntityId == null ) {
            if ( other.legalEntityId != null )
                return false;
        }
        else if ( !legalEntityId.equals( other.legalEntityId ) )
            return false;
        if ( legalEntityVersion == null ) {
            if ( other.legalEntityVersion != null )
                return false;
        }
        else if ( !legalEntityVersion.equals( other.legalEntityVersion ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LegalEntityKey [legalEntityId=" + legalEntityId + ", legalEntityDomain=" + legalEntityDomain
               + ", legalEntityVersion=" + legalEntityVersion + "]";
    }

}
