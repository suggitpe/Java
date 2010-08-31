/*
 * GameTest.java created on 25 Aug 2010 20:00:52 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.basicscenario;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * TODO Write javadoc for GameTest
 * 
 * @author suggitpe
 * @version 1.0 25 Aug 2010
 */
public class GameTest {

    private static final Log LOG = LogFactory.getLog( GameTest.class );

    private Game toggleGame;

    @Before
    public void onSetup() {
        LOG.debug( "--------------------" );
        toggleGame = new Game( 2, 2 );
    }

    @Test
    public void createGameWith0RowAnd0Column() {
        Game game = new Game( 0, 0 );
        assertThat( game.asString(), equalTo( "" ) );
    }

    @Test
    public void createGameWith1RowAnd1Column() {
        Game game = new Game( 1, 1 );
        assertThat( game.asString(), equalTo( "." ) );
    }

    @Test
    public void createGameWith2RowAnd1Column() {
        Game game = new Game( 1, 2 );
        assertThat( game.asString(), equalTo( ".\n." ) );
    }

    @Test
    public void createGameWith1RowAnd2Column() {
        Game game = new Game( 2, 1 );
        assertThat( game.asString(), equalTo( ".." ) );
    }

    @Test
    public void createGameWith2RowAnd2Column() {
        Game game = new Game( 2, 2 );
        assertThat( game.asString(), equalTo( "..\n.." ) );
    }

    @Test
    public void toggleGameWorksForPoint00() {
        toggleGame.toggleCellAt( 0, 0 );
        assertThat( toggleGame.asString(), equalTo( "X.\n.." ) );
    }

    @Test
    public void toggleGameWorksForPoint01() {
        toggleGame.toggleCellAt( 0, 1 );
        assertThat( toggleGame.asString(), equalTo( "..\nX." ) );
    }

    @Test
    public void toggleGameWorksForPoint10() {
        toggleGame.toggleCellAt( 1, 0 );
        assertThat( toggleGame.asString(), equalTo( ".X\n.." ) );
    }

    @Test
    public void toggleAndUntoggleWorksForSamePoint() {
        toggleGame.toggleCellAt( 0, 0 );
        assertThat( toggleGame.asString(), equalTo( "X.\n.." ) );
        toggleGame.toggleCellAt( 0, 0 );
        assertThat( toggleGame.asString(), equalTo( "..\n.." ) );
    }
}
