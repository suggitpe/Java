package org.suggs.sandbox.test.easyb

import org.suggs.sandbox.test.basicscenario.Game

description "This story is a test story based around a simple grid system"

narrative "I want to have a game where I can create a grid and toggle the grid points as I go along", {
    as a "person who likes to play games"
    i want "to play a game of life with myself"
    so that "I can fulfil my day"
}

scenario "With a square grid I can change various points on the grid", {

    def Game game

    given "a 5 by 5 game", {
        game = new Game(5, 5)
    }

    when "I toggle the cell at (2, 3)", {
        game.toggleCellAt(2, 3)
    }

    then "the grid should look like", {
        game.asString().shouldBe """.....
.....
.....
..X..
....."""
    }

    when "I toggle cell at (2, 4)", {
        game.toggleCellAt(2, 4)
    }

    then "the grid should look like", {
        game.asString().shouldBe """.....
.....
.....
..X..
..X.."""
    }

    when "I toggle the cell at (2, 3)", {
        game.toggleCellAt(2, 3)
    }

    then "the grid should look like", {
        game.asString().shouldBe """.....
.....
.....
.....
..X.."""
    }

}

scenario "With a rectangular  grid I can change various points on the grid", {

    def Game game

    given "a 6 by 2 game", {
        game = new Game(6, 2)
    }

    when "I toggle the cell at (0, 0)", {
        game.toggleCellAt(0, 0)
    }

    then "the grid should look like", {
        game.asString().shouldBe """X.....
......"""
    }

    when "I toggle the cell at (1, 1)", {
        game.toggleCellAt(1, 1)
    }

    then "the grid should look like", {
        game.asString().shouldBe """X.....
.X...."""
    }

    when "I toggle the cell at (0, 0)", {
        game.toggleCellAt(0, 0)
    }

    then "the grid should look like", {
        game.asString().shouldBe """......
.X...."""
    }

    when "I toggle the cell at (1, 1)", {
        game.toggleCellAt(1, 1)
    }

    then "the grid should look like", {
        game.asString().shouldBe """......
......"""
    }

    when "I toggle the cell at (4, 0)", {
        game.toggleCellAt(4, 0)
    }

    then "the grid should look like", {
        game.asString().shouldBe """....X.
......"""
    }

    when "I toggle the cell at (2, 1)", {
        game.toggleCellAt(2, 1)
    }

    then "the grid should look like", {
        game.asString().shouldBe """....X.
..X..."""
    }

}
