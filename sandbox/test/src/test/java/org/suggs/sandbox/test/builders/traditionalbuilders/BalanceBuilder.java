package org.suggs.sandbox.test.builders.traditionalbuilders;

import org.suggs.sandbox.test.builders.pojo.Balance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder for balance objects.
 */

public class BalanceBuilder implements Builder<Balance> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BalanceBuilder.class );

    private Balance balance;

    private BalanceBuilder() {
        balance = new Balance();
    }

    public static final BalanceBuilder aBalance() {
        return new BalanceBuilder();
    }

    public BalanceBuilder withAmount( double aAmount ) {
        balance.setAmount( aAmount );
        return this;
    }

    public BalanceBuilder withCurrency( String aCurrency ) {
        balance.setCurrency( aCurrency );
        return this;
    }

    public BalanceBuilder withCurrencyAndAmount( String aCurrency, double aAmount ) {
        withCurrency( aCurrency );
        withAmount( aAmount );
        return this;
    }

    @Override
    public Balance build() {
        return balance;
    }
}
