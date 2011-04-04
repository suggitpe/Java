package org.suggs.sandbox.easyb

import org.suggs.sandbox.jbehave.basicscenario.Game;


scenario "With a square grid I can change various points on the grid"{
    
    def Game game
    
    given "a 5 by 5 game"{
        game = Game(5,5)
    }
    when "I toggle the cell at (2, 3)"{
        
        game.toggleCellAt(2,3)
    }
    
    then "the "{  def expected = """
    .....
    .....
    .....
    ..X..
    .....""" }
}
