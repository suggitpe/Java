/*
 * TestClass.java created on 21 Sep 2007 07:06:25 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to show how all of the differing construction options take order
 * 
 * @author suggitpe
 * @version 1.0 21 Sep 2007
 */
public class TestClass {

    static final ReferenceClass REF = new ReferenceClass( "static member" );
    static final List<ReferenceClass> LIST = new ArrayList<ReferenceClass>();

    static {
        System.out.println( "Updating LIST in static initiliser" );
        LIST.add( new ReferenceClass( "ref class ctor'd in the static initialiser" ) );
    }

    {
        System.out.println( "Updating LIST in non-static initiliser" );
        LIST.add( new ReferenceClass( "ref class ctor'd in the non-static initialiser" ) );
    }

    /**
     * Constructs a new instance.
     */
    public TestClass() {
        System.out.println( "In the TestClass constructor" );
    }

}
