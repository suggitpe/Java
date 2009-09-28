/*
 * OrcaClientTestStub.java created on 28 Sep 2009 07:01:51 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.teststubs;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.client.agent.replay.IReplayObserver;
import com.ubs.orca.client.api.IConversation;
import com.ubs.orca.client.api.IConversationMessage;
import com.ubs.orca.client.api.IDestination;
import com.ubs.orca.client.api.IDestinationListener;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.IStartParameters;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.client.operations.IOperationsManager;
import com.ubs.orca.common.bus.MessageType;
import com.ubs.orca.common.primitive.IAttribute;
import com.ubs.orca.common.xml.Element;
import com.ubs.orca.destinationfinder.IDestinationFinder;
import com.ubs.orca.destinationfinder.RoutingException;

/**
 * Test stub class for the Orca Client
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2009
 */
public class OrcaClientTestStub implements IOrcaClient
{

    private static final Log LOG = LogFactory.getLog( OrcaClientTestStub.class );

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#connect()
     */
    @Override
    public void connect() throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing connect" );
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#disconnect()
     */
    @Override
    public void disconnect() throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing disconnect" );
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#doNotSendMessagesIfAlreadyPublished(boolean)
     */
    @Override
    public void doNotSendMessagesIfAlreadyPublished( boolean aParamBoolean ) throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing doNotSendMessagesIfAlreadyPublished" );
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#getApplicationConfiguration()
     */
    @Override
    public Element getApplicationConfiguration() throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing getApplicationConfiguration" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#getDestinationFinder()
     */
    @Override
    public IDestinationFinder getDestinationFinder() throws RoutingException
    {
        LOG.debug( "***** Stub Orca Client - Performing getDestinationFinder" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#getOperationsManager()
     */
    @Override
    public IOperationsManager getOperationsManager()
    {
        LOG.debug( "***** Stub Orca Client - Performing getOperationsManager" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#getToken()
     */
    @Override
    public String getToken()
    {
        LOG.debug( "***** Stub Orca Client - Performing getToken" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#send(com.ubs.orca.client.api.IConversationMessage)
     */
    @Override
    public String send( IConversationMessage aParamIConversationMessage ) throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing send" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#setDestinationListener(com.ubs.orca.client.api.IDestinationListener)
     */
    @Override
    public IDestination[] setDestinationListener( IDestinationListener aParamIDestinationListener )
                    throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing setDestinationListener" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#setInstanceLoad(int)
     */
    @Override
    public void setInstanceLoad( int aParamInt ) throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing setInstanceLoad" );
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#start()
     */
    @Override
    public void start() throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing start" );
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#start(com.ubs.orca.client.api.IStartParameters)
     */
    @Override
    public void start( IStartParameters aParamIStartParameters ) throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing start" );
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaClient#stop()
     */
    @Override
    public void stop() throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing stop" );
    }

    /**
     * @see com.ubs.orca.client.api.IConversationMessageFactory#createAttributesMessage(com.ubs.orca.client.api.IConversation,
     *      com.ubs.orca.common.bus.MessageType, java.lang.String,
     *      com.ubs.orca.common.primitive.IAttribute[])
     */
    @Override
    public IConversationMessage createAttributesMessage( IConversation aParamIConversation,
                                                         MessageType aParamMessageType,
                                                         String aParamString,
                                                         IAttribute[] aParamArrayOfIAttribute )
    {
        LOG.debug( "***** Stub Orca Client - Performing createAttributesMessage" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IConversationMessageFactory#createAttributesMessage(com.ubs.orca.client.api.IConversation,
     *      com.ubs.orca.common.bus.MessageType, java.lang.String,
     *      com.ubs.orca.common.primitive.IAttribute[],
     *      com.ubs.orca.common.primitive.IAttribute[])
     */
    @Override
    public IConversationMessage createAttributesMessage( IConversation aParamIConversation,
                                                         MessageType aParamMessageType,
                                                         String aParamString,
                                                         IAttribute[] aParamArrayOfIAttribute1,
                                                         IAttribute[] aParamArrayOfIAttribute2 )
    {
        LOG.debug( "***** Stub Orca Client - Performing createAttributesMesssage" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IConversationMessageFactory#createTextMessage(com.ubs.orca.client.api.IConversation,
     *      com.ubs.orca.common.bus.MessageType, java.lang.String,
     *      java.lang.String)
     */
    @Override
    public IConversationMessage createTextMessage( IConversation aParamIConversation,
                                                   MessageType aParamMessageType,
                                                   String aParamString1, String aParamString2 )
    {
        LOG.debug( "***** Stub Orca Client - Performing createTextMessage" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IConversationMessageFactory#createTextMessage(com.ubs.orca.client.api.IConversation,
     *      com.ubs.orca.common.bus.MessageType, java.lang.String,
     *      java.lang.String,
     *      com.ubs.orca.common.primitive.IAttribute[])
     */
    @Override
    public IConversationMessage createTextMessage( IConversation aParamIConversation,
                                                   MessageType aParamMessageType,
                                                   String aParamString1, String aParamString2,
                                                   IAttribute[] aParamArrayOfIAttribute )
    {
        LOG.debug( "***** Stub Orca Client - Performing createTextMessage" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IConversationRepository#createConversation(java.lang.String)
     */
    @Override
    public IConversation createConversation( String aParamString ) throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing createConversation" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IConversationRepository#createConversation(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public IConversation createConversation( String aParamString1, String aParamString2 )
                    throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing createConversation" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IConversationRepository#createConversation(java.lang.String,
     *      com.ubs.orca.common.primitive.IAttribute[])
     */
    @Override
    public IConversation createConversation( String aParamString,
                                             IAttribute[] aParamArrayOfIAttribute )
                    throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing createConversation" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IConversationRepository#createConversation(java.lang.String,
     *      java.lang.String,
     *      com.ubs.orca.common.primitive.IAttribute[])
     */
    @Override
    public IConversation createConversation( String aParamString1, String aParamString2,
                                             IAttribute[] aParamArrayOfIAttribute )
                    throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing createConversation" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IConversationRepository#getConversation(java.lang.String)
     */
    @Override
    public IConversation getConversation( String aParamString ) throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing getConversation" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IPublishedIdManager#getAllLastPublishedIds()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map getAllLastPublishedIds() throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing getAllLastPublishedIds" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IPublishedIdManager#getLastPublishedId(java.lang.String)
     */
    @Override
    public String getLastPublishedId( String aParamString ) throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing getLastPublishedId" );
        return null;
    }

    /**
     * @see com.ubs.orca.client.api.IPublishedIdManager#resetLastPublishedId(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void resetLastPublishedId( String aParamString1, String aParamString2 )
                    throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing resetLastPublishedId" );
    }

    /**
     * @see com.ubs.orca.common.bus.IReceivedIdManager#getLastReceivedId()
     */
    @Override
    public long getLastReceivedId() throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing getLastReceivedId" );
        return 0;
    }

    /**
     * @see com.ubs.orca.common.bus.IReceivedIdManager#resetLastReceivedId(long)
     */
    @Override
    public void resetLastReceivedId( long aParamLong ) throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing resetLastReceivedId" );
    }

    /**
     * @see com.ubs.orca.common.bus.IReceivedIdManager#resetReceivedId(long,
     *      long)
     */
    @Override
    public void resetReceivedId( long aParamLong1, long aParamLong2 ) throws OrcaException
    {
        LOG.debug( "***** Stub Orca Client - Performing resetReceivedId" );
    }

    /**
     * @see com.ubs.orca.common.bus.IReceivedIdManager#setReplayObserver(com.ubs.orca.client.agent.replay.IReplayObserver)
     */
    @Override
    public void setReplayObserver( IReplayObserver aParamIReplayObserver )
    {
        LOG.debug( "***** Stub Orca Client - Performing setReplayObserver" );
    }

    /**
     * @see com.ubs.orca.common.mom.IForceAck#forceAck()
     */
    @Override
    public void forceAck()
    {
        LOG.debug( "***** Stub Orca Client - Performing forceAck" );
    }
}
