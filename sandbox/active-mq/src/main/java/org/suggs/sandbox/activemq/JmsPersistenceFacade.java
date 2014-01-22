package org.suggs.sandbox.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;
import java.lang.IllegalStateException;

public class JmsPersistenceFacade {

    private static final Logger LOG = LoggerFactory.getLogger(JmsPersistenceFacade.class);
    private final Context initialContext;
    private final Destination destination;
    private final ConnectionFactory connectionFactory;

    public JmsPersistenceFacade(Context anInitialContext, String aDestinationName) throws NamingException {
        initialContext = anInitialContext;
        connectionFactory = (QueueConnectionFactory) initialContext.lookup("ConnectionFactory");
        destination = (Destination) initialContext.lookup(aDestinationName);
        LOG.debug("Created JMS Utility against [{}]", destination);
    }

    public void writeMessage(final String aMessage) {
        LOG.debug("Writing message [{}] to [{}]", aMessage, destination);
        runCallBackInSession(session -> {
            MessageProducer producer = null;
            try {
                producer = session.createProducer(destination);
                producer.send(session.createTextMessage(aMessage));
                session.commit();
                producer.close();
            } catch (JMSException jmse) {
                throw new IllegalStateException("Failed to send message to JMS broker", jmse);
            }
        });
    }

    public String readMessage() {
        LOG.debug("Reading message from [{}]", destination);
        StringBuffer buffer = new StringBuffer();
        runCallBackInSession(session -> {
            try {
                MessageConsumer consumer = session.createConsumer(destination);
                String message = extractTextFromMessage(consumer.receiveNoWait());
                buffer.append(message);
                consumer.close();
            } catch (JMSException e) {
                throw new IllegalStateException("Failed to consume message from JMS broker");
            }
        });
        return buffer.toString();
    }

    private String extractTextFromMessage(Message aMessage) throws JMSException {
        if (aMessage != null && aMessage instanceof TextMessage) {
            TextMessage txtMsg = (TextMessage) aMessage;
            return txtMsg.getText();
        }
        throw new IllegalStateException("No valid message received");
    }

    private void runCallBackInSession(JmsCallback aCallback) {
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            aCallback.runInSession(session);
            session.close();
            connection.close();
        } catch (JMSException jmse) {
            throw new IllegalStateException("Issues when trying to execute against JMS broker", jmse);
        }
    }

    @FunctionalInterface
    private interface JmsCallback {
        void runInSession(Session aSession);
    }
}
