/*
 * TestClass.java created on 20 Sep 2007 17:55:22 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.simple;

/**
 * Simple class that will extend the SuperClass with its own impl of
 * the printThree method.
 * 
 * @author suggitpe
 * @version 1.0 20 Sep 2007
 */
public class TestClass extends SuperClass
{

    int three = (int) Math.PI; // ie 3

    /**
     * @see org.suggs.sandbox.oddsandsods.initialisationorder.simple.SuperClass#printThree()
     */
    @Override
    public void printThree()
    {
        System.out.println( three );
    }
}
