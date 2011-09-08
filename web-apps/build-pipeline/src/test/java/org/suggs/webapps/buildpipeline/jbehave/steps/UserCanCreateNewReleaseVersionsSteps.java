package org.suggs.webapps.buildpipeline.jbehave.steps;

import org.suggs.webapps.buildpipeline.pages.JbehavePages;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Steps class for the create new version story.
 * <p/>
 * User: suggitpe
 * Date: 18/08/11
 * Time: 19:25
 */

public final class UserCanCreateNewReleaseVersionsSteps extends AbstractBuildPipelineSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanCreateNewReleaseVersionsSteps.class );

    public UserCanCreateNewReleaseVersionsSteps( JbehavePages aPagesObject ) {
        super(aPagesObject);
    }

    @Given("user is on Release Management page")
    public void givenUserIsOnReleaseManagementPage() {
        //remove

        pages().releaseManagementPage().open();
    }

    @When("user creates a new release with a description of $description")
    public void whenUserRequestsANewReleaseWithADescriptionOf( @Named("description") String aDescription ) {
        //createNewRelease().withDescription(aDescription);

        pages().releaseManagementPage().requestNewRelease();
        pages().releaseVersionForm().isShownInNewForm();
        pages().releaseVersionForm().setDescription( aDescription );
        pages().releaseVersionForm().completeNew();
        pages().releaseManagementPage().isShown();
    }

    @Then("a new release is displayed with a description of $description")
    public void thenANewReleaseIsDisplayedWithADescriptionOf( @Named("description") String aDescription ) {

        //String version = pages().releaseManagementPage().findVersionWithDescription( aDescription );
        //assertThat( version, not( equalTo( "NEW" ) ) );
    }

    @Given("an existing release with a description of $description")
    public void givenAnExistingReleaseWithADescriptionOf( @Named("description") String aDescription ) {
        //createNewRelease().withDescription(aDescription);

        pages().releaseManagementPage().open();
        whenUserRequestsANewReleaseWithADescriptionOf( aDescription );
    }

    @Then("the release number of $description1 is different to the release number of $description2")
    public void thenTheReleaseNumberIsDifferentToTheOtherReleaseNumber( @Named("description1") String aDescription1,
                                                                        @Named("description2") String aDescription2 ) {
        //String version1 = pages().releaseManagementPage().findVersionWithDescription( aDescription1 );
        //String version2 = pages().releaseManagementPage().findVersionWithDescription( aDescription2 );
        //assertThat( version1, not( equalTo( version2 ) ) );
    }

    @When("user requests a new release")
    public void whenUserRequestsANewRelease() {
        //createNewRelease();

        pages().releaseManagementPage().requestNewRelease();
        pages().releaseVersionForm().isShownInNewForm();
    }
}
