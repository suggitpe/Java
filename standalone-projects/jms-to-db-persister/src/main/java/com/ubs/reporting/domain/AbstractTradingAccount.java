/*
 * AbstractTradingAccount.java created on 28 Sep 2010 19:27:20 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract persistable domain class for all Trading Account entities.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@MappedSuperclass
public abstract class AbstractTradingAccount extends AbstractStagedEntity {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( AbstractTradingAccount.class );

    @Id
    private TradingAccountKey tradingAccountKey;

    @Column(name = "TRADING_ACC_NK")
    private String tradingAccountNK;

    /**
     * Constructs a new instance. For hibernate use only.
     */
    protected AbstractTradingAccount() {}

    /**
     * Constructs a new instance.
     */
    public AbstractTradingAccount( TradingAccountKey aTradingAccountKey, String aTradingAccountNK ) {
        tradingAccountKey = aTradingAccountKey;
        tradingAccountNK = aTradingAccountNK;
    }

    /**
     * Returns the value of tradingAccountKey.
     * 
     * @return Returns the tradingAccountKey.
     */
    public TradingAccountKey getTradingAccountKey() {
        return tradingAccountKey;
    }

    /**
     * Sets the tradingAccountKey field to the specified value.
     * 
     * @param aTradingAccountKey
     *            The tradingAccountKey to set.
     */
    public void setTradingAccountKey( TradingAccountKey aTradingAccountKey ) {
        tradingAccountKey = aTradingAccountKey;
    }

    /**
     * Returns the value of tradingAccountNK.
     * 
     * @return Returns the tradingAccountNK.
     */
    public String getTradingAccountNK() {
        return tradingAccountNK;
    }

    /**
     * Sets the tradingAccountNK field to the specified value.
     * 
     * @param aTradingAccountNK
     *            The tradingAccountNK to set.
     */
    public void setTradingAccountNK( String aTradingAccountNK ) {
        tradingAccountNK = aTradingAccountNK;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( tradingAccountKey == null ) ? 0 : tradingAccountKey.hashCode() );
        result = prime * result + ( ( tradingAccountNK == null ) ? 0 : tradingAccountNK.hashCode() );
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
        AbstractTradingAccount other = (AbstractTradingAccount) obj;
        if ( tradingAccountKey == null ) {
            if ( other.tradingAccountKey != null )
                return false;
        }
        else if ( !tradingAccountKey.equals( other.tradingAccountKey ) )
            return false;
        if ( tradingAccountNK == null ) {
            if ( other.tradingAccountNK != null )
                return false;
        }
        else if ( !tradingAccountNK.equals( other.tradingAccountNK ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AbstractTradingAccount [tradingAccountKey=" + tradingAccountKey + ", tradingAccountNK="
               + tradingAccountNK + "]";
    }

}
