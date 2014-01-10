package org.suggs.sandbox.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;

public class JmsUtility {

    private static final Logger LOG = LoggerFactory.getLogger(JmsUtility.class);
    private final Context initialContext;
    private final Destination destination;
    private final ConnectionFactory connectionFactory;

    public JmsUtility(Context anInitialContext, String aDestinationName) throws NamingException {
        initialContext = anInitialContext;
        connectionFactory = (QueueConnectionFactory) initialContext.lookup("ConnectionFactory");
        destination = (Destination) initialContext.lookup(aDestinationName);
        LOG.debug("Created JMS Utility against [" + destination + "]");
    }

    public void writeMessage(String aMessage) throws Exception {
        LOG.debug("Writing message [" + aMessage + "] to [" + destination.toString() + "]");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(aMessage);
        producer.send(message);
        session.commit();

        producer.close();
        session.close();
        connection.close();
    }

    public String readMessage() throws Exception {
        LOG.debug("Reading message from [" + destination + "]");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(destination);
        String messageContent = extractTextFromMessage(consumer.receiveNoWait());
        session.commit();

        consumer.close();
        session.close();
        connection.stop();
        connection.close();
        return messageContent;
    }

    private String extractTextFromMessage(Message aMessage) throws JMSException {
        if (aMessage != null && aMessage instanceof TextMessage) {
            TextMessage txtMsg = (TextMessage) aMessage;
            return txtMsg.getText();
        }
        LOG.warn("No message received");
        return "";
    }
}
