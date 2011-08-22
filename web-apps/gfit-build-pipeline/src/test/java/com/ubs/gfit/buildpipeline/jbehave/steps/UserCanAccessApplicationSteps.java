package com.ubs.gfit.buildpipeline.jbehave.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import com.ubs.gfit.buildpipeline.pages.JbehavePages;

/**
 * Steps class for the basic scenario.
 * <p/>
 * User: suggitpe
 * Date: 17/08/11
 * Time: 19:26
 */

public final class UserCanAccessApplicationSteps extends AbstractBuildPipelineSteps {

    public UserCanAccessApplicationSteps( JbehavePages aPages ) {
        super( aPages );
    }

    @When("I try to access the application")
    public void whenUserOpensTheApplication() {
        openApplication();
    }

    @Then("the application is available")
    public void thenApplicationIsDisplayedToTheUser() {
        checkApplicationIsShown();
    }
}
