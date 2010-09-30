/*
 * TradingAccount.java created on 28 Sep 2010 17:36:23 by suggitpe for project masterfiles-receiver
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
 * Persistable domain class for a Trading Account.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_TRADING_ACCOUNT")
public class TradingAccount extends AbstractTradingAccount {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TradingAccount.class );
    private static final long serialVersionUID = -6968568761822911880L;

    @Column(name = "STATUS_UPDT_DATE")
    private Date statusUpdateDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "OPEN_DATE")
    private Date openDate;

    @Column(name = "CLIENT_CLASS")
    private String clientClass;

    @Column(name = "RISK_LOCATION")
    private String riskLocation;

    @Column(name = "RISK_CITY_CODE")
    private Integer riskCityCode;

    @Column(name = "SIMPLE_NAME")
    private String simpleName;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "COMMUNICATION_TYPE")
    private String communicationType;

    @Column(name = "ADDRESS_LINE")
    private String addressLine;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "FUNCTION_REFERENCE")
    private String functionReference;

    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE3")
    private String addressLine3;

    @Column(name = "ADDRESS_LINE4")
    private String addressLine4;

    /**
     * Constructs a new instance.
     */
    public TradingAccount( TradingAccountKey aTradingAccountKey, String aTradingAccountNK ) {
        super( aTradingAccountKey, aTradingAccountNK );
    }
}
