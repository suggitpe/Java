/*
 * TraderSteps.java created on 2 Sep 2010 07:15:03 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.test.jbehave.trader.steps;

import org.suggs.sandbox.test.jbehave.trader.Stock;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Steps class to support the trader stories.<br/>
 * Some things to note:
 * <ul>
 * <li>you need to annotate the given/when/then steps to teh story itself</li>
 * <li>the prefix to the passed in variable is defined in the configuration of the story</li>
 * <li>when using an examples table you need to use the @Named annotation for the parameters</li>
 * <li>to allow the mapping of a parameterised scenario, you need to word the steps lightly differently to the
 * default step name</li>
 * </ul>
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2010
 */
public class TraderSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TraderSteps.class );

    private Stock stock;

    @Given("a stock of symbol $symbol and a threshold of $threshold")
    @Alias("a stock of <symbol> and a threshold <threshold>")
    public void aStockExists( @Named("symbol") String aSymbol, @Named("threshold") Double aThreshold ) {
        stock = new Stock( aSymbol, aThreshold );
    }

    @When("the stock is traded at $price")
    @Alias("the stock is traded with <price>")
    public void theStockIsTradedAt( @Named("price") Double aPrice ) {
        stock.tradeAt( aPrice );
    }

    @Then("the alert status should be $status")
    @Alias("the alert status will be <status>")
    public void theAlertStatusShouldBe( @Named("status") String aStatus ) {
        assertThat( stock.getStatus().name(), equalTo( aStatus ) );
    }
}
