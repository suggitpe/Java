package org.suggs.sandbox.test.builders.makeiteasy;

import org.suggs.sandbox.test.builders.pojo.Balance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

import static com.natpryce.makeiteasy.Property.newProperty;

/**
 * Nat Pryce maker for balances.
 */

public final class BalanceMaker {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BalanceMaker.class );
    
    public static final Property<Balance, Double> balanceAmount = newProperty();
    public static final Property<Balance, String> balanceCurrency = newProperty();
    
    public static final Instantiator<Balance> Balance = new Instantiator<Balance>() {
        @Override
        public Balance instantiate( PropertyLookup<Balance> lookup ) {
            Balance balance = new Balance();
            balance.setCurrency( lookup.valueOf( balanceCurrency, "GBP"));
            balance.setAmount( lookup.valueOf( balanceAmount, 100.0d));
            return balance;
        }
    };
}
