package org.suggs.sandbox_webapps.springmvcpersistenttest.jbehave;

import org.suggs.sandbox_webapps.springmvcpersistenttest.jbehave.pages.Pages;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Steps class for the SpringMvcPersistenceTest stories.
 * <p/>
 * User: suggitpe
 * Date: 21/04/11
 * Time: 16:36
 */

public class PersistenceTestSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( PersistenceTestSteps.class );

    private final Pages pages;

    public PersistenceTestSteps( Pages aPages ) {
        pages = aPages;
    }

    @Given("user is on Home page")
    public void userIsOnHomePage() {
        pages.home().open();
    }

    @When("user opens Ping Test page")
    public void userOpensPingTestPage() {
        pages.pingTest().open();
    }

    @Then("Ping Test page is shown")
    public void pingTestPageIsShown() {
        pages.pingTest().pageIsShown();
    }

    @When("user returns to home page")
    public void userReturnsToHomePage() {
        pages.pingTest().returnToHome();
    }

    @Then("Home page is shown")
    public void homePageIsShown() {
        pages.home().pageIsShown();
    }


}
