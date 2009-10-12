/*
 * MessageFacadeFactoryTest.java created on 7 Oct 2009 19:20:48 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.message;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.common.bus.IOrcaMessage;
import com.ubs.orca.orcabridge.IMessageFacade;

/**
 * Test suite to test the message facade factory
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public class MessageFacadeFactoryTest
{

    private static final Log LOG = LogFactory.getLog( MessageFacadeFactoryTest.class );

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + MessageFacadeFactoryTest.class.getSimpleName() );
    }

    /**
     * Test setup
     * 
     * @throws Exception
     */
    @Before
    public void doBefore() throws Exception
    {
        LOG.debug( "-----------------" );
    }

    /**
     * Tests that the message facade factory creates the correct
     * message facade type with a jms message.
     */
    @Test
    public void testBuildJmsMessageFacade()
    {
        Message msg = EasyMock.createMock( Message.class );
        IMessageFacade facade = MessageFacadeFactory.createMessageAdapter( msg );
        Assert.assertThat( facade, new IsInstanceOf( JmsMessageFacade.class ) );
    }

    /**
     * Tests that the message facade factory creates the correct
     * facade type with an Orca message
     */
    @Test
    public void testBuildOrcaMessageFacade()
    {
        IOrcaMessage msg = EasyMock.createMock( IOrcaMessage.class );
        IMessageFacade facade = MessageFacadeFactory.createMessageAdapter( msg );
        Assert.assertThat( facade, new IsInstanceOf( OrcaMessageFacade.class ) );
    }
}
