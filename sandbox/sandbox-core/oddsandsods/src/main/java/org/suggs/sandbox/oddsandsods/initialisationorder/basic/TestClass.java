/*
 * TestClass.java created on 21 Sep 2007 07:06:25 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.basic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to show how all of the differing construction options take order
 * 
 * @author suggitpe
 * @version 1.0 21 Sep 2007
 */
public class TestClass {

    private static final Log LOG = LogFactory.getLog( TestClass.class );
    static final ReferenceClass REF = new ReferenceClass( "static member" );
    static final List<ReferenceClass> LIST = new ArrayList<ReferenceClass>();

    static {
        LOG.debug( "Updating LIST in static initiliser" );
        LIST.add( new ReferenceClass( "ref class ctor'd in the static initialiser" ) );
    }

    {
        LOG.debug( "Updating LIST in non-static initiliser" );
        LIST.add( new ReferenceClass( "ref class ctor'd in the non-static initialiser" ) );
    }

    /**
     * Constructs a new instance.
     */
    public TestClass() {
        LOG.debug( "In the TestClass constructor" );
    }

}
