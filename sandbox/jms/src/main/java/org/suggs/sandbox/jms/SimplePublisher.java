/*
 * SimplePublisher.java created on 15 Apr 2009 07:02:37 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a very simple publisher class, that will send a text
 * message to a topic on a broker.
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2009
 */
public class SimplePublisher
{

    private static final Log LOG = LogFactory.getLog( SimplePublisher.class );

    public void sendMessageToTopic( String topicName ) throws NamingException,
                    NameNotFoundException, JMSException
    {
        InitialContext ctx = createInitialContext();
        TopicConnectionFactory tFact = (TopicConnectionFactory) ctx.lookup( ConfigManager.instance()
            .getProperty( ConfigManager.TOPIC_CONN_FACT ) );

        Topic t = (Topic) ctx.lookup( topicName );

        TopicConnection conn = tFact.createTopicConnection();

        try
        {
            TopicSession tSess = conn.createTopicSession( true, Session.CLIENT_ACKNOWLEDGE );
            try
            {
                TopicPublisher tPub = tSess.createPublisher( t );
                try
                {
                    TextMessage msg = tSess.createTextMessage( "This is a test message" );
                    msg.setStringProperty( "testHeader", "testValue" );
                    tPub.send( msg );
                    tSess.commit();
                }
                finally
                {
                    LOG.debug( "Closing publisher" );
                    tPub.close();
                }
            }
            finally
            {
                LOG.debug( "Closing session" );
                tSess.rollback();
                tSess.close();
            }
        }
        finally
        {
            LOG.debug( "Closing connection" );
            conn.close();
        }

        LOG.debug( "Sending message to topic [" + topicName + "]" );
    }

    /**
     * Helper to create an initial context based on the
     * 
     * @return the initial context
     * @throws NamingException
     *             if there is a problem creating the initial context
     */
    InitialContext createInitialContext() throws NamingException
    {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put( Context.INITIAL_CONTEXT_FACTORY, ConfigManager.instance()
            .getProperty( ConfigManager.CONTEXT_FACTORY ) );
        env.put( Context.PROVIDER_URL, ConfigManager.instance()
            .getProperty( ConfigManager.PROVIDER_URL ) );
        String user = ConfigManager.instance().getProperty( ConfigManager.USERNAME );
        if ( user != null && !user.equals( "" ) )
        {
            env.put( Context.SECURITY_PRINCIPAL, user );
            String pass = ConfigManager.instance().getProperty( ConfigManager.PASSWORD );
            if ( pass != null && !pass.equals( "" ) )
            {
                env.put( Context.SECURITY_CREDENTIALS, pass );
            }
        }
        return new InitialContext( env );
    }
}
