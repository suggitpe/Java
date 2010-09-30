/*
 * JmsMessageReceiver.java created on 29 Sep 2010 07:14:53 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.receiver.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.naming.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.reporting.receiver.MessagePersister;

/**
 * Messsage receiver
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2010
 */
public class JmsMessageReceiver {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( JmsMessageReceiver.class );

    private Context initialContext;
    private MessagePersister messagePersister;

    /**
     * @param aInitialContext
     *            initial context for the JMS connection.
     */
    public JmsMessageReceiver( Context aInitialContext ) {
        initialContext = aInitialContext;
    }

    public void processMessages() {
        Connection connection = createConnection();
    }

    private Connection createConnection() {
        LOG.debug( "Starting JMS Client" );
        ConnectionFactory connectionFactory = null;
        return null;
    }

}
