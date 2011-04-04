/*
 * GridSteps.java created on 24 Aug 2010 07:28:04 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.basicscenario.steps;

import org.suggs.sandbox.jbehave.basicscenario.Game;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * TODO Write javadoc for GridSteps
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2010
 */
public class GridSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( GridSteps.class );

    private Game game;

    @Given("a $width by $height game")
    public void theGameIsRunning( int width, int height ) {
        game = new Game( width, height );
    }

    @When("I toggle the cell at ($column, $row)")
    public void iToggleTheCellAt( int column, int row ) {
        game.toggleCellAt( column, row );
    }

    @Then("the grid should look like $grid")
    public void theGridShouldLookLike( String grid ) {
        assertThat( game.asString(), equalTo( grid ) );
    }

}
