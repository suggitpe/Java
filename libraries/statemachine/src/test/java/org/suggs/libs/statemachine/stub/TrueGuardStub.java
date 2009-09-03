/*
 * TrueGuardStub.java created on 3 Sep 2009 19:50:26 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.stub;

import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.IStateTransitionGuard;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Stub implementation of the state transition guard that always
 * returns true
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2009
 */
public class TrueGuardStub implements IStateTransitionGuard
{

    private static final Log LOG = LogFactory.getLog( TrueGuardStub.class );

    /**
     * @see org.suggs.libs.statemachine.IStateTransitionGuard#evaluateGuard(org.suggs.libs.statemachine.IStateMachineContext)
     */
    @Override
    public boolean evaluateGuard( IStateMachineContext aContext )
    {
        LOG.debug( "Executing stub implementation of the evaluateGuard that always returns true" );
        return true;
    }
}
