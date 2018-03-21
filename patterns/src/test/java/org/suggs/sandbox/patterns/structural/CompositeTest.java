/*
 * CompositeTestCase.java created on 7 Sep 2007 06:11:52 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.suggs.sandbox.patterns.AbstractPatternTest;
import org.suggs.sandbox.patterns.structural.composite.IMenuComponent;
import org.suggs.sandbox.patterns.structural.composite.MenuComponent;
import org.suggs.sandbox.patterns.structural.composite.MenuItem;
import org.suggs.sandbox.patterns.structural.composite.Waitress;

import org.junit.Test;

/**
 * Test case for the composite pattern.
 */
public class CompositeTest extends AbstractPatternTest {

    @Test
    public void testCompositeWaitress() {
        IMenuComponent allMenus = buildMenus();
        Waitress w = new Waitress( allMenus );

        w.printMenu();
    }

    @Test
    public void testCompositeIterator() {
        IMenuComponent allMenus = buildMenus();
        Waitress w = new Waitress( allMenus );

        w.printVegetarianMenu();
    }

    private IMenuComponent buildMenus() {
        // build top level menu
        IMenuComponent allMenus = new MenuComponent( "ALL MENUS", "All the menus" );

        // now build lower branches
        IMenuComponent pancakeHouseMenu = new MenuComponent( "PANCAKE HOUSE MENU", "Breakfast" );
        IMenuComponent dinerMenu = new MenuComponent( "DINER MENU", "Lunch" );
        IMenuComponent cafeMenu = new MenuComponent( "CAFE MENU", "Dinner" );
        IMenuComponent dessertMenu = new MenuComponent( "DESSERT MENU", "Good old fashioned dessert" );

        allMenus.add( pancakeHouseMenu );
        allMenus.add( dinerMenu );
        allMenus.add( cafeMenu );

        // now add all of the items
        pancakeHouseMenu.add( new MenuItem( "K&B's pancake breakfast",
                                            "Pancakes with scrambled egss and toast",
                                            true,
                                            2.99d ) );
        pancakeHouseMenu.add( new MenuItem( "Regular pancake breakfast",
                                            "Pancakes with fried eggs and sausage",
                                            false,
                                            2.99d ) );
        pancakeHouseMenu.add( new MenuItem( "Blueberry pancakes",
                                            "Pancakes made with fresh blueberries",
                                            true,
                                            3.49d ) );
        pancakeHouseMenu.add( new MenuItem( "Waffles", "Waffles with your choice of topping", true, 3.59d ) );

        dinerMenu.add( new MenuItem( "Vegetarian BLT",
                                     "'Fakin' bacon with lettuce & tomato on whie bread",
                                     true,
                                     2.99d ) );
        dinerMenu.add( new MenuItem( "BLT", "Bacon lettuce and tomate on whole wheat", false, 2.99d ) );
        dinerMenu.add( new MenuItem( "Soup of the day", "soup of the day with a bread roll", false, 3.29d ) );
        dinerMenu.add( new MenuItem( "Hot Dog", "A hot dog with all the trimmings", false, 3.05d ) );

        cafeMenu.add( new MenuItem( "Steak and Chips",
                                    "Fillet steak with lashings of pepper sauce",
                                    false,
                                    13.99d ) );
        cafeMenu.add( new MenuItem( "Spaghetti Bologneise", "Like mama used to make", false, 8.99d ) );
        cafeMenu.add( new MenuItem( "Fish and Chips", "Cod in beer batter and potatoe wedges", false, 6.49d ) );
        cafeMenu.add( new MenuItem( "Sausage 'n mash",
                                    "Apple and cider sausages with egg spaghetti",
                                    false,
                                    7.49d ) );

        dessertMenu.add( new MenuItem( "Spotted Dick ", "Sponge with currents and fruit zest", true, 3.99d ) );
        dessertMenu.add( new MenuItem( "Fresh fruit",
                                       "A select of the freshest fruit known to man!",
                                       true,
                                       3.99d ) );
        dessertMenu.add( new MenuItem( "Hot chocolate fudge cake",
                                       "Chocolate fudge cake warmed and covered with cream or ice-cream",
                                       true,
                                       3.99d ) );
        dessertMenu.add( new MenuItem( "Rhubarb crumble", "An old favourite", true, 3.99d ) );
        dessertMenu.add( new MenuItem( "Cheese platter",
                                       "A selection of cheeses from around thre world",
                                       true,
                                       5.99d ) );

        // now add the completed sub-menu to the structure
        cafeMenu.add( dessertMenu );

        return allMenus;
    }
}
