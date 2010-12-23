package com.ubs.orca.orcabridge.support;

import javax.jms.Destination;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.orca.orcabridge.jmsclient.IJmsAction;

/**
 * Stub class for the IJmsAction interface
 * 
 * @author suggitpe
 * @version 1.0 26 Oct 2009
 */
public class JmsActionTestStub implements IJmsAction {

    private static final Logger LOG = LoggerFactory.getLogger( JmsActionTestStub.class );

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsAction#actionInTransaction(javax.jms.Session,
     *      javax.jms.Destination)
     */
    @Override
    public void actionInTransaction( Session aSession, Destination aDestination ) {
        LOG.debug( "***** Stub JMS Action - Performing Action" );
    }
}
