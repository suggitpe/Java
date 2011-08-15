/*
 * SimplePublisher.java created on 15 Apr 2009 07:02:37 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a very simple publisher class, that will send a text message to a topic on a broker.
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2009
 */
public class SimplePublisher {

    private static final Logger LOG = LoggerFactory.getLogger( SimplePublisher.class );

    /**
     * @param destName
     * @throws NamingException
     * @throws NameNotFoundException
     * @throws JMSException
     */
    public void sendMessageToTopic( String destName ) throws NamingException, NameNotFoundException,
                    JMSException {
        InitialContext ctx = ContextHelper.createInitialContext();
        ConnectionFactory fact = (ConnectionFactory) ctx.lookup( ConfigManager.instance()
            .getProperty( ConfigManager.TOPIC_CONN_FACT ) );

        Destination d = (Destination) ctx.lookup( destName );

        String user = ConfigManager.instance().getProperty( ConfigManager.USERNAME );
        String pass = ConfigManager.instance().getProperty( ConfigManager.PASSWORD );

        Connection conn = fact.createConnection( user, pass );

        try {
            Session sess = conn.createSession( true, Session.CLIENT_ACKNOWLEDGE );
            try {
                MessageProducer msgProd = sess.createProducer( d );
                try {
                    TextMessage msg = sess.createTextMessage( "This is a test message" );
                    msg.setStringProperty( "testHeader", "testValue" );
                    msgProd.send( msg );
                    sess.commit();
                }
                finally {
                    LOG.debug( "Closing publisher" );
                    msgProd.close();
                }
            }
            finally {
                LOG.debug( "Closing session" );
                sess.rollback();
                sess.close();
            }
        }
        finally {
            LOG.debug( "Closing connection" );
            conn.close();
        }

        LOG.debug( "Sending message to destination [" + destName + "]" );
    }

}
