/*
 * TradingAccountKey.java created on 29 Sep 2010 08:39:51 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the composite key for a Trading Account.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2010
 */
@Embeddable
public class TradingAccountKey implements Serializable {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TradingAccountKey.class );
    private static final long serialVersionUID = 4313529050644550568L;

    @Column(name = "TRADING_ACC_ID")
    private Integer tradingAccountId;

    @Column(name = "TRADING_ACC_DOMAIN")
    private String tradingAccountDomain;

    @Column(name = "TRADING_ACC_VERSION")
    private Integer tradingAccountversion;

    /**
     * Constructs a new instance.
     */
    protected TradingAccountKey() {}

    /**
     * Constructs a new instance.
     */
    public TradingAccountKey( Integer aTradingAccountId, String aTradingAccountDomain,
                              Integer aTradingAccountVersion ) {
        tradingAccountId = aTradingAccountId;
        tradingAccountDomain = aTradingAccountDomain;
        tradingAccountversion = aTradingAccountVersion;
    }

    /**
     * Returns the value of tradingAccountId.
     * 
     * @return Returns the tradingAccountId.
     */
    public Integer getTradingAccountId() {
        return tradingAccountId;
    }

    /**
     * Sets the tradingAccountId field to the specified value.
     * 
     * @param aTradingAccountId
     *            The tradingAccountId to set.
     */
    public void setTradingAccountId( Integer aTradingAccountId ) {
        tradingAccountId = aTradingAccountId;
    }

    /**
     * Returns the value of tradingAccountDomain.
     * 
     * @return Returns the tradingAccountDomain.
     */
    public String getTradingAccountDomain() {
        return tradingAccountDomain;
    }

    /**
     * Sets the tradingAccountDomain field to the specified value.
     * 
     * @param aTradingAccountDomain
     *            The tradingAccountDomain to set.
     */
    public void setTradingAccountDomain( String aTradingAccountDomain ) {
        tradingAccountDomain = aTradingAccountDomain;
    }

    /**
     * Returns the value of tradingAccountversion.
     * 
     * @return Returns the tradingAccountversion.
     */
    public Integer getTradingAccountversion() {
        return tradingAccountversion;
    }

    /**
     * Sets the tradingAccountversion field to the specified value.
     * 
     * @param aTradingAccountversion
     *            The tradingAccountversion to set.
     */
    public void setTradingAccountversion( Integer aTradingAccountversion ) {
        tradingAccountversion = aTradingAccountversion;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( tradingAccountDomain == null ) ? 0 : tradingAccountDomain.hashCode() );
        result = prime * result + ( ( tradingAccountId == null ) ? 0 : tradingAccountId.hashCode() );
        result = prime * result + ( ( tradingAccountversion == null ) ? 0 : tradingAccountversion.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        TradingAccountKey other = (TradingAccountKey) obj;
        if ( tradingAccountDomain == null ) {
            if ( other.tradingAccountDomain != null )
                return false;
        }
        else if ( !tradingAccountDomain.equals( other.tradingAccountDomain ) )
            return false;
        if ( tradingAccountId == null ) {
            if ( other.tradingAccountId != null )
                return false;
        }
        else if ( !tradingAccountId.equals( other.tradingAccountId ) )
            return false;
        if ( tradingAccountversion == null ) {
            if ( other.tradingAccountversion != null )
                return false;
        }
        else if ( !tradingAccountversion.equals( other.tradingAccountversion ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TradingAccountKey [tradingAccountId=" + tradingAccountId + ", tradingAccountDomain="
               + tradingAccountDomain + ", tradingAccountversion=" + tradingAccountversion + "]";
    }

}
