/*
 * TradingAccountRelationship.java created on 28 Sep 2010 17:36:12 by suggitpe for project masterfiles-receiver
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
 * Persistable domain class for a Trading Account Relationship.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_TA_RELATIONSHIP")
public class TradingAccountRelationship extends AbstractTradingAccount {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TradingAccountRelationship.class );

    @Column(name = "RELATIONSHIP_ID")
    private String relationshipId;

    @Column(name = "RELATIONSHIP_DOMAIN")
    private String relationShipDomain;

    @Column(name = "RELATIONSHIP_TYPE")
    private String relationshipType;

    @Temporal(TemporalType.DATE)
    @Column(name = "RELATIONSHIP_STATUS_DATE")
    private Date relationshipDate;

    @Column(name = "RELATIONSHIP_STATUS")
    private String relationshipStatus;

    /**
     * Constructs a new instance. Created for Hibernate only.
     */
    protected TradingAccountRelationship() {}

    /**
     * Constructs a new instance.
     */
    public TradingAccountRelationship( TradingAccountKey aTradingAccountKey, String aTradingAccountNK ) {
        super( aTradingAccountKey, aTradingAccountNK );
    }

    /**
     * Constructs a new instance.
     */
    public TradingAccountRelationship( TradingAccountKey aTradingAccountKey, String aTradingAccountNK,
                                       String aRelationshipId, String aRelationShipDomain,
                                       String aRelationshipType, Date aRelationshipDate,
                                       String aRelationshipStatus ) {
        this( aTradingAccountKey, aTradingAccountNK );

        relationshipId = aRelationshipId;
        relationShipDomain = aRelationShipDomain;
        relationshipType = aRelationshipType;
        relationshipDate = aRelationshipDate;
        relationshipStatus = aRelationshipStatus;
    }

    /**
     * Returns the value of relationshipId.
     * 
     * @return Returns the relationshipId.
     */
    public String getRelationshipId() {
        return relationshipId;
    }

    /**
     * Sets the relationshipId field to the specified value.
     * 
     * @param aRelationshipId
     *            The relationshipId to set.
     */
    public void setRelationshipId( String aRelationshipId ) {
        relationshipId = aRelationshipId;
    }

    /**
     * Returns the value of relationShipDomain.
     * 
     * @return Returns the relationShipDomain.
     */
    public String getRelationShipDomain() {
        return relationShipDomain;
    }

    /**
     * Sets the relationShipDomain field to the specified value.
     * 
     * @param aRelationShipDomain
     *            The relationShipDomain to set.
     */
    public void setRelationShipDomain( String aRelationShipDomain ) {
        relationShipDomain = aRelationShipDomain;
    }

    /**
     * Returns the value of relationshipType.
     * 
     * @return Returns the relationshipType.
     */
    public String getRelationshipType() {
        return relationshipType;
    }

    /**
     * Sets the relationshipType field to the specified value.
     * 
     * @param aRelationshipType
     *            The relationshipType to set.
     */
    public void setRelationshipType( String aRelationshipType ) {
        relationshipType = aRelationshipType;
    }

    /**
     * Returns the value of relationshipDate.
     * 
     * @return Returns the relationshipDate.
     */
    public Date getRelationshipDate() {
        return relationshipDate;
    }

    /**
     * Sets the relationshipDate field to the specified value.
     * 
     * @param aRelationshipDate
     *            The relationshipDate to set.
     */
    public void setRelationshipDate( Date aRelationshipDate ) {
        relationshipDate = aRelationshipDate;
    }

    /**
     * Returns the value of relationshipStatus.
     * 
     * @return Returns the relationshipStatus.
     */
    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    /**
     * Sets the relationshipStatus field to the specified value.
     * 
     * @param aRelationshipStatus
     *            The relationshipStatus to set.
     */
    public void setRelationshipStatus( String aRelationshipStatus ) {
        relationshipStatus = aRelationshipStatus;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( relationShipDomain == null ) ? 0 : relationShipDomain.hashCode() );
        result = prime * result + ( ( relationshipDate == null ) ? 0 : relationshipDate.hashCode() );
        result = prime * result + ( ( relationshipId == null ) ? 0 : relationshipId.hashCode() );
        result = prime * result + ( ( relationshipStatus == null ) ? 0 : relationshipStatus.hashCode() );
        result = prime * result + ( ( relationshipType == null ) ? 0 : relationshipType.hashCode() );
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
        TradingAccountRelationship other = (TradingAccountRelationship) obj;
        if ( relationShipDomain == null ) {
            if ( other.relationShipDomain != null )
                return false;
        }
        else if ( !relationShipDomain.equals( other.relationShipDomain ) )
            return false;
        if ( relationshipDate == null ) {
            if ( other.relationshipDate != null )
                return false;
        }
        else if ( !relationshipDate.equals( other.relationshipDate ) )
            return false;
        if ( relationshipId == null ) {
            if ( other.relationshipId != null )
                return false;
        }
        else if ( !relationshipId.equals( other.relationshipId ) )
            return false;
        if ( relationshipStatus == null ) {
            if ( other.relationshipStatus != null )
                return false;
        }
        else if ( !relationshipStatus.equals( other.relationshipStatus ) )
            return false;
        if ( relationshipType == null ) {
            if ( other.relationshipType != null )
                return false;
        }
        else if ( !relationshipType.equals( other.relationshipType ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TradingAccountRelationship [relationshipId=" + relationshipId + ", relationShipDomain="
               + relationShipDomain + ", relationshipType=" + relationshipType + ", relationshipDate="
               + relationshipDate + ", relationshipStatus=" + relationshipStatus + "]";
    }

}
