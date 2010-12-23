/*
 * LegalEntity.java created on 28 Sep 2010 17:34:57 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Persistable domain class for a Legal Entity.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_LEGAL_ENTITY")
public class LegalEntity extends AbstractLegalEntity {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( LegalEntity.class );

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({
                  @JoinColumn(name = LegalEntityKey.ID_COLUMN_NAME,
                      referencedColumnName = LegalEntityKey.ID_COLUMN_NAME),
                  @JoinColumn(name = LegalEntityKey.DOMAIN_COLUMN_NAME,
                      referencedColumnName = LegalEntityKey.DOMAIN_COLUMN_NAME),
                  @JoinColumn(name = LegalEntityKey.VERSION_COLUMN_NAME,
                      referencedColumnName = LegalEntityKey.VERSION_COLUMN_NAME) })
    private Set<LegalEntityRiskOwner> riskOwners = new HashSet<LegalEntityRiskOwner>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({
                  @JoinColumn(name = LegalEntityKey.ID_COLUMN_NAME,
                      referencedColumnName = LegalEntityKey.ID_COLUMN_NAME),
                  @JoinColumn(name = LegalEntityKey.DOMAIN_COLUMN_NAME,
                      referencedColumnName = LegalEntityKey.DOMAIN_COLUMN_NAME),
                  @JoinColumn(name = LegalEntityKey.VERSION_COLUMN_NAME,
                      referencedColumnName = LegalEntityKey.VERSION_COLUMN_NAME) })
    private Set<LegalEntityCrossReference> crossReference = new HashSet<LegalEntityCrossReference>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({
                  @JoinColumn(name = LegalEntityKey.ID_COLUMN_NAME,
                      referencedColumnName = LegalEntityKey.ID_COLUMN_NAME),
                  @JoinColumn(name = LegalEntityKey.DOMAIN_COLUMN_NAME,
                      referencedColumnName = LegalEntityKey.DOMAIN_COLUMN_NAME),
                  @JoinColumn(name = LegalEntityKey.VERSION_COLUMN_NAME,
                      referencedColumnName = LegalEntityKey.VERSION_COLUMN_NAME) })
    private Set<LegalEntityContraEntity> contraEntity = new HashSet<LegalEntityContraEntity>();

    @Column(name = "COUNTRY_DOMICILE")
    private String countryDomicile;

    @Column(name = "COUNTRY_INCORPORATION")
    private String countryIncorporation;

    @Column(name = "INDUSTRY_CLASS_CODE")
    private String classCode;

    @Column(name = "INDUSTRY_CLASS_TYPE")
    private String classType;

    @Temporal(TemporalType.DATE)
    @Column(name = "LE_STATUS_UPDT_DATE")
    private Date legalEntityStatusUpdateDate;

    @Column(name = "LE_STATUS")
    private String legalEntityStatus;

    @Column(name = "CLIENT_CLASS")
    private String clientClass;

    @Column(name = "HASCOLLATERAL")
    private String hasCollateral;

    @Column(name = "COUNTRY_OF_RISK")
    private String countryOfRisk;

    @Column(name = "IS_CONFIDENTIAL")
    private String isConfidential;

    @Column(name = "INTL_RATING_CODE")
    private String internationalRatingCode;

    @Temporal(TemporalType.DATE)
    @Column(name = "INTL_RATING_REVIEW_DATE")
    private Date internationalRatingReviewDate;

    @Column(name = "PORTFOLIO_SEGMENT")
    private String portfolioSegment;

    @Column(name = "RATING_APPROACH")
    private String ratingApproach;

    @Column(name = "BOE_CODE")
    private String boeCode;

    @Column(name = "BOE_TYPE")
    private String boeType;

    @Column(name = "CA_CODE")
    private String caCode;

    @Column(name = "CA_TYPE")
    private String caType;

    @Column(name = "CT_CODE")
    private String ctCode;

    @Column(name = "CT_TYPE")
    private String ctType;

    @Column(name = "LEGACY_ID")
    private String legacyId;

    /**
     * Constructs a new instance. Created for Hibernate only.
     */
    protected LegalEntity() {}

    /**
     * Constructs a new instance.
     */
    public LegalEntity( LegalEntityKey aLegalEntityKey, String aLegalName ) {
        super( aLegalEntityKey, aLegalName );
    }

    /**
     * Constructs a new instance.
     */
    public LegalEntity( LegalEntityKey aLegalEntityKey, String aLegalName, String aCountryDomicile,
                        String aCountryIncorporation, String aClassCode, String aClassType,
                        Date aLegalEntityStatusUpdateDate, String aLegalEntityStatus, String aClientClass,
                        String aHasCollateralFlag, String aCountryOfRisk, String aIsConfidentialFlag,
                        String aInternationalRatingCode, Date aInternationalRatingReviewDate,
                        String aPortfolioSegment, String aRatingApproach, String aBoeCode, String aBoeType,
                        String aCaCode, String aCaType, String aCtCode, String aCtType, String aLegacyId ) {
        this( aLegalEntityKey, aLegalName );

        countryDomicile = aCountryDomicile;
        countryIncorporation = aCountryIncorporation;
        classCode = aClassCode;
        classType = aClassType;
        legalEntityStatusUpdateDate = aLegalEntityStatusUpdateDate;
        legalEntityStatus = aLegalEntityStatus;
        clientClass = aClientClass;
        hasCollateral = aHasCollateralFlag;
        countryOfRisk = aCountryOfRisk;
        isConfidential = aIsConfidentialFlag;
        internationalRatingCode = aInternationalRatingCode;
        internationalRatingReviewDate = aInternationalRatingReviewDate;
        portfolioSegment = aPortfolioSegment;
        ratingApproach = aRatingApproach;
        boeCode = aBoeCode;
        boeType = aBoeType;
        caCode = aCaCode;
        caType = aCaType;
        ctCode = aCtCode;
        ctType = aCtType;
        legacyId = aLegacyId;
    }

    /**
     * Returns the value of riskOwners.
     * 
     * @return Returns the riskOwners.
     */
    public Set<LegalEntityRiskOwner> getRiskOwners() {
        return riskOwners;
    }

    /**
     * Sets the riskOwners field to the specified value.
     * 
     * @param aRiskOwners
     *            The riskOwners to set.
     */
    public void setRiskOwners( Set<LegalEntityRiskOwner> aRiskOwners ) {
        riskOwners = aRiskOwners;
    }

    /**
     * Adds a riskOwner to the collection of risk owners.
     * 
     * @param aRiskOwner
     *            The riskOwner to add.
     */
    public void addRiskOwner( LegalEntityRiskOwner aRiskOwner ) {
        riskOwners.add( aRiskOwner );
    }

    /**
     * Returns the value of crossReference.
     * 
     * @return Returns the crossReference.
     */
    public Set<LegalEntityCrossReference> getCrossReference() {
        return crossReference;
    }

    /**
     * Sets the crossReference field to the specified value.
     * 
     * @param aCrossReference
     *            The crossReference to set.
     */
    public void setCrossReference( Set<LegalEntityCrossReference> aCrossReference ) {
        crossReference = aCrossReference;
    }

    /**
     * Adds a crossReference to the collection of cross references.
     * 
     * @param aCrossReference
     *            The crossReference to add.
     */
    public void addCrossReference( LegalEntityCrossReference aCrossReference ) {
        crossReference.add( aCrossReference );
    }

    /**
     * Returns the value of contraEntity.
     * 
     * @return Returns the contraEntity.
     */
    public Set<LegalEntityContraEntity> getContraEntity() {
        return contraEntity;
    }

    /**
     * Sets the contraEntity field to the specified value.
     * 
     * @param aContraEntity
     *            The contraEntity to set.
     */
    public void setContraEntity( Set<LegalEntityContraEntity> aContraEntity ) {
        contraEntity = aContraEntity;
    }

    /**
     * Adds a contraEntity to the collection of contra entites.
     * 
     * @param aContraEntity
     *            The contraEntity to add.
     */
    public void addContraEntity( LegalEntityContraEntity aContraEntity ) {
        contraEntity.add( aContraEntity );
    }

    /**
     * Returns the value of countryDomicile.
     * 
     * @return Returns the countryDomicile.
     */
    public String getCountryDomicile() {
        return countryDomicile;
    }

    /**
     * Sets the countryDomicile field to the specified value.
     * 
     * @param aCountryDomicile
     *            The countryDomicile to set.
     */
    public void setCountryDomicile( String aCountryDomicile ) {
        countryDomicile = aCountryDomicile;
    }

    /**
     * Returns the value of countryIncorporation.
     * 
     * @return Returns the countryIncorporation.
     */
    public String getCountryIncorporation() {
        return countryIncorporation;
    }

    /**
     * Sets the countryIncorporation field to the specified value.
     * 
     * @param aCountryIncorporation
     *            The countryIncorporation to set.
     */
    public void setCountryIncorporation( String aCountryIncorporation ) {
        countryIncorporation = aCountryIncorporation;
    }

    /**
     * Returns the value of classCode.
     * 
     * @return Returns the classCode.
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Sets the classCode field to the specified value.
     * 
     * @param aClassCode
     *            The classCode to set.
     */
    public void setClassCode( String aClassCode ) {
        classCode = aClassCode;
    }

    /**
     * Returns the value of classType.
     * 
     * @return Returns the classType.
     */
    public String getClassType() {
        return classType;
    }

    /**
     * Sets the classType field to the specified value.
     * 
     * @param aClassType
     *            The classType to set.
     */
    public void setClassType( String aClassType ) {
        classType = aClassType;
    }

    /**
     * Returns the value of legalEntityStatusUpdateDate.
     * 
     * @return Returns the legalEntityStatusUpdateDate.
     */
    public Date getLegalEntityStatusUpdateDate() {
        return legalEntityStatusUpdateDate;
    }

    /**
     * Sets the legalEntityStatusUpdateDate field to the specified value.
     * 
     * @param aLegalEntityStatusUpdateDate
     *            The legalEntityStatusUpdateDate to set.
     */
    public void setLegalEntityStatusUpdateDate( Date aLegalEntityStatusUpdateDate ) {
        legalEntityStatusUpdateDate = aLegalEntityStatusUpdateDate;
    }

    /**
     * Returns the value of legalEntityStatus.
     * 
     * @return Returns the legalEntityStatus.
     */
    public String getLegalEntityStatus() {
        return legalEntityStatus;
    }

    /**
     * Sets the legalEntityStatus field to the specified value.
     * 
     * @param aLegalEntityStatus
     *            The legalEntityStatus to set.
     */
    public void setLegalEntityStatus( String aLegalEntityStatus ) {
        legalEntityStatus = aLegalEntityStatus;
    }

    /**
     * Returns the value of clientClass.
     * 
     * @return Returns the clientClass.
     */
    public String getClientClass() {
        return clientClass;
    }

    /**
     * Sets the clientClass field to the specified value.
     * 
     * @param aClientClass
     *            The clientClass to set.
     */
    public void setClientClass( String aClientClass ) {
        clientClass = aClientClass;
    }

    /**
     * Returns the value of hasCollateral.
     * 
     * @return Returns the hasCollateral.
     */
    public String getHasCollateral() {
        return hasCollateral;
    }

    /**
     * Sets the hasCollateral field to the specified value.
     * 
     * @param aHasCollateral
     *            The hasCollateral to set.
     */
    public void setHasCollateral( String aHasCollateral ) {
        hasCollateral = aHasCollateral;
    }

    /**
     * Returns the value of countryOfRisk.
     * 
     * @return Returns the countryOfRisk.
     */
    public String getCountryOfRisk() {
        return countryOfRisk;
    }

    /**
     * Sets the countryOfRisk field to the specified value.
     * 
     * @param aCountryOfRisk
     *            The countryOfRisk to set.
     */
    public void setCountryOfRisk( String aCountryOfRisk ) {
        countryOfRisk = aCountryOfRisk;
    }

    /**
     * Returns the value of isConfidential.
     * 
     * @return Returns the isConfidential.
     */
    public String getIsConfidential() {
        return isConfidential;
    }

    /**
     * Sets the isConfidential field to the specified value.
     * 
     * @param aIsConfidential
     *            The isConfidential to set.
     */
    public void setIsConfidential( String aIsConfidential ) {
        isConfidential = aIsConfidential;
    }

    /**
     * Returns the value of internationalRatingCode.
     * 
     * @return Returns the internationalRatingCode.
     */
    public String getInternationalRatingCode() {
        return internationalRatingCode;
    }

    /**
     * Sets the internationalRatingCode field to the specified value.
     * 
     * @param aInternationalRatingCode
     *            The internationalRatingCode to set.
     */
    public void setInternationalRatingCode( String aInternationalRatingCode ) {
        internationalRatingCode = aInternationalRatingCode;
    }

    /**
     * Returns the value of internationalRatingReviewDate.
     * 
     * @return Returns the internationalRatingReviewDate.
     */
    public Date getInternationalRatingReviewDate() {
        return internationalRatingReviewDate;
    }

    /**
     * Sets the internationalRatingReviewDate field to the specified value.
     * 
     * @param aInternationalRatingReviewDate
     *            The internationalRatingReviewDate to set.
     */
    public void setInternationalRatingReviewDate( Date aInternationalRatingReviewDate ) {
        internationalRatingReviewDate = aInternationalRatingReviewDate;
    }

    /**
     * Returns the value of portfolioSegment.
     * 
     * @return Returns the portfolioSegment.
     */
    public String getPortfolioSegment() {
        return portfolioSegment;
    }

    /**
     * Sets the portfolioSegment field to the specified value.
     * 
     * @param aPortfolioSegment
     *            The portfolioSegment to set.
     */
    public void setPortfolioSegment( String aPortfolioSegment ) {
        portfolioSegment = aPortfolioSegment;
    }

    /**
     * Returns the value of ratingApproach.
     * 
     * @return Returns the ratingApproach.
     */
    public String getRatingApproach() {
        return ratingApproach;
    }

    /**
     * Sets the ratingApproach field to the specified value.
     * 
     * @param aRatingApproach
     *            The ratingApproach to set.
     */
    public void setRatingApproach( String aRatingApproach ) {
        ratingApproach = aRatingApproach;
    }

    /**
     * Returns the value of boeCode.
     * 
     * @return Returns the boeCode.
     */
    public String getBoeCode() {
        return boeCode;
    }

    /**
     * Sets the boeCode field to the specified value.
     * 
     * @param aBoeCode
     *            The boeCode to set.
     */
    public void setBoeCode( String aBoeCode ) {
        boeCode = aBoeCode;
    }

    /**
     * Returns the value of boeType.
     * 
     * @return Returns the boeType.
     */
    public String getBoeType() {
        return boeType;
    }

    /**
     * Sets the boeType field to the specified value.
     * 
     * @param aBoeType
     *            The boeType to set.
     */
    public void setBoeType( String aBoeType ) {
        boeType = aBoeType;
    }

    /**
     * Returns the value of caCode.
     * 
     * @return Returns the caCode.
     */
    public String getCaCode() {
        return caCode;
    }

    /**
     * Sets the caCode field to the specified value.
     * 
     * @param aCaCode
     *            The caCode to set.
     */
    public void setCaCode( String aCaCode ) {
        caCode = aCaCode;
    }

    /**
     * Returns the value of caType.
     * 
     * @return Returns the caType.
     */
    public String getCaType() {
        return caType;
    }

    /**
     * Sets the caType field to the specified value.
     * 
     * @param aCaType
     *            The caType to set.
     */
    public void setCaType( String aCaType ) {
        caType = aCaType;
    }

    /**
     * Returns the value of ctCode.
     * 
     * @return Returns the ctCode.
     */
    public String getCtCode() {
        return ctCode;
    }

    /**
     * Sets the ctCode field to the specified value.
     * 
     * @param aCtCode
     *            The ctCode to set.
     */
    public void setCtCode( String aCtCode ) {
        ctCode = aCtCode;
    }

    /**
     * Returns the value of ctType.
     * 
     * @return Returns the ctType.
     */
    public String getCtType() {
        return ctType;
    }

    /**
     * Sets the ctType field to the specified value.
     * 
     * @param aCtType
     *            The ctType to set.
     */
    public void setCtType( String aCtType ) {
        ctType = aCtType;
    }

    /**
     * Returns the value of legacyId.
     * 
     * @return Returns the legacyId.
     */
    public String getLegacyId() {
        return legacyId;
    }

    /**
     * Sets the legacyId field to the specified value.
     * 
     * @param aLegacyId
     *            The legacyId to set.
     */
    public void setLegacyId( String aLegacyId ) {
        legacyId = aLegacyId;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( boeCode == null ) ? 0 : boeCode.hashCode() );
        result = prime * result + ( ( boeType == null ) ? 0 : boeType.hashCode() );
        result = prime * result + ( ( caCode == null ) ? 0 : caCode.hashCode() );
        result = prime * result + ( ( caType == null ) ? 0 : caType.hashCode() );
        result = prime * result + ( ( classCode == null ) ? 0 : classCode.hashCode() );
        result = prime * result + ( ( classType == null ) ? 0 : classType.hashCode() );
        result = prime * result + ( ( clientClass == null ) ? 0 : clientClass.hashCode() );
        result = prime * result + ( ( countryDomicile == null ) ? 0 : countryDomicile.hashCode() );
        result = prime * result + ( ( countryIncorporation == null ) ? 0 : countryIncorporation.hashCode() );
        result = prime * result + ( ( countryOfRisk == null ) ? 0 : countryOfRisk.hashCode() );
        result = prime * result + ( ( ctCode == null ) ? 0 : ctCode.hashCode() );
        result = prime * result + ( ( ctType == null ) ? 0 : ctType.hashCode() );
        result = prime * result + ( ( hasCollateral == null ) ? 0 : hasCollateral.hashCode() );
        result = prime * result
                 + ( ( internationalRatingCode == null ) ? 0 : internationalRatingCode.hashCode() );
        result = prime
                 * result
                 + ( ( internationalRatingReviewDate == null ) ? 0 : internationalRatingReviewDate.hashCode() );
        result = prime * result + ( ( isConfidential == null ) ? 0 : isConfidential.hashCode() );
        result = prime * result + ( ( legacyId == null ) ? 0 : legacyId.hashCode() );
        result = prime * result + ( ( legalEntityStatus == null ) ? 0 : legalEntityStatus.hashCode() );
        result = prime * result
                 + ( ( legalEntityStatusUpdateDate == null ) ? 0 : legalEntityStatusUpdateDate.hashCode() );
        result = prime * result + ( ( portfolioSegment == null ) ? 0 : portfolioSegment.hashCode() );
        result = prime * result + ( ( ratingApproach == null ) ? 0 : ratingApproach.hashCode() );
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
        LegalEntity other = (LegalEntity) obj;
        if ( boeCode == null ) {
            if ( other.boeCode != null )
                return false;
        }
        else if ( !boeCode.equals( other.boeCode ) )
            return false;
        if ( boeType == null ) {
            if ( other.boeType != null )
                return false;
        }
        else if ( !boeType.equals( other.boeType ) )
            return false;
        if ( caCode == null ) {
            if ( other.caCode != null )
                return false;
        }
        else if ( !caCode.equals( other.caCode ) )
            return false;
        if ( caType == null ) {
            if ( other.caType != null )
                return false;
        }
        else if ( !caType.equals( other.caType ) )
            return false;
        if ( classCode == null ) {
            if ( other.classCode != null )
                return false;
        }
        else if ( !classCode.equals( other.classCode ) )
            return false;
        if ( classType == null ) {
            if ( other.classType != null )
                return false;
        }
        else if ( !classType.equals( other.classType ) )
            return false;
        if ( clientClass == null ) {
            if ( other.clientClass != null )
                return false;
        }
        else if ( !clientClass.equals( other.clientClass ) )
            return false;
        if ( countryDomicile == null ) {
            if ( other.countryDomicile != null )
                return false;
        }
        else if ( !countryDomicile.equals( other.countryDomicile ) )
            return false;
        if ( countryIncorporation == null ) {
            if ( other.countryIncorporation != null )
                return false;
        }
        else if ( !countryIncorporation.equals( other.countryIncorporation ) )
            return false;
        if ( countryOfRisk == null ) {
            if ( other.countryOfRisk != null )
                return false;
        }
        else if ( !countryOfRisk.equals( other.countryOfRisk ) )
            return false;
        if ( ctCode == null ) {
            if ( other.ctCode != null )
                return false;
        }
        else if ( !ctCode.equals( other.ctCode ) )
            return false;
        if ( ctType == null ) {
            if ( other.ctType != null )
                return false;
        }
        else if ( !ctType.equals( other.ctType ) )
            return false;
        if ( hasCollateral == null ) {
            if ( other.hasCollateral != null )
                return false;
        }
        else if ( !hasCollateral.equals( other.hasCollateral ) )
            return false;
        if ( internationalRatingCode == null ) {
            if ( other.internationalRatingCode != null )
                return false;
        }
        else if ( !internationalRatingCode.equals( other.internationalRatingCode ) )
            return false;
        if ( internationalRatingReviewDate == null ) {
            if ( other.internationalRatingReviewDate != null )
                return false;
        }
        else if ( !internationalRatingReviewDate.equals( other.internationalRatingReviewDate ) )
            return false;
        if ( isConfidential == null ) {
            if ( other.isConfidential != null )
                return false;
        }
        else if ( !isConfidential.equals( other.isConfidential ) )
            return false;
        if ( legacyId == null ) {
            if ( other.legacyId != null )
                return false;
        }
        else if ( !legacyId.equals( other.legacyId ) )
            return false;
        if ( legalEntityStatus == null ) {
            if ( other.legalEntityStatus != null )
                return false;
        }
        else if ( !legalEntityStatus.equals( other.legalEntityStatus ) )
            return false;
        if ( legalEntityStatusUpdateDate == null ) {
            if ( other.legalEntityStatusUpdateDate != null )
                return false;
        }
        else if ( !legalEntityStatusUpdateDate.equals( other.legalEntityStatusUpdateDate ) )
            return false;
        if ( portfolioSegment == null ) {
            if ( other.portfolioSegment != null )
                return false;
        }
        else if ( !portfolioSegment.equals( other.portfolioSegment ) )
            return false;
        if ( ratingApproach == null ) {
            if ( other.ratingApproach != null )
                return false;
        }
        else if ( !ratingApproach.equals( other.ratingApproach ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LegalEntity [countryDomicile=" + countryDomicile + ", countryIncorporation="
               + countryIncorporation + ", classCode=" + classCode + ", classType=" + classType
               + ", legalEntityStatusUpdateDate=" + legalEntityStatusUpdateDate + ", legalEntityStatus="
               + legalEntityStatus + ", clientClass=" + clientClass + ", hasCollateral=" + hasCollateral
               + ", countryOfRisk=" + countryOfRisk + ", isConfidential=" + isConfidential
               + ", internationalRatingCode=" + internationalRatingCode + ", internationalRatingReviewDate="
               + internationalRatingReviewDate + ", portfolioSegment=" + portfolioSegment
               + ", ratingApproach=" + ratingApproach + ", boeCode=" + boeCode + ", boeType=" + boeType
               + ", caCode=" + caCode + ", caType=" + caType + ", ctCode=" + ctCode + ", ctType=" + ctType
               + ", legacyId=" + legacyId + "]";
    }

}
