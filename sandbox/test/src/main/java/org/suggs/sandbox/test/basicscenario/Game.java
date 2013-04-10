/*
 * Game.java created on 24 Aug 2010 07:36:14 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.test.basicscenario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {

    private static final Logger LOG = LoggerFactory.getLogger( Game.class );

    private char[][] grid;

    public Game( int aWidth, int aHeight ) {
        buildGrid( aWidth, aHeight );
    }

    private void buildGrid( int aWidth, int aHeight ) {
        LOG.debug( "Building a grid with rows(height)=[" + aHeight + "] and cols(width)=[" + aWidth + "]" );
        grid = new char[aHeight][aWidth];
        populateGridWithDots();
    }

    private void populateGridWithDots() {
        for ( int row = 0; row < grid.length; ++row ) {
            for ( int cell = 0; cell < grid[row].length; ++cell ) {
                grid[row][cell] = '.';
            }
        }
    }

    public String asString() {
        StringBuilder builder = new StringBuilder();
        int maxRows = grid.length;
        for ( int row = 0; row < maxRows; ++row ) {
            for ( int cell = 0; cell < grid[row].length; ++cell ) {
                builder.append( grid[row][cell] );
            }
            if ( ( row + 1 ) != maxRows ) {
                builder.append( '\n' );
            }
        }
        return builder.toString();
    }

    public void toggleCellAt( int aColumn, int aRow ) {
        LOG.debug( "Toggling cell [" + aRow + ", " + aColumn + "]" );
        if ( grid[aRow][aColumn] == '.' ) {
            grid[aRow][aColumn] = 'X';
        }
        else {
            grid[aRow][aColumn] = '.';
        }
    }

}
