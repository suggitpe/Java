package com.ubs.gfit.buildpipeline.jbehave.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.jbehave.JbehavePages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Steps class for the basic scenario.
 * <p/>
 * User: suggitpe
 * Date: 17/08/11
 * Time: 19:26
 */

public final class UserCanAccessApplicationSteps extends AbstractBuildPipelineSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanAccessApplicationSteps.class );

    public UserCanAccessApplicationSteps( JbehavePages aPages ) {
        super( aPages );
    }

    @When("I try to access the application")
    public void whenUserOpensHomePage() {
        pages().homePage().open();
    }

    @Then("the application is available")
    public void thenHomePageIsDisplayedToTheUser() {
        assertThat( pages().homePage().isShown(), is( true ) );
    }
}
