/*
 * JmsSenderActionTest.java created on 14 Oct 2009 07:05:15 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import static org.easymock.EasyMock.expect;

/**
 * Test suite for the JMS sender client.
 * 
 * @author suggitpe
 * @version 1.0 14 Oct 2009
 */
public class JmsSenderActionTest
{

    private static final Log LOG = LogFactory.getLog( JmsSenderActionTest.class );

    private IMocksControl ctrl_;
    private IJmsAction jmsSenderAction_;
    private IMessageFacade mockMessageFacade_;
    private Destination mockDestination_;
    private Session mockSession_;
    private MessageProducer msgProducer_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsSenderActionTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore()
    {
        LOG.debug( "-------------" );
        ctrl_ = EasyMock.createControl();
        mockMessageFacade_ = ctrl_.createMock( IMessageFacade.class );
        mockDestination_ = ctrl_.createMock( Destination.class );
        mockSession_ = ctrl_.createMock( Session.class );

        jmsSenderAction_ = new JmsSenderAction( mockMessageFacade_ );
    }

    @Test
    public void testAction() throws JmsClientException, JMSException
    {
        expect( mockSession_.createProducer( mockDestination_ ) ).andReturn( msgProducer_ );

        ctrl_.replay();
        jmsSenderAction_.action( mockSession_, mockDestination_ );
        ctrl_.verify();
    }
}
