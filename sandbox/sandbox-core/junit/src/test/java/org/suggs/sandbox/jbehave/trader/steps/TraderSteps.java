/*
 * TraderSteps.java created on 2 Sep 2010 07:15:03 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.steps;

import org.suggs.sandbox.jbehave.trader.Stock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Steps class to support the trader stories
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2010
 */
public class TraderSteps {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TraderSteps.class );

    private Stock stock;

    @Given("a stock of symbol $symbol and a threshold of %threshold")
    public void aStockExists( String aSymbol, Double aThreshold ) {
        stock = new Stock( aSymbol, aThreshold );
    }

    @When("the stock is traded at %price")
    public void theStockIsTradedAt( Double aPrice ) {
        stock.tradeAt( aPrice );
    }

    @Then("the alert status should be %status")
    public void theAlertStatusShouldBe( String aStatus ) {
        assertThat( stock.getStatus().name(), equalTo( aStatus ) );
    }
}
