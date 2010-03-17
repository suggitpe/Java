/*
 * SimpleSubscriber.java created on 16 Apr 2009 06:10:33 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple subscriber mechanism for creating a durable and then
 * retrieving messages from that durable.
 * 
 * @author suggitpe
 * @version 1.0 16 Apr 2009
 */
public class SimpleSubscriber
{

    private static final Log LOG = LogFactory.getLog( SimpleSubscriber.class );

    /**
     * @param aDestName
     * @throws NamingException
     * @throws JMSException
     */
    public void subscribeToTopic( String aDestName ) throws NamingException, JMSException
    {
        InitialContext ctx = ContextHelper.createInitialContext();

        ConnectionFactory fact = (ConnectionFactory) ctx.lookup( ConfigManager.instance()
            .getProperty( ConfigManager.TOPIC_CONN_FACT ) );
        Topic d = (Topic) ctx.lookup( aDestName );

        String user = ConfigManager.instance().getProperty( ConfigManager.USERNAME );
        String pass = ConfigManager.instance().getProperty( ConfigManager.PASSWORD );

        Connection conn = fact.createConnection( user, pass );

        try
        {
            Session sess = conn.createSession( true, Session.CLIENT_ACKNOWLEDGE );
            try
            {
                String durable = ConfigManager.instance()
                    .getProperty( ConfigManager.SUBSCR_DURABLE );
                TopicSubscriber tSubScr = sess.createDurableSubscriber( d,
                                                                        "PeteDurable",
                                                                        ConfigManager.instance()
                                                                            .getNullableProperty( ConfigManager.SUBSCR_MSG_SELECTOR ),
                                                                        true );

                try
                {
                    conn.start();
                    Message msg = null;
                    LOG.debug( "Pulling all of the messages from the [" + durable
                               + "] durable using message selector ["
                               + tSubScr.getMessageSelector() + "]" );
                    while ( ( msg = tSubScr.receiveNoWait() ) != null )
                    {
                        processMessages( msg );
                        sess.commit();
                    }
                    LOG.debug( "All messages consumed" );
                    conn.stop();
                }
                finally
                {
                    tSubScr.close();
                }
            }
            finally
            {
                sess.close();
            }
        }
        finally
        {
            conn.close();
        }
    }

    private void processMessages( Message msg ) throws JMSException
    {
        LOG.debug( "Processing message with ID [" + msg.getJMSMessageID() + "]" );
    }
}
