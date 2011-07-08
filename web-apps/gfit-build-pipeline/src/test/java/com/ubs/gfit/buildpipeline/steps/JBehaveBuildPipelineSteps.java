package com.ubs.gfit.buildpipeline.steps;

import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.pages.Pages;

/**
 * Class to define the steps that are mapped to the JBehave story
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

    @Given("no pre-requisites")
    public void givenNoPrerequisites() {
    }

    @When("user opens home page")
    public void whenUserOpensHomePage() {
        pages.homePage().open();
    }

    @Then("home page is displayed to the user")
    public void thenHomePageIsDisplayedToTheUser() {
        pages.homePage().isShown();
    }

    @Given("user is on Release Management page")
    public void givenUserIsOnReleaseManagementPage() {
        pages.releaseManagementPage().open();
        pages.releaseManagementPage().isShown();
    }

    @When("user requests a new release")
    public void whenUserRequestsANewRelease() {
        pages.releaseManagementPage().requestNewRelease();
    }

    @Then("the new release version form is shown")
    public void thenTheNewReleaseFormIsShown() {
        pages.releaseVersionForm().isShown();
    }

    @When("the user enters a release version comment of $description")
    public void whenTheUserEntersAReleaseVersionCommentOfFoo( @Named("description" )String aDescription) {
        pages.releaseVersionForm().addDescription(aDescription);
    }

    /**
     @Then("a unique identifier is displayed on a new page")
     @Pending public void thenAUniqueIdentifierIsDisplayedOnANewPage() {
     pages.releaseManagementPage().isShown();
     initialId = pages.releaseManagementPage().uniqueIdentifierIsDisplayed();
     }


     @When("user returns to release management page")
     @Pending public void whenUserReturnsToReleaseManagementPage() {
     pages.versionDisplayPage().returnToReleaseManagementPage();
     pages.releaseManagementPage().isShown();
     }

     @When("requests a new release")
     @Pending public void whenRequestsANewRelease() {
     pages.releaseManagementPage().requestNewRelease();
     }

     @Then("a different unique identifier is displayed on a new page")
     @Pending public void thenADifferentUniqueIdentifierIsDisplayedOnANewPage() {
     secondId = pages.versionDisplayPage().uniqueIdentifierIsDisplayed();
     assertThat( initialId, not( equalTo( secondId ) ) );
     }

     */


}
