package org.suggs.webapps.buildpipeline.jbehave.steps;

import org.suggs.webapps.buildpipeline.pages.impl.JbehavePages;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

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

    public UserCanAccessApplicationSteps( JbehavePages aPages ) {
        super( aPages );
    }

    @When("I open the application")
    public void whenUserOpensTheApplication() {
        openApplication();
    }

    @Then("the application is available")
    public void thenApplicationIsDisplayedToTheUser() {
        assertThat(applicationIsOpen(), is(true));
    }
}
