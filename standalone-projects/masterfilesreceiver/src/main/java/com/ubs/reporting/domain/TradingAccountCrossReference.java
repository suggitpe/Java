/*
 * TradingAccountCrossReference.java created on 28 Sep 2010 17:35:41 by suggitpe for project masterfiles-receiver
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

    @Column(name = "ALT_IDENTIFIER_STATUS_DATE")
    private Date altIdentifierStatusDate;

    @Column(name = "ALT_IDENTIFIER_STATUS")
    private String altIdentifierStatus;

    @Column(name = "ALT_IDENTIFIER_BLOCKED")
    private String altIdentifierBlocked;

    /**
     * Constructs a new instance.
     */
    public TradingAccountCrossReference( TradingAccountKey aTradingAccountKey, String aTradingAccountNK ) {
        super( aTradingAccountKey, aTradingAccountNK );
    }
}
