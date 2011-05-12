package org.suggs.sandbox_webapps.jbehave.steps;

import org.suggs.sandbox_webapps.jbehave.pages.Pages;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 12/05/11
 * Time: 07:24
 */

public class JBehaveWebTestSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( JBehaveWebTestSteps.class );

    private final Pages pages;

    public JBehaveWebTestSteps( Pages aPages ) {
        pages = aPages;
    }

    @Given("user is on Home page")
    public void openHomePage() {
        pages.home().open();
    }

    @When("user opens funky page")
    public void openFunkyPage() {
        pages.home().openFunkyPage();
    }

    @Then("funky page is shown")
    public void funkyPageIsShown() {
        pages.funkyPage().pageIsShown();
    }

    @When("user returns to home page")
    public void returnToHomeLink() {
        pages.funkyPage().returnToHomePage();
    }

    @Then("Home page is shown")
    public void homePageIsShown() {
        pages.home().pageIsShown();
    }

    @AfterScenario
    public void closeBrowser() {
        pages.closeBrowser();
    }


}
