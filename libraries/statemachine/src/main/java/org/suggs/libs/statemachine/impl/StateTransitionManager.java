/*
 * StateTransitionManager.java created on 24 Aug 2009 06:59:46 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateTransition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This class is used to store all of the state transitions in a
 * single accessible place. Realistically it is a fancy collections
 * class that is accessed statically for all parties. <br/>
 * <br/>
 * <b>TODO: This needs to be made thread safe and also to ensure that
 * it can service more than one state machine at any one time.</b>
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
public class StateTransitionManager implements InitializingBean
{

    private static final Log LOG = LogFactory.getLog( StateTransitionManager.class );
    private static final StateTransitionManager mInstance_ = new StateTransitionManager();
    private final Map<String, Map<String, IStateTransition>> mTransitionMap_ = new HashMap<String, Map<String, IStateTransition>>();

    /**
     * Constructs a new instance.
     */
    private StateTransitionManager()
    {
        super();
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.isTrue( mTransitionMap_.size() > 0,
                       "In creation of the state transition manager, it is expected that more than 0 state transitions should be loaded" );
    }

    /**
     * Accessor method to the underlying singleton instance.
     * 
     * @return the singleton instance of the State Transition Manager
     */
    public static final StateTransitionManager instance()
    {
        return mInstance_;
    }

    /**
     * Getter for a list of transitions relating to one starting
     * state.
     * 
     * @param aState
     *            The state that the transitions work from
     * @return a list of state specific transitions, if there are no
     *         states that relate then an empty list will be returned.
     */
    public List<IStateTransition> getListOfTransitionsForState( IState aState )
    {
        List<IStateTransition> listOfTransitions = new ArrayList<IStateTransition>();

        Map<String, IStateTransition> mapOfTransitions = getMapOfTransitionsForState( aState );
        for ( String transitionName : mapOfTransitions.keySet() )
        {
            listOfTransitions.add( mapOfTransitions.get( transitionName ) );
        }

        return listOfTransitions;
    }

    private Map<String, IStateTransition> getMapOfTransitionsForState( IState aState )
    {
        if ( aState == null )
        {
            throw new IllegalArgumentException( "Cannot use null for State Transition lookup" );
        }

        LOG.debug( "Searching for all transitions for state=[" + aState + "]" );
        if ( mTransitionMap_.containsKey( aState.getStateName() ) )
        {
            return mTransitionMap_.get( aState.getStateName() );
        }
        return new HashMap<String, IStateTransition>();
    }

    /**
     * Accessor to the transitions held within the manager
     * 
     * @return a list of transitions, if no transitions held in the
     *         manager then this will return an empty (typed) list.
     */
    public List<IStateTransition> getAllTransitions()
    {
        List<IStateTransition> listOfTransitions = new ArrayList<IStateTransition>();
        for ( String stateName : mTransitionMap_.keySet() )
        {
            Map<String, IStateTransition> innerMapOfTransitions = mTransitionMap_.get( stateName );
            for ( String transitionName : innerMapOfTransitions.keySet() )
            {
                listOfTransitions.add( innerMapOfTransitions.get( transitionName ) );
            }
        }
        return listOfTransitions;
    }

    /**
     * Allows a single transition to be added to the manager.
     * 
     * @param aStateTransition
     *            the transition to add.
     */
    public void addTransitionToManager( IStateTransition aStateTransition )
    {
        if ( aStateTransition == null )
        {
            throw new IllegalArgumentException( "Cannot add a null transition to the transition manager" );
        }

        String startStateName = aStateTransition.getStartingState().getStateName();
        buildInnerTransitionMapIfNeeded( startStateName );

        Map<String, IStateTransition> innerMap = mTransitionMap_.get( startStateName );
        if ( innerMap.containsKey( aStateTransition.getTransitionName() ) )
        {
            throw new IllegalStateException( "Cannot add more than one State Transition with the same name for the same state: State=["
                                             + aStateTransition.getStartingState()
                                             + "], Transition=[" + aStateTransition + "], " );
        }

        innerMap.put( aStateTransition.getTransitionName(), aStateTransition );

        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Successfully loaded transition: state=["
                       + aStateTransition.getStartingState() + "], transition=[" + aStateTransition
                       + "]" );
        }
    }

    private void buildInnerTransitionMapIfNeeded( String aStateName )
    {
        if ( !mTransitionMap_.keySet().contains( aStateName ) )
        {
            mTransitionMap_.put( aStateName, new HashMap<String, IStateTransition>() );
        }
    }

    /**
     * Setter for the transition list. This method is important if you
     * are injecting the transitions through spring.
     * 
     * @param aListOfTransitions
     *            the list of transitions
     */
    public void setTransitions( List<IStateTransition> aListOfTransitions )
    {
        if ( aListOfTransitions == null )
        {
            throw new IllegalArgumentException( "Cannot add a null list of transitions to the Transition Manager" );
        }

        for ( IStateTransition transition : aListOfTransitions )
        {
            this.addTransitionToManager( transition );
        }
    }

    /**
     * Used to clear the transition manager of all of its transitions.
     * It is envisaged that this will really only be used for its unit
     * tests.
     */
    public void clearTransitionsFromTransitionManager()
    {
        LOG.info( "Clearing all transitions from the transition manager" );
        mTransitionMap_.clear();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder ret = new StringBuilder( "TransitionManager: " );
        ret.append( "{" ).append( Integer.toHexString( super.hashCode() ) ).append( "} " );
        ret.append( "numStates=[" ).append( mTransitionMap_.size() ).append( "]" );
        for ( String startState : mTransitionMap_.keySet() )
        {
            ret.append( ": state[" ).append( startState ).append( "]" );
            for ( String transitionName : mTransitionMap_.get( startState ).keySet() )
            {
                ret.append( ", transition[" ).append( transitionName ).append( "]" );
            }
        }

        return ret.toString();
    }

}
