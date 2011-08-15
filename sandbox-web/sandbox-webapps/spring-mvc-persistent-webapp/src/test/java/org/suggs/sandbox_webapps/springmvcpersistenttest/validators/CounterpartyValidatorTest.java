package org.suggs.sandbox_webapps.springmvcpersistenttest.validators;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.Counterparty;
import org.suggs.sandbox_webapps.springmvcpersistenttest.validators.support.AbstractValidatorTest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;

/**
 * Parameterized test class for the Counterparty validator.
 * <p/>
 * User: suggitpe Date: 10/03/11 Time: 12:08
 */
public class CounterpartyValidatorTest extends AbstractValidatorTest<CounterpartyValidator, Counterparty> {

    public CounterpartyValidatorTest( Counterparty aCounterparty, Boolean aExpectedValidatorResult ) {
        super( aCounterparty, aExpectedValidatorResult );
    }

    @Override
    protected CounterpartyValidator createValidator() {
        return new CounterpartyValidator();
    }

    @Parameterized.Parameters
    @SuppressWarnings("boxing")
    public static Collection createTestInput() {
        Object[][] data = new Object[][]{
                { new Counterparty( "Name", "Legal Name", 12346 ), Boolean.TRUE },
                { new Counterparty(), Boolean.FALSE },
                { new Counterparty( "", "", 0 ), Boolean.FALSE },
                { new Counterparty( "foo", "", 0 ), Boolean.FALSE },
                { new Counterparty( "", "bar", 0 ), Boolean.FALSE } };
        return Arrays.asList( data );
    }
}
