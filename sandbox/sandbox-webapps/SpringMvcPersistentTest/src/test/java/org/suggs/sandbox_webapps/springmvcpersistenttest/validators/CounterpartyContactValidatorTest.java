package org.suggs.sandbox_webapps.springmvcpersistenttest.validators;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.CounterpartyContact;
import org.suggs.sandbox_webapps.springmvcpersistenttest.validators.support.AbstractValidatorTest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;

/**
 * Parameterized test class for the Counterparty Contact validator.
 * <p/>
 * User: suggitpe Date: 10/03/11 Time: 12:08
 */
public class CounterpartyContactValidatorTest extends AbstractValidatorTest<CounterpartyContactValidator, CounterpartyContact> {

    public CounterpartyContactValidatorTest( CounterpartyContact aCounterpartyContact, Boolean aExpectedValidatorResult ) {
        super( aCounterpartyContact, aExpectedValidatorResult );
    }

    @Override
    protected CounterpartyContactValidator createValidator() {
        return new CounterpartyContactValidator();
    }

    @Parameterized.Parameters
    @SuppressWarnings("boxing")
    public static Collection createTestInput() {
        Object[][] data = new Object[][]{
                { new CounterpartyContact( "First Nam", "Last Name", "Address", "Postcode", "Telephone" ), Boolean.TRUE },
                { new CounterpartyContact(), Boolean.FALSE },
                { new CounterpartyContact( "", "", "", "", "" ), Boolean.FALSE },
                { new CounterpartyContact( "First Name", "", "", "", "" ), Boolean.FALSE },
                { new CounterpartyContact( "First Name", "bar", "", "", "" ), Boolean.FALSE } };
        return Arrays.asList( data );
    }
}
