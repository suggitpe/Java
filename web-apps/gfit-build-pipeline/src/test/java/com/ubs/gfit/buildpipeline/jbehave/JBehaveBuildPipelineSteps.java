package com.ubs.gfit.buildpipeline.jbehave;

import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private JbehavePages pages;

    public JBehaveBuildPipelineSteps( JbehavePages aPages ) {
        pages = aPages;
    }

    @AfterStories
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
    public void whenUserRequestsANewReleaseWithADescriptionOf( @Named("description") String aDescription ) {
        pages.releaseManagementPage().requestNewRelease();
        pages.releaseVersionForm().isShownInNewForm();
        pages.releaseVersionForm().setDescription( aDescription );
        pages.releaseVersionForm().completeNew();
        pages.releaseManagementPage().isShown();
    }

    @Given("an existing release with a description of $description")
    public void givenAnExistingReleaseWithADescriptionOf( @Named("description") String aDescription ) {
        pages.releaseManagementPage().open();
        whenUserRequestsANewReleaseWithADescriptionOf( aDescription );
    }

    @Then("a new release is displayed with a description of $description")
    public void thenANewReleaseIsDisplayedWithADescriptionOf( @Named("description") String aDescription ) {
        String version = pages.releaseManagementPage().findVersionWithDescription( aDescription );
        assertThat( version, not( equalTo( "NEW" ) ) );
    }

    @Then("the release number of $description1 is different to the release number of $description2")
    public void thenTheReleaseNumberIsDifferentToTheOtherReleaseNumber( @Named("description1") String aDescription1,
                                                                        @Named("description2") String aDescription2 ) {
        String version1 = pages.releaseManagementPage().findVersionWithDescription( aDescription1 );
        String version2 = pages.releaseManagementPage().findVersionWithDescription( aDescription2 );
        assertThat( version1, not( equalTo( version2 ) ) );
    }

    @When("user requests a new release")
    public void whenUserRequestsANewRelease() {
        pages.releaseManagementPage().requestNewRelease();
        pages.releaseVersionForm().isShownInNewForm();
    }

    @When("user opens release with a description of $description")
    public void whenUserOpensReleaseWithADescriptionOf( @Named("description") String aDescription ) {
        pages.releaseManagementPage().findLinkForReleaseVersionDesription( aDescription ).click();
        pages.releaseVersionShow().isShown();
    }

    @Then("the user can see the contents of the release described as $description")
    public void thenTheUserCanSeeTheContentsOfTheRelease( @Named("description") String aDescription ) {
        pages.releaseVersionShow().isShown();
        pages.releaseVersionShow().ensureDescribes( aDescription );
    }

    @Then("the user can change the description from $initialDescription to $alteredDescription")
    public void thenTheUserCanChangeTheDescription( @Named("initialDescription") String aInitialDescription,
                                                    @Named("alteredDescription") String aAlteredDescription ) {
        pages.releaseVersionShow().isShown();
        String version = pages.releaseVersionShow().getVersion();
        pages.releaseVersionShow().editRelease();
        pages.releaseVersionForm().isShownInEditForm();
        pages.releaseVersionForm().setDescription( aAlteredDescription );
        pages.releaseVersionForm().completeUpdate();
        pages.releaseManagementPage().open();
        String sameVersion = pages.releaseManagementPage().findVersionWithDescription( aAlteredDescription );
        assertThat( version, equalTo( sameVersion ) );
    }

    @When("the user deletes the release with the description of $description")
    public void whenTheUserDeletesTheReleaseWithTheDescriptionOf( @Named("description") String aDescription ) {
        pages.releaseManagementPage().open();
        String version = pages.releaseManagementPage().findVersionWithDescription( aDescription );
        LOG.debug( "Planning to delete release version [" + version + "]" );
        pages.releaseManagementPage().findLinkForReleaseVersionDesription( aDescription ).click();
        pages.releaseVersionShow().editRelease();
        pages.releaseVersionForm().completeDelete();
    }

    @Then("no release exists with a description of $description")
    public void thenNoReleaseExistsWithADescriptionOf( @Named("description") String aDescription ) {
        pages.releaseManagementPage().open();
        pages.releaseManagementPage().assertNoReleaseWithDescriptionOf( aDescription );
    }


}
