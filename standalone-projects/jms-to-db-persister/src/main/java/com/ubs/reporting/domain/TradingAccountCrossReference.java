/*
 * TradingAccountCrossReference.java created on 28 Sep 2010 17:35:41 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Persistable domain class for a Trading Account Cross Reference.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_TA_CROSS_REF")
public class TradingAccountCrossReference extends AbstractTradingAccount {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TradingAccountCrossReference.class );
    private static final long serialVersionUID = -7228437206417707676L;

    @Column(name = "ALT_IDENTIFIER_ID")
    private String altIdentifierId;

    @Column(name = "ALT_IDENTIFIER_DOMAIN")
    private String altIdentifierDomain;

    @Column(name = "ALT_DOMAIN_CODE")
    private String altDomainCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "ALT_IDENTIFIER_STATUS_DATE")
    private Date altIdentifierStatusDate;

    @Column(name = "ALT_IDENTIFIER_STATUS")
    private String altIdentifierStatus;

    @Column(name = "ALT_IDENTIFIER_BLOCKED")
    private String altIdentifierBlocked;

    /**
     * Constructs a new instance. Created for Hibernate only.
     */
    protected TradingAccountCrossReference() {}

    /**
     * Constructs a new instance.
     */
    public TradingAccountCrossReference( TradingAccountKey aTradingAccountKey, String aTradingAccountNK ) {
        super( aTradingAccountKey, aTradingAccountNK );
    }

    /**
     * Constructs a new instance.
     */
    public TradingAccountCrossReference( TradingAccountKey aTradingAccountKey, String aTradingAccountNK,
                                         String aAltIdentifierId, String aAltIdentifierDomain,
                                         String aAltDomainCode, Date aAltIdentifierStatusDate,
                                         String aAltIdentifierStatus, String aAltIdentifierBlocked ) {
        this( aTradingAccountKey, aTradingAccountNK );

        altIdentifierId = aAltIdentifierId;
        altIdentifierDomain = aAltIdentifierDomain;
        altDomainCode = aAltDomainCode;
        altIdentifierStatusDate = aAltIdentifierStatusDate;
        altIdentifierStatus = aAltIdentifierStatus;
        altIdentifierBlocked = aAltIdentifierBlocked;
    }

    /**
     * Returns the value of altIdentifierId.
     * 
     * @return Returns the altIdentifierId.
     */
    public String getAltIdentifierId() {
        return altIdentifierId;
    }

    /**
     * Sets the altIdentifierId field to the specified value.
     * 
     * @param aAltIdentifierId
     *            The altIdentifierId to set.
     */
    public void setAltIdentifierId( String aAltIdentifierId ) {
        altIdentifierId = aAltIdentifierId;
    }

    /**
     * Returns the value of altIdentifierDomain.
     * 
     * @return Returns the altIdentifierDomain.
     */
    public String getAltIdentifierDomain() {
        return altIdentifierDomain;
    }

    /**
     * Sets the altIdentifierDomain field to the specified value.
     * 
     * @param aAltIdentifierDomain
     *            The altIdentifierDomain to set.
     */
    public void setAltIdentifierDomain( String aAltIdentifierDomain ) {
        altIdentifierDomain = aAltIdentifierDomain;
    }

    /**
     * Returns the value of altDomainCode.
     * 
     * @return Returns the altDomainCode.
     */
    public String getAltDomainCode() {
        return altDomainCode;
    }

    /**
     * Sets the altDomainCode field to the specified value.
     * 
     * @param aAltDomainCode
     *            The altDomainCode to set.
     */
    public void setAltDomainCode( String aAltDomainCode ) {
        altDomainCode = aAltDomainCode;
    }

    /**
     * Returns the value of altIdentifierStatusDate.
     * 
     * @return Returns the altIdentifierStatusDate.
     */
    public Date getAltIdentifierStatusDate() {
        return altIdentifierStatusDate;
    }

    /**
     * Sets the altIdentifierStatusDate field to the specified value.
     * 
     * @param aAltIdentifierStatusDate
     *            The altIdentifierStatusDate to set.
     */
    public void setAltIdentifierStatusDate( Date aAltIdentifierStatusDate ) {
        altIdentifierStatusDate = aAltIdentifierStatusDate;
    }

    /**
     * Returns the value of altIdentifierStatus.
     * 
     * @return Returns the altIdentifierStatus.
     */
    public String getAltIdentifierStatus() {
        return altIdentifierStatus;
    }

    /**
     * Sets the altIdentifierStatus field to the specified value.
     * 
     * @param aAltIdentifierStatus
     *            The altIdentifierStatus to set.
     */
    public void setAltIdentifierStatus( String aAltIdentifierStatus ) {
        altIdentifierStatus = aAltIdentifierStatus;
    }

    /**
     * Returns the value of altIdentifierBlocked.
     * 
     * @return Returns the altIdentifierBlocked.
     */
    public String getAltIdentifierBlocked() {
        return altIdentifierBlocked;
    }

    /**
     * Sets the altIdentifierBlocked field to the specified value.
     * 
     * @param aAltIdentifierBlocked
     *            The altIdentifierBlocked to set.
     */
    public void setAltIdentifierBlocked( String aAltIdentifierBlocked ) {
        altIdentifierBlocked = aAltIdentifierBlocked;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( altDomainCode == null ) ? 0 : altDomainCode.hashCode() );
        result = prime * result + ( ( altIdentifierBlocked == null ) ? 0 : altIdentifierBlocked.hashCode() );
        result = prime * result + ( ( altIdentifierDomain == null ) ? 0 : altIdentifierDomain.hashCode() );
        result = prime * result + ( ( altIdentifierId == null ) ? 0 : altIdentifierId.hashCode() );
        result = prime * result + ( ( altIdentifierStatus == null ) ? 0 : altIdentifierStatus.hashCode() );
        result = prime * result
                 + ( ( altIdentifierStatusDate == null ) ? 0 : altIdentifierStatusDate.hashCode() );
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
        TradingAccountCrossReference other = (TradingAccountCrossReference) obj;
        if ( altDomainCode == null ) {
            if ( other.altDomainCode != null )
                return false;
        }
        else if ( !altDomainCode.equals( other.altDomainCode ) )
            return false;
        if ( altIdentifierBlocked == null ) {
            if ( other.altIdentifierBlocked != null )
                return false;
        }
        else if ( !altIdentifierBlocked.equals( other.altIdentifierBlocked ) )
            return false;
        if ( altIdentifierDomain == null ) {
            if ( other.altIdentifierDomain != null )
                return false;
        }
        else if ( !altIdentifierDomain.equals( other.altIdentifierDomain ) )
            return false;
        if ( altIdentifierId == null ) {
            if ( other.altIdentifierId != null )
                return false;
        }
        else if ( !altIdentifierId.equals( other.altIdentifierId ) )
            return false;
        if ( altIdentifierStatus == null ) {
            if ( other.altIdentifierStatus != null )
                return false;
        }
        else if ( !altIdentifierStatus.equals( other.altIdentifierStatus ) )
            return false;
        if ( altIdentifierStatusDate == null ) {
            if ( other.altIdentifierStatusDate != null )
                return false;
        }
        else if ( !altIdentifierStatusDate.equals( other.altIdentifierStatusDate ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TradingAccountCrossReference [altIdentifierId=" + altIdentifierId + ", altIdentifierDomain="
               + altIdentifierDomain + ", altDomainCode=" + altDomainCode + ", altIdentifierStatusDate="
               + altIdentifierStatusDate + ", altIdentifierStatus=" + altIdentifierStatus
               + ", altIdentifierBlocked=" + altIdentifierBlocked + "]";
    }

}
