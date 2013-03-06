package org.suggs.sandbox.test.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.test.trader.Stock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CucumberStepdefs {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberStepdefs.class);

    private Stock stock = new Stock();

    @Given("^a stock of (.+)$")
    public void a_stock_of(String aStock) throws Throwable {
        stock.setStockName(aStock);
    }

    @Given("^a threshold of (.+)$")
    public void a_threshold_of(double aThreshold) throws Throwable {
        stock.setThreshold(aThreshold);
    }

    @When("^the stock is traded with (.+)$")
    public void the_stock_is_traded_with_(double aPrice) throws Throwable {
        stock.tradeAt(aPrice);
    }

    @Then("^the alert status will be (.+)$")
    public void the_alert_status_will_be(String aStatus) throws Throwable {
        assertThat(stock.getStatus().name(), is(aStatus));
    }

}
