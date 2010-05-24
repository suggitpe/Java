/*
 * ReferenceClass.java created on 21 Sep 2007 07:06:50 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.basic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for ReferenceClass
 * 
 * @author suggitpe
 * @version 1.0 21 Sep 2007
 */
public final class ReferenceClass {

    private static final Log LOG = LogFactory.getLog( ReferenceClass.class );

    /**
     * Constructs a new instance.
     * 
     * @param aName
     */
    public ReferenceClass( String aName ) {
        LOG.debug( "Constructing Reference class [" + aName + "]" );
    }

}
