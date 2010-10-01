/*
 * LegalEntityContraEntity.java created on 28 Sep 2010 17:36:45 by suggitpe for project masterfiles-receiver
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
 * Persistable domain class for a Legal Entity Contra Entity.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_LE_CONTR_ENTITY")
public class LegalEntityContraEntity extends AbstractLegalEntity {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LegalEntityContraEntity.class );
    private static final long serialVersionUID = -8628034557666992483L;

    @Column(name = "CONTR_ENTITY_ID")
    private Integer contraEntityId;

    @Column(name = "CONTR_ENTITY_DOMAIN")
    private String contraEntityDomain;

    @Column(name = "CONTR_ENTITY_NAME")
    private String contraEntityName;

    @Temporal(TemporalType.DATE)
    @Column(name = "CONTR_ENTITY_UPDT_DATE")
    private Date contraEntityUpdateDate;

    @Column(name = "CONTR_ENTITY_STATUS")
    private String contraEntityStatus;

    /**
     * Constructs a new instance. Created for Hibernate only.
     */
    protected LegalEntityContraEntity() {}

    /**
     * Constructs a new instance.
     */
    public LegalEntityContraEntity( LegalEntityKey aLegalEntityKey, String aLegalName,
                                    Integer aContraEntityId, String aContraEntityDomain,
                                    String aContraEntityName, Date aContraEntityUpdateDate,
                                    String aContraEntityStatus ) {
        super( aLegalEntityKey, aLegalName );
        contraEntityId = aContraEntityId;
        contraEntityDomain = aContraEntityDomain;
        contraEntityName = aContraEntityName;
        contraEntityUpdateDate = aContraEntityUpdateDate;
        contraEntityStatus = aContraEntityStatus;
    }

    /**
     * Returns the value of contraEntityId.
     * 
     * @return Returns the contraEntityId.
     */
    public Integer getContraEntityId() {
        return contraEntityId;
    }

    /**
     * Sets the contraEntityId field to the specified value.
     * 
     * @param aContraEntityId
     *            The contraEntityId to set.
     */
    public void setContraEntityId( Integer aContraEntityId ) {
        contraEntityId = aContraEntityId;
    }

    /**
     * Returns the value of contraEntityDomain.
     * 
     * @return Returns the contraEntityDomain.
     */
    public String getContraEntityDomain() {
        return contraEntityDomain;
    }

    /**
     * Sets the contraEntityDomain field to the specified value.
     * 
     * @param aContraEntityDomain
     *            The contraEntityDomain to set.
     */
    public void setContraEntityDomain( String aContraEntityDomain ) {
        contraEntityDomain = aContraEntityDomain;
    }

    /**
     * Returns the value of contraEntityName.
     * 
     * @return Returns the contraEntityName.
     */
    public String getContraEntityName() {
        return contraEntityName;
    }

    /**
     * Sets the contraEntityName field to the specified value.
     * 
     * @param aContraEntityName
     *            The contraEntityName to set.
     */
    public void setContraEntityName( String aContraEntityName ) {
        contraEntityName = aContraEntityName;
    }

    /**
     * Returns the value of contraEntityUpdateDate.
     * 
     * @return Returns the contraEntityUpdateDate.
     */
    public Date getContraEntityUpdateDate() {
        return contraEntityUpdateDate;
    }

    /**
     * Sets the contraEntityUpdateDate field to the specified value.
     * 
     * @param aContraEntityUpdateDate
     *            The contraEntityUpdateDate to set.
     */
    public void setContraEntityUpdateDate( Date aContraEntityUpdateDate ) {
        contraEntityUpdateDate = aContraEntityUpdateDate;
    }

    /**
     * Returns the value of contraEntityStatus.
     * 
     * @return Returns the contraEntityStatus.
     */
    public String getContraEntityStatus() {
        return contraEntityStatus;
    }

    /**
     * Sets the contraEntityStatus field to the specified value.
     * 
     * @param aContraEntityStatus
     *            The contraEntityStatus to set.
     */
    public void setContraEntityStatus( String aContraEntityStatus ) {
        contraEntityStatus = aContraEntityStatus;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( contraEntityDomain == null ) ? 0 : contraEntityDomain.hashCode() );
        result = prime * result + ( ( contraEntityId == null ) ? 0 : contraEntityId.hashCode() );
        result = prime * result + ( ( contraEntityName == null ) ? 0 : contraEntityName.hashCode() );
        result = prime * result + ( ( contraEntityStatus == null ) ? 0 : contraEntityStatus.hashCode() );
        result = prime * result
                 + ( ( contraEntityUpdateDate == null ) ? 0 : contraEntityUpdateDate.hashCode() );
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
        LegalEntityContraEntity other = (LegalEntityContraEntity) obj;
        if ( contraEntityDomain == null ) {
            if ( other.contraEntityDomain != null )
                return false;
        }
        else if ( !contraEntityDomain.equals( other.contraEntityDomain ) )
            return false;
        if ( contraEntityId == null ) {
            if ( other.contraEntityId != null )
                return false;
        }
        else if ( !contraEntityId.equals( other.contraEntityId ) )
            return false;
        if ( contraEntityName == null ) {
            if ( other.contraEntityName != null )
                return false;
        }
        else if ( !contraEntityName.equals( other.contraEntityName ) )
            return false;
        if ( contraEntityStatus == null ) {
            if ( other.contraEntityStatus != null )
                return false;
        }
        else if ( !contraEntityStatus.equals( other.contraEntityStatus ) )
            return false;
        if ( contraEntityUpdateDate == null ) {
            if ( other.contraEntityUpdateDate != null )
                return false;
        }
        // I have switched off the equals on the actual dates because the
        // returned one is different to the one we persisted (Hibernate issuette).
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LegalEntityContraEntity [contraEntityId=" + contraEntityId + ", contraEntityDomain="
               + contraEntityDomain + ", contraEntityName=" + contraEntityName + ", contraEntityUpdateDate="
               + contraEntityUpdateDate + ", contraEntityStatus=" + contraEntityStatus + "]";
    }

}
