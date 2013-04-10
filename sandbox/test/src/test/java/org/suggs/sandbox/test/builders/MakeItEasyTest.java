package org.suggs.sandbox.test.builders;

import org.suggs.sandbox.test.builders.pojo.Company;
import org.suggs.sandbox.test.builders.pojo.Posting;
import org.suggs.sandbox.test.builders.pojo.Balance;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.natpryce.makeiteasy.MakeItEasy.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.suggs.sandbox.test.builders.makeiteasy.CompanyMaker.*;
import static org.suggs.sandbox.test.builders.makeiteasy.PostingMaker.*;
import static org.suggs.sandbox.test.builders.makeiteasy.BalanceMaker.*;

/**
 * Test class to demonstrate how to use the make it easy tool set.
 */
public class MakeItEasyTest {

    private static final String NAME = "foobar";
    private static final String CURRENCY = "CHF";
    private static final double AMOUNT = 100.0d;

    @Test
    public void shouldCreatePostingUsingInstantiator() {
        Posting posting = make( a( Posting, with( postingName, NAME ), with( postingValue, AMOUNT ) ) );
        assertThat( posting.getPostingName(), is( NAME ) );
        assertThat( posting.getPostingValue(), is( AMOUNT ) );
    }

    @Test
    public void shouldCreateCompanyUsingInstantiator() {
        Company company = make( a( Company, with( companyName, NAME ), with( simpleFlag, Boolean.TRUE ) ) );
        assertThat( company.getCompanyName(), is( NAME ) );
        assertThat( company.isSimpleFlag(), is( true ) );
    }
    
    @Test
    public void shouldCreateBalanceUsingInstantiator(){
        Balance balance = make( a( Balance, with( balanceCurrency, CURRENCY ), with(balanceAmount, AMOUNT)  ));
        assertThat( balance.getCurrency(), is( CURRENCY ) );
        assertThat( balance.getAmount(), is( AMOUNT ) );
    }
}
