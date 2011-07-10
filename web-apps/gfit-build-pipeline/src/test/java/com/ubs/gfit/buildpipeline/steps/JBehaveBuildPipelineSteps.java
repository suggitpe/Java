package com.ubs.gfit.buildpipeline.steps;

import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.pages.Pages;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

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
    public void whenTheUserEntersAReleaseVersionCommentOfFoo( @Named("description") String aDescription ) {
        pages.releaseVersionForm().addDescription( aDescription );
    }

    @When("clicks Add New Release Version")
    public void whenClicksAddNewReleaseVersion() {
        pages.releaseVersionForm().completeNew();
    }

    @Then("the release management page is shown")
    public void thenTheReleaseManagementPageIsShown() {
        pages.releaseManagementPage().isShown();
    }

    @Then("a unique identifier is displayed for the release described as $description")
    public void thenAUniqueIdentifierIsDisplayedForTheReleaseDescribedAsFoo( @Named("description") String aDescription ) {
        int version = pages.releaseManagementPage().findVersionWithDescription( aDescription );
        assertThat( version, not( equalTo( 0 ) ) );
    }


}
