/*
 * LegalEntityCrossReference.java created on 28 Sep 2010 17:34:26 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Persistable domain class for a Legal Entity Cross Reference.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_LE_CROSS_REF")
public class LegalEntityCrossReference extends AbstractLegalEntity {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( LegalEntityCrossReference.class );

    @Column(name = "ALT_IDENTIFIER_ID")
    private String altIdId;

    @Column(name = "ALT_IDENTIFIER_DOMAIN")
    private String altIdDomain;

    @Column(name = "ALT_DOMAIN_CODE")
    private String altIdDomainCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "ALT_IDENTIFIER_STATUS_DATE")
    private Date altIdStatusDate;

    @Column(name = "ALT_IDENTIFIER_STATUS")
    private String altIdStatus;

    /**
     * Constructs a new instance. Created for Hibernate only.
     */
    protected LegalEntityCrossReference() {}

    /**
     * Constructs a new instance.
     */
    public LegalEntityCrossReference( LegalEntityKey aLegalEntityKey, String aLegalName ) {
        super( aLegalEntityKey, aLegalName );
    }

    /**
     * Constructs a new instance.
     */
    public LegalEntityCrossReference( LegalEntityKey aLegalEntityKey, String aLegalName, String aAltIdId,
                                      String aAltIdDomain, String aAltIdDomainCode, Date aAltIdStatusDate,
                                      String aAltIdStatus ) {
        this( aLegalEntityKey, aLegalName );

        altIdId = aAltIdId;
        altIdDomain = aAltIdDomain;
        altIdDomainCode = aAltIdDomainCode;
        altIdStatusDate = aAltIdStatusDate;
        altIdStatus = aAltIdStatus;
    }

    /**
     * Returns the value of altIdId.
     * 
     * @return Returns the altIdId.
     */
    public String getAltIdId() {
        return altIdId;
    }

    /**
     * Sets the altIdId field to the specified value.
     * 
     * @param aAltIdId
     *            The altIdId to set.
     */
    public void setAltIdId( String aAltIdId ) {
        altIdId = aAltIdId;
    }

    /**
     * Returns the value of altIdDomain.
     * 
     * @return Returns the altIdDomain.
     */
    public String getAltIdDomain() {
        return altIdDomain;
    }

    /**
     * Sets the altIdDomain field to the specified value.
     * 
     * @param aAltIdDomain
     *            The altIdDomain to set.
     */
    public void setAltIdDomain( String aAltIdDomain ) {
        altIdDomain = aAltIdDomain;
    }

    /**
     * Returns the value of altIdDomainCode.
     * 
     * @return Returns the altIdDomainCode.
     */
    public String getAltIdDomainCode() {
        return altIdDomainCode;
    }

    /**
     * Sets the altIdDomainCode field to the specified value.
     * 
     * @param aAltIdDomainCode
     *            The altIdDomainCode to set.
     */
    public void setAltIdDomainCode( String aAltIdDomainCode ) {
        altIdDomainCode = aAltIdDomainCode;
    }

    /**
     * Returns the value of altIdStatusDate.
     * 
     * @return Returns the altIdStatusDate.
     */
    public Date getAltIdStatusDate() {
        return altIdStatusDate;
    }

    /**
     * Sets the altIdStatusDate field to the specified value.
     * 
     * @param aAltIdStatusDate
     *            The altIdStatusDate to set.
     */
    public void setAltIdStatusDate( Date aAltIdStatusDate ) {
        altIdStatusDate = aAltIdStatusDate;
    }

    /**
     * Returns the value of altIdStatus.
     * 
     * @return Returns the altIdStatus.
     */
    public String getAltIdStatus() {
        return altIdStatus;
    }

    /**
     * Sets the altIdStatus field to the specified value.
     * 
     * @param aAltIdStatus
     *            The altIdStatus to set.
     */
    public void setAltIdStatus( String aAltIdStatus ) {
        altIdStatus = aAltIdStatus;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( altIdDomain == null ) ? 0 : altIdDomain.hashCode() );
        result = prime * result + ( ( altIdDomainCode == null ) ? 0 : altIdDomainCode.hashCode() );
        result = prime * result + ( ( altIdId == null ) ? 0 : altIdId.hashCode() );
        result = prime * result + ( ( altIdStatus == null ) ? 0 : altIdStatus.hashCode() );
        result = prime * result + ( ( altIdStatusDate == null ) ? 0 : altIdStatusDate.hashCode() );
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
        LegalEntityCrossReference other = (LegalEntityCrossReference) obj;
        if ( altIdDomain == null ) {
            if ( other.altIdDomain != null )
                return false;
        }
        else if ( !altIdDomain.equals( other.altIdDomain ) )
            return false;
        if ( altIdDomainCode == null ) {
            if ( other.altIdDomainCode != null )
                return false;
        }
        else if ( !altIdDomainCode.equals( other.altIdDomainCode ) )
            return false;
        if ( altIdId == null ) {
            if ( other.altIdId != null )
                return false;
        }
        else if ( !altIdId.equals( other.altIdId ) )
            return false;
        if ( altIdStatus == null ) {
            if ( other.altIdStatus != null )
                return false;
        }
        else if ( !altIdStatus.equals( other.altIdStatus ) )
            return false;
        if ( altIdStatusDate == null ) {
            if ( other.altIdStatusDate != null )
                return false;
        }
        else if ( !altIdStatusDate.equals( other.altIdStatusDate ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LegalEntityCrossReference [altIdId=" + altIdId + ", altIdDomain=" + altIdDomain
               + ", altIdDomainCode=" + altIdDomainCode + ", altIdStatusDate=" + altIdStatusDate
               + ", altIdStatus=" + altIdStatus + "]";
    }

}
