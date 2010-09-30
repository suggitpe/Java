/*
 * LegalEntityRiskOwner.java created on 28 Sep 2010 17:34:43 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
    private static final Log LOG = LogFactory.getLog( LegalEntityRiskOwner.class );
    private static final long serialVersionUID = -9119178129773249781L;

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
     * Constructs a new instance.
     */
    public LegalEntityRiskOwner( LegalEntityKey aLegalEntityKey, String aLegalName ) {
        super( aLegalEntityKey, aLegalName );
    }

}
