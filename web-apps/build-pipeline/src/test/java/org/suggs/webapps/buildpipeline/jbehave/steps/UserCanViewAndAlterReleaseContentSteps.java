package org.suggs.webapps.buildpipeline.jbehave.steps;

import org.suggs.webapps.buildpipeline.pages.JbehavePages;

import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Steps class for viewing and altering release content
 * <p/>
 * User: suggitpe
 * Date: 18/08/11
 * Time: 07:35
 */

public final class UserCanViewAndAlterReleaseContentSteps extends AbstractBuildPipelineSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanViewAndAlterReleaseContentSteps.class );

    public UserCanViewAndAlterReleaseContentSteps( JbehavePages aPages ) {
        super( aPages );
    }

    @Given("an existing release with a description of $description")
    public void givenAnExistingReleaseWithADescriptionOf( @Named("description") String aDescription ) {
        //createVersionBuilder().withDescription(aDescription);

        pages().releaseManagementPage().open();
        pages().releaseManagementPage().requestNewRelease();
        pages().releaseVersionForm().isShownInNewForm();
        pages().releaseVersionForm().setDescription( aDescription );
        pages().releaseVersionForm().completeNew();
        pages().releaseManagementPage().isShown();
    }

    @Given("user is on Release Management page")
    public void givenUserIsOnReleaseManagementPage() {
        // remove

        pages().releaseManagementPage().open();
    }

    @When("user opens release with a description of $description")
    public void whenUserOpensReleaseWithADescriptionOf( @Named("description") String aDescription ) {
        // openRelease().withDescription(aDescription);

        //pages().releaseManagementPage().findLinkForReleaseVersionDesription( aDescription ).click();
        pages().releaseVersionShow().isShown();
    }

    @Then("the user can see the contents of the release described as $description")
    public void thenTheUserCanSeeTheContentsOfTheRelease( @Named("description") String aDescription ) {
        // getCuurentRelease()

        pages().releaseVersionShow().isShown();
        pages().releaseVersionShow().ensureDescribes( aDescription );
    }

    @Then("the user can change the description from $initialDescription to $alteredDescription")
    public void thenTheUserCanChangeTheDescription( @Named("initialDescription") String aInitialDescription,
                                                    @Named("alteredDescription") String aAlteredDescription ) {
        pages().releaseVersionShow().isShown();
        String version = pages().releaseVersionShow().getVersion();
        pages().releaseVersionShow().editRelease();
        pages().releaseVersionForm().isShownInEditForm();
        pages().releaseVersionForm().setDescription( aAlteredDescription );
        pages().releaseVersionForm().completeUpdate();
        pages().releaseManagementPage().open();
        //String sameVersion = pages().releaseManagementPage().findVersionWithDescription( aAlteredDescription );
        //assertThat( version, equalTo( sameVersion ) );
    }

    @When("the user deletes the release with the description of $description")
    public void whenTheUserDeletesTheReleaseWithTheDescriptionOf( @Named("description") String aDescription ) {
        pages().releaseManagementPage().open();
        //String version = pages().releaseManagementPage().findVersionWithDescription( aDescription );
        //LOG.debug( "Planning to delete release version [" + version + "]" );
        //pages().releaseManagementPage().findLinkForReleaseVersionDesription( aDescription ).click();
        pages().releaseVersionShow().editRelease();
        pages().releaseVersionForm().completeDelete();
    }

    @Then("no release exists with a description of $description")
    @Pending
    public void thenNoReleaseExistsWithADescriptionOf( @Named("description") String aDescription ) {
        pages().releaseManagementPage().open();
        pages().releaseManagementPage().assertNoReleaseWithDescriptionOf( aDescription );
    }


}
