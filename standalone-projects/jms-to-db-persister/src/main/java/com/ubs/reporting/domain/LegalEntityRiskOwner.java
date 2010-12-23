/*
 * LegalEntityRiskOwner.java created on 28 Sep 2010 17:34:43 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Persistable domain class for a Legal Entity Risk Owner.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_LE_RISK_OWNER")
public class LegalEntityRiskOwner extends AbstractLegalEntity {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( LegalEntityRiskOwner.class );

    @Column(name = "RISK_OWNER_TYPE")
    private String riskOwnerType;

    @Column(name = "RISK_OWNER_ROLE")
    private String riskOwnerRole;

    @Column(name = "RISK_OWNER_CODE")
    private String riskOwnerCode;

    @Column(name = "RISK_OWNER_DESC")
    private String riskOwnerDescription;

    @Column(name = "PRIMARY_CONTACT")
    private String primaryContact;

    @Column(name = "OWNING_GROUP_CODE")
    private String owningGroupCode;

    /**
     * Constructs a new instance. Created for Hibernate only.
     */
    protected LegalEntityRiskOwner() {}

    /**
     * Constructs a new instance.
     */
    public LegalEntityRiskOwner( LegalEntityKey aLegalEntityKey, String aLegalName ) {
        super( aLegalEntityKey, aLegalName );
    }

    /**
     * Constructs a new instance.
     */
    public LegalEntityRiskOwner( LegalEntityKey aLegalEntityKey, String aLegalName, String aRiskOwnerType,
                                 String aRiskOwnerRole, String aRiskOwnerCode, String aRiskOwnerDesciption,
                                 String aPrimaryContact, String aOwningGroupCode ) {
        this( aLegalEntityKey, aLegalName );

        riskOwnerType = aRiskOwnerType;
        riskOwnerRole = aRiskOwnerRole;
        riskOwnerCode = aRiskOwnerCode;
        riskOwnerDescription = aRiskOwnerDesciption;
        primaryContact = aPrimaryContact;
        owningGroupCode = aOwningGroupCode;
    }

    /**
     * Returns the value of riskOwnerType.
     * 
     * @return Returns the riskOwnerType.
     */
    public String getRiskOwnerType() {
        return riskOwnerType;
    }

    /**
     * Sets the riskOwnerType field to the specified value.
     * 
     * @param aRiskOwnerType
     *            The riskOwnerType to set.
     */
    public void setRiskOwnerType( String aRiskOwnerType ) {
        riskOwnerType = aRiskOwnerType;
    }

    /**
     * Returns the value of riskOwnerRole.
     * 
     * @return Returns the riskOwnerRole.
     */
    public String getRiskOwnerRole() {
        return riskOwnerRole;
    }

    /**
     * Sets the riskOwnerRole field to the specified value.
     * 
     * @param aRiskOwnerRole
     *            The riskOwnerRole to set.
     */
    public void setRiskOwnerRole( String aRiskOwnerRole ) {
        riskOwnerRole = aRiskOwnerRole;
    }

    /**
     * Returns the value of riskOwnerCode.
     * 
     * @return Returns the riskOwnerCode.
     */
    public String getRiskOwnerCode() {
        return riskOwnerCode;
    }

    /**
     * Sets the riskOwnerCode field to the specified value.
     * 
     * @param aRiskOwnerCode
     *            The riskOwnerCode to set.
     */
    public void setRiskOwnerCode( String aRiskOwnerCode ) {
        riskOwnerCode = aRiskOwnerCode;
    }

    /**
     * Returns the value of riskOwnerDescription.
     * 
     * @return Returns the riskOwnerDescription.
     */
    public String getRiskOwnerDescription() {
        return riskOwnerDescription;
    }

    /**
     * Sets the riskOwnerDescription field to the specified value.
     * 
     * @param aRiskOwnerDescription
     *            The riskOwnerDescription to set.
     */
    public void setRiskOwnerDescription( String aRiskOwnerDescription ) {
        riskOwnerDescription = aRiskOwnerDescription;
    }

    /**
     * Returns the value of primaryContact.
     * 
     * @return Returns the primaryContact.
     */
    public String getPrimaryContact() {
        return primaryContact;
    }

    /**
     * Sets the primaryContact field to the specified value.
     * 
     * @param aPrimaryContact
     *            The primaryContact to set.
     */
    public void setPrimaryContact( String aPrimaryContact ) {
        primaryContact = aPrimaryContact;
    }

    /**
     * Returns the value of owningGroupCode.
     * 
     * @return Returns the owningGroupCode.
     */
    public String getOwningGroupCode() {
        return owningGroupCode;
    }

    /**
     * Sets the owningGroupCode field to the specified value.
     * 
     * @param aOwningGroupCode
     *            The owningGroupCode to set.
     */
    public void setOwningGroupCode( String aOwningGroupCode ) {
        owningGroupCode = aOwningGroupCode;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( owningGroupCode == null ) ? 0 : owningGroupCode.hashCode() );
        result = prime * result + ( ( primaryContact == null ) ? 0 : primaryContact.hashCode() );
        result = prime * result + ( ( riskOwnerCode == null ) ? 0 : riskOwnerCode.hashCode() );
        result = prime * result + ( ( riskOwnerDescription == null ) ? 0 : riskOwnerDescription.hashCode() );
        result = prime * result + ( ( riskOwnerRole == null ) ? 0 : riskOwnerRole.hashCode() );
        result = prime * result + ( ( riskOwnerType == null ) ? 0 : riskOwnerType.hashCode() );
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
        LegalEntityRiskOwner other = (LegalEntityRiskOwner) obj;
        if ( owningGroupCode == null ) {
            if ( other.owningGroupCode != null )
                return false;
        }
        else if ( !owningGroupCode.equals( other.owningGroupCode ) )
            return false;
        if ( primaryContact == null ) {
            if ( other.primaryContact != null )
                return false;
        }
        else if ( !primaryContact.equals( other.primaryContact ) )
            return false;
        if ( riskOwnerCode == null ) {
            if ( other.riskOwnerCode != null )
                return false;
        }
        else if ( !riskOwnerCode.equals( other.riskOwnerCode ) )
            return false;
        if ( riskOwnerDescription == null ) {
            if ( other.riskOwnerDescription != null )
                return false;
        }
        else if ( !riskOwnerDescription.equals( other.riskOwnerDescription ) )
            return false;
        if ( riskOwnerRole == null ) {
            if ( other.riskOwnerRole != null )
                return false;
        }
        else if ( !riskOwnerRole.equals( other.riskOwnerRole ) )
            return false;
        if ( riskOwnerType == null ) {
            if ( other.riskOwnerType != null )
                return false;
        }
        else if ( !riskOwnerType.equals( other.riskOwnerType ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LegalEntityRiskOwner [riskOwnerType=" + riskOwnerType + ", riskOwnerRole=" + riskOwnerRole
               + ", riskOwnerCode=" + riskOwnerCode + ", riskOwnerDescription=" + riskOwnerDescription
               + ", primaryContact=" + primaryContact + ", owningGroupCode=" + owningGroupCode + "]";
    }

}
