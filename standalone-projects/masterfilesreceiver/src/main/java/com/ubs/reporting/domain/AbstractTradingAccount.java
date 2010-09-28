/*
 * AbstractTradingAccount.java created on 28 Sep 2010 19:27:20 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for AbstractTradingAccount
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@MappedSuperclass
public class AbstractTradingAccount extends AbstractStagedEntity {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( AbstractTradingAccount.class );

    @Column(name = "TRADING_ACC_NK")
    private String tradingAccountNK;

    @Column(name = "TRADING_ACC_DOMAIN")
    private String tradingAccountDomain;

    @Column(name = "TRADING_ACC_VERSION")
    private Integer tradingAccountversion;

    @Column(name = "TRADING_ACC_ID")
    private Integer tradingAccountId;
}
