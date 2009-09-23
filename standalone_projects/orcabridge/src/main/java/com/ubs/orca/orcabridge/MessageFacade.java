/*
 * MessageFacade.java created on 15 Sep 2009 07:17:41 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.common.bus.IOrcaMessage;

/**
 * When routing between the differing Orca instances we have two core
 * message types:<br/>
 * <ul>
 * <li>Orca Messages - native only to the Orca v2 implementations</li>
 * <li>JMS Messages - common across all of the major middleware
 * implementations and predominant through the later Orca
 * implementations</li>
 * </ul>
 * The core aim of this class is to provide a common facade/wrapper to
 * the differing message types. It additionally uses various builder
 * classes to create messages that are suitable to the same or indeed
 * different message types.<br/>
 * Using this class enables the logic surrounding the management of
 * the different message types to be fully encapsulated.
 * 
 * @author suggitpe
 * @version 1.0 15 Sep 2009
 */
public class MessageFacade
{

    private static final Log LOG = LogFactory.getLog( MessageFacade.class );

    private Message mJmsMessage_;
    private IOrcaMessage mOrcaMessage_;

    /**
     * Constructs a new instance using a JMS Message.
     * 
     * @param aJmsMessage
     *            a JMS message to initialise the facade with.
     */
    public MessageFacade( Message aJmsMessage )
    {
        super();
        mJmsMessage_ = aJmsMessage;
    }

    /**
     * Constructs a new instance using an ORCA message.
     * 
     * @param aOrcaMessage
     *            an Orca message to initialise the facade.
     */
    public MessageFacade( IOrcaMessage aOrcaMessage )
    {
        super();
        mOrcaMessage_ = aOrcaMessage;
    }
}
