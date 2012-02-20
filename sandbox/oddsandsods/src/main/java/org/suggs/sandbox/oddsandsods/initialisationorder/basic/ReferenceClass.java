/*
 * ReferenceClass.java created on 21 Sep 2007 07:06:50 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Write javadoc for ReferenceClass
 * 
 * @author suggitpe
 * @version 1.0 21 Sep 2007
 */
public final class ReferenceClass {

    private static final Logger LOG = LoggerFactory.getLogger( ReferenceClass.class );

    /**
     * Constructs a new instance.
     * 
     * @param aName
     */
    public ReferenceClass( String aName ) {
        LOG.debug( "Constructing Reference class [" + aName + "]" );
    }

}
