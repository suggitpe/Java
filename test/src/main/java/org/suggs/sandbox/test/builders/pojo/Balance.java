package org.suggs.sandbox.test.builders.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple pojo to wrap a balance object.
 */

public class Balance {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Balance.class );
    
    private double amount;
    private String currency;

    public double getAmount() {
        return amount;
    }

    public void setAmount( double aAmount ) {
        amount = aAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency( String aCurrency ) {
        currency = aCurrency;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Balance ) ) return false;

        Balance that = ( Balance ) o;

        if ( Double.compare( that.amount, amount ) != 0 ) return false;
        if ( currency != null ? !currency.equals( that.currency ) : that.currency != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = amount != +0.0d ? Double.doubleToLongBits( amount ) : 0L;
        result = ( int ) ( temp ^ ( temp >>> 32 ) );
        result = 31 * result + ( currency != null ? currency.hashCode() : 0 );
        return result;
    }
}

