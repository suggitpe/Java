/*
 * Transition.java created on 10 Aug 2009 19:38:46 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection.impl;

import org.suggs.sandbox.patterns.behavioural.state.connection.IState;
import org.suggs.sandbox.patterns.behavioural.state.connection.IStateMachineEventContext;
import org.suggs.sandbox.patterns.behavioural.state.connection.ITransition;
import org.suggs.sandbox.patterns.behavioural.state.connection.ITransitionEvent;
import org.suggs.sandbox.patterns.behavioural.state.connection.ITransitionGuard;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Class to encapsulate the transition route between two states. This
 * class is responsible for all evaluation of the transition
 * evaluation.
 * 
 * @author suggitpe
 * @version 1.0 10 Aug 2009
 */
public class Transition implements ITransition, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( Transition.class );

    private String mName_;
    private IState mStartState_;
    private IState mEndState_;
    private List<ITransitionEvent> mTransitionEvents_;
    private List<ITransitionGuard> mTransitionGuards_;

    /**
     * Constructs a new instance.
     * 
     * @param aName
     *            the name of the transition
     * @param aStartState
     *            the starting state for the transition
     * @param aEndState
     *            the end state for the transition
     */
    public Transition( String aName, IState aStartState, IState aEndState )
    {
        mName_ = aName;
        mStartState_ = aStartState;
        mEndState_ = aEndState;
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mTransitionEvents_,
                        "Transition Events cannot be null on a Transition object" );
        Assert.notNull( mTransitionGuards_,
                        "Transition Guards cannot be null on a Transition object" );
    }

    /**
     * Evaluates the transition for both the events and guards
     * 
     * @param aContext
     *            the event context
     * @return true if transition is valid for both events and guards
     */
    public boolean evaluateStateAgainstEventsAndGuards( IStateMachineEventContext aContext,
                                                        IState aState )
    {
        return ( isTransitionEventValid( aContext ) && areAllTransitionGuardsValid( aContext ) );
    }

    private boolean isTransitionEventValid( IStateMachineEventContext aContext )
    {
        if ( !areTransitionEventsSet() )
        {
            return true;
        }

        return isOneContextEventValidForTransition( aContext );
    }

    private boolean areTransitionEventsSet()
    {
        if ( getTransitionEvents() == null || getTransitionEvents().size() == 0 )
        {
            return false;
        }
        return true;
    }

    private boolean isOneContextEventValidForTransition( IStateMachineEventContext aContext )
    {
        for ( ITransitionEvent e : getTransitionEvents() )
        {
            if ( aContext.getEventName().equals( e.getTransitionEventName() ) )
            {
                LOG.debug( "Transition event [" + e.getTransitionEventName() + "] is found on ["
                           + getName() + "]" );
                return true;
            }
        }
        LOG.debug( "No valid transition events found on [" + getName() + "]" );
        return false;
    }

    private boolean areAllTransitionGuardsValid( IStateMachineEventContext aContext )
    {
        if ( !areTransitionGuardsSet() )
        {
            return true;
        }

        return ( areAllGuardsValidForTransition( aContext ) );
    }

    private boolean areTransitionGuardsSet()
    {
        if ( getTransitionGuards() == null || getTransitionGuards().size() == 0 )
        {
            return false;
        }
        return true;
    }

    private boolean areAllGuardsValidForTransition( IStateMachineEventContext aContext )
    {
        for ( ITransitionGuard g : getTransitionGuards() )
        {
            if ( !g.evaluateGuard( aContext ) )
            {
                return false;
            }
        }
        return true;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.connection.ITransition#getName()
     */
    @Override
    public String getName()
    {
        return mName_;
    }

    List<ITransitionEvent> getTransitionEvents()
    {
        return mTransitionEvents_;
    }

    /**
     * Setter for the transition events
     * 
     * @param aListOfTransitionEvents
     *            a list of transition events
     */
    public void setTransitionEvents( List<ITransitionEvent> aListOfTransitionEvents )
    {
        mTransitionEvents_ = aListOfTransitionEvents;
    }

    List<ITransitionGuard> getTransitionGuards()
    {
        return mTransitionGuards_;
    }

    /**
     * Setter for the transition guards
     * 
     * @param aListOfTransitionGuards
     *            the list of transition guards
     */
    public void setTransitionGuards( List<ITransitionGuard> aListOfTransitionGuards )
    {
        mTransitionGuards_ = aListOfTransitionGuards;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.connection.ITransition#getStartState()
     */
    @Override
    public IState getStartState()
    {
        return mStartState_;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.connection.ITransition#getEndState()
     */
    @Override
    public IState getEndState()
    {
        return mEndState_;
    }

}
