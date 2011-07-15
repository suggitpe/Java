package com.ubs.gfit.buildpipeline.steps;

import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.pages.Pages;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
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

    @When("user creates a new release with a description of $description")
    public void whenUserRequestsANewReleaseWithADescriptionOfFoo( @Named("description") String aDescription ) {
        pages.releaseManagementPage().requestNewRelease();
        pages.releaseVersionForm().isShown();
        pages.releaseVersionForm().addDescription( aDescription );
        pages.releaseVersionForm().completeNew();
        pages.releaseManagementPage().isShown();
    }

    @Given("an existing release with a description of $description")
    public void givenAnExistingReleaseWithADescriptionOfViewable( @Named("description") String aDescription ) {
        whenUserRequestsANewReleaseWithADescriptionOfFoo( aDescription );
    }

    @Then("a new release is displayed with a description of $description")
    public void thenANewReleaseIsDisplayedWithADescriptionOfFoo( @Named("description") String aDescription ) {
        int version = pages.releaseManagementPage().findVersionWithDescription( aDescription );
        assertThat( version, not( equalTo( 0 ) ) );
    }

    @Then("the release number of $description1 is different to the release number of $description2")
    public void thenTheReleaseNumberOfFooIsDifferentToTheReleaseNumberOfBar( @Named("description1") String aDescription1,
                                                                             @Named("description2") String aDescription2 ) {
        int version1 = pages.releaseManagementPage().findVersionWithDescription( aDescription1 );
        int version2 = pages.releaseManagementPage().findVersionWithDescription( aDescription2 );
        assertThat( version1, not( equalTo( version2 ) ) );
    }

    @When("user requests a new release")
    public void whenUserRequestsANewRelease() {
        pages.releaseManagementPage().requestNewRelease();
        pages.releaseVersionForm().isShown();
    }

    @When("user opens release with a description of $description")
    public void whenUserOpensReleaseWithADescriptionOfViewable( @Named("description") String aDescription ) {
        pages.releaseManagementPage().findLinkForReleaseVersionDesription( aDescription ).click();
    }

    @Then("the user can see the contents of the release described as $description")
    public void thenTheUserCanSeeTheContentsOfTheRelease(@Named( "description") String aDescription) {
        pages.releaseVersionShow().isShown();
        pages.releaseVersionShow().ensureDescribes( aDescription );
    }

}
