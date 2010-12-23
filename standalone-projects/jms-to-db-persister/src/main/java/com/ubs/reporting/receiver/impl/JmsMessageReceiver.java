/*
 * JmsMessageReceiver.java created on 29 Sep 2010 07:14:53 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.receiver.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Messsage receiver
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2010
 */
public class JmsMessageReceiver {

    private static final Logger LOG = LoggerFactory.getLogger( JmsMessageReceiver.class );

    private Context initialContext;

    /**
     * @param aInitialContext
     *            initial context for the JMS connection.
     */
    public JmsMessageReceiver( Context aInitialContext ) {
        initialContext = aInitialContext;
    }

    public void processMessages() throws JMSException, NamingException {
        Connection connection = createConnection();
        connection.start();
    }

    private Connection createConnection() throws JMSException, NamingException {
        LOG.debug( "Starting JMS Client" );
        ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup( "" );
        Connection connection = connectionFactory.createConnection();
        return connection;
    }

}
