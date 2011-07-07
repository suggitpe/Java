package com.ubs.gfit.buildpipeline.steps;

import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.pages.Pages;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 19:09
 */

public final class JBehaveBuildPipelineSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( JBehaveBuildPipelineSteps.class );

    private Pages pages;
    private int initialId = 0;
    private int secondId = 0;

    public JBehaveBuildPipelineSteps( Pages aPages ) {
        pages = aPages;
    }

    @AfterScenario
    public void closeBrowser() {
        pages.closeBrowser();
    }

    @Given("user is on release management page")
    public void givenUserIsOnReleaseManagementPage() {
        pages.releaseManagementPage().open();
        pages.releaseManagementPage().pageIsShown();
    }

    @When("user requests a new release")
    @Pending
    public void whenUserRequestsANewRelease() {
        pages.releaseManagementPage().requestNewRelease();
    }

    @Then("a unique identifier is displayed on a new page")
    @Pending
    public void thenAUniqueIdentifierIsDisplayedOnANewPage() {
        pages.versionDisplayPage().pageIsShown();
        initialId = pages.versionDisplayPage().uniqueIdentifierIsDisplayed();
    }


    @When("user returns to release management page")
    @Pending
    public void whenUserReturnsToReleaseManagementPage() {
        pages.versionDisplayPage().returnToReleaseManagementPage();
        pages.releaseManagementPage().pageIsShown();
    }

    @When("requests a new release")
    @Pending
    public void whenRequestsANewRelease() {
        pages.releaseManagementPage().requestNewRelease();
    }

    @Then("a different unique identifier is displayed on a new page")
    @Pending
    public void thenADifferentUniqueIdentifierIsDisplayedOnANewPage() {
        secondId = pages.versionDisplayPage().uniqueIdentifierIsDisplayed();
        assertThat( initialId, not( equalTo( secondId ) ) );
    }

    @Given("no pre-requisites")
    public void givenNoPrerequisites() {
    }

    @When("user opens home page")
    public void whenUserOpensHomePage() {
        pages.homePage().open();
    }

    @Then("home page is displayed to the user")
    public void thenHomePageIsDisplayedToTheUser() {
        pages.homePage().pageIsShown();
    }


}
