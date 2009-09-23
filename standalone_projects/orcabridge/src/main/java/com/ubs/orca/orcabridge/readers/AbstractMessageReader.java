/*
 * AbstractMessageReader.java created on 22 Sep 2009 20:07:31 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import com.ubs.orca.orcabridge.IMessageReader;
import com.ubs.orca.orcabridge.IMessageSender;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * TODO Write javadoc for AbstractMessageReader
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2009
 */
public abstract class AbstractMessageReader implements IMessageReader, InitializingBean
{

    private IMessageSender mMessageSender_;

    /**
     * Verifies that the message sender has been set on the class
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mMessageSender_, "Must set the Message Sender on the Message Reader" );
        doAfterPropertiesSet();
    }

    /**
     * This application is a spring enabled application. To enable us
     * to verify injected properties down the hierarchy we have an
     * abstract method to call down the stack.
     * 
     * @throws Exception
     */
    protected abstract void doAfterPropertiesSet() throws Exception;

    /**
     * Getter for the message sender. This is protected as we only
     * want it exposed to the children of this class.
     * 
     * @return the message sender class
     */
    protected IMessageSender getMessageSender()
    {
        return mMessageSender_;
    }

    /**
     * Setter for the message sender. This is mostly used for spring
     * injection.
     * 
     * @param aSender
     *            the message sender to set.
     */
    public void setMessageSender( IMessageSender aSender )
    {
        mMessageSender_ = aSender;
    }
}
