package org.suggs.sandbox.test.builders;

import org.suggs.sandbox.test.builders.pojo.Balance;
import org.suggs.sandbox.test.builders.pojo.Company;
import org.suggs.sandbox.test.builders.pojo.Posting;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.suggs.sandbox.test.builders.traditionalbuilders.BalanceBuilder.aBalance;
import static org.suggs.sandbox.test.builders.traditionalbuilders.CompanyBuilder.aCompany;
import static org.suggs.sandbox.test.builders.traditionalbuilders.PostingBuilder.aPosting;

/**
 * Test class to demonstrate the use of the traditional builder pattern to create data objects for testing.
 * <p/>
 * User: suggitpe Date: 1/10/12 Time: 7:08 PM
 */

public class TraditionalBuilderTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TraditionalBuilderTest.class );

    private static final String NAME = "foobar";
    private static final String CURRENCY = "CHF";
    private static final double AMOUNT = 100.0d;

    @Test
    public void shouldCreatePostingUsingBuilder() {
        Posting posting = aPosting().withName( NAME ).withPostingValue( AMOUNT ).build();
        assertThat( posting.getPostingName(), is( NAME ) );
        assertThat( posting.getPostingValue(), is( AMOUNT ) );
    }

    @Test
    public void shouldCreateCompanyUsingBuilder() {
        Company company = aCompany().withCompanyName( NAME ).withSimpleFlagSetTo( true ).build();
        assertThat( company.getCompanyName(), is( NAME ) );
        assertThat( company.isSimpleFlag(), is( true ) );
    }

    @Test
    public void shouldCreateBalanceUsingBuilder() {
        Balance balance = aBalance().withCurrencyAndAmount( CURRENCY, AMOUNT ).build();
        assertThat( balance.getCurrency(), is( CURRENCY ) );
        assertThat( balance.getAmount(), is( AMOUNT ) );
    }

}
