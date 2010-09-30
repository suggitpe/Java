/*
 * TradingAccountRelationship.java created on 28 Sep 2010 17:36:12 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
    private static final Log LOG = LogFactory.getLog( TradingAccountRelationship.class );
    private static final long serialVersionUID = 8666474208057036840L;

    @Column(name = "RELATIONSHIP_ID")
    private String relationshipId;

    @Column(name = "RELATIONSHIP_DOMAIN")
    private String relationShipDomain;

    @Column(name = "RELATIONSHIP_TYPE")
    private String relationshipType;

    @Column(name = "RELATIONSHIP_STATUS_DATE")
    private Date relationshipDate;

    @Column(name = "RELATIONSHIP_STATUS")
    private String relationshipStatus;

    /**
     * Constructs a new instance.
     */
    public TradingAccountRelationship( TradingAccountKey aTradingAccountKey, String aTradingAccountNK ) {
        super( aTradingAccountKey, aTradingAccountNK );
    }
}
