/*
 * TrueGuardStub.java created on 3 Sep 2009 19:50:26 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.support;

import org.suggs.libs.statemachine.StateMachineContext;
import org.suggs.libs.statemachine.StateTransitionGuard;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Stub implementation of the state transition guard that always returns true
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2009
 */
public class TrueGuardStub implements StateTransitionGuard {

    private static final Log LOG = LogFactory.getLog( TrueGuardStub.class );

    /**
     * @see org.suggs.libs.statemachine.StateTransitionGuard#evaluateGuard(org.suggs.libs.statemachine.StateMachineContext)
     */
    @Override
    public boolean evaluateGuard( StateMachineContext aContext ) {
        LOG.debug( "Executing stub implementation of the evaluateGuard that always returns true" );
        return true;
    }
}
