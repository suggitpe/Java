package org.suggs.webapps.buildpipeline.jbehave.steps;

import org.suggs.webapps.buildpipeline.dsl.Component;
import org.suggs.webapps.buildpipeline.dsl.ComponentVersion;
import org.suggs.webapps.buildpipeline.dsl.DSL;
import org.suggs.webapps.buildpipeline.dsl.ReleaseVersion;
import org.suggs.webapps.buildpipeline.pages.impl.SeleniumPages;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class to manage the steps used in teh JBehave test.
 * <p/>
 * User: suggitpe Date: 15/09/11 Time: 18:16
 */

public class UserCanCreateReleaseVersionsSteps extends DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanCreateReleaseVersionsSteps.class );

    private ReleaseVersion releaseVersion;
    private ReleaseVersion releaseVersionOther;
    private Component component;
    private ComponentVersion componentVersion;

    public UserCanCreateReleaseVersionsSteps() {
        super( new SeleniumPages() );
    }

    @When("I create a new release with a description of $description")
    public void whenICreateANewReleaseWithADescriptionOf( @Named("decription") String aDescription ) {
        releaseVersion = createReleaseWithDescription( aDescription );
    }

    @Then("a new release is created with a description of $description")
    public void thenANewReleaseIsCreatedWithADescriptionOf( @Named("description") String aDescription ) {
        assertThat( releaseVersion.getDescription(), is( equalTo( aDescription ) ) );
    }

    @Given("an existing release")
    public void givenAnExistingRelease() {
        releaseVersion = createRelease();
    }

    @When("I create a new release")
    public void whenICreateANewRelease() {
        releaseVersionOther = createRelease();
    }

    @Then("the version numbers of the releases are different")
    public void thenTheVersionNumbersOfTheReleasesAreDifferent() {
        assertThat( releaseVersion.getVersionNumber(), is( not( equalTo( releaseVersionOther.getVersionNumber() ) ) ) );
    }

    @Given("an existing version $componentVersion of component $componentName")
    public void givenAnExistingVersionOfComponent( @Named("componentVersion") String aComponentVersion, @Named("componentName") String aComponentName ) {
        if ( component == null ) {
            component = createComponent( aComponentName );
        }
        component.addVersion( aComponentVersion );
    }

    @When("I create a new release with version $componentversion of component $componentName")
    public void whenICreateANewReleaseWithVersionOfComponent( @Named("componentVersion") String aComponentVersion, @Named("componentName") String aComponentName ) {
        assertThat( component.getName(), is( equalTo( aComponentName ) ) );
        componentVersion = component.addVersion( aComponentVersion );
        releaseVersion = createReleaseWithComponentVersion( componentVersion );
    }

    @Then("the release should contain a version 1.2 of component MyNewComponent")
    public void thenTheReleaseShouldContainAVersion12OfComponentMyNewComponent() {
        assertThat( releaseVersion.getVersionOfComponent( component ), is( equalTo( componentVersion.getVersionNumber() ) ) );
    }
}
