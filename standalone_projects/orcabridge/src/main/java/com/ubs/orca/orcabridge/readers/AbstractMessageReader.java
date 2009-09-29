/*
 * AbstractMessageReader.java created on 22 Sep 2009 20:07:31 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.IMessageReader;
import com.ubs.orca.orcabridge.OrcaBridgeException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Abstract class that will manage the message reader interaction with
 * the message processors.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2009
 */
public abstract class AbstractMessageReader implements IMessageReader, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( AbstractMessageReader.class );
    static final String STATE_UNINITIALISED = "Uninintialised";
    static final String STATE_STARTING = "Starting";
    static final String STATE_RUNNING = "Running";
    static final String STATE_STOPPING = "Stopping";
    static final String STATE_STOPPED = "Stopped";
    private IMessageProcessor mMessageProcessor_;
    private String mState_ = STATE_UNINITIALISED;

    /**
     * Constructs a new instance.
     */
    public AbstractMessageReader()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessageProcessor
     *            the message processor
     */
    public AbstractMessageReader( IMessageProcessor aMessageProcessor )
    {
        mMessageProcessor_ = aMessageProcessor;
    }

    /**
     * Verifies that the message processor has been set on the class
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mMessageProcessor_, "Must set the Message Processor on the Message Reader" );
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
     * @see com.ubs.orca.orcabridge.IMessageReader#startReader()
     */
    @Override
    public synchronized void startReader() throws OrcaBridgeException
    {
        LOG.debug( "Starting Orca Bridge." );
        mState_ = STATE_STARTING;
        doStartReader();
        mState_ = STATE_RUNNING;
    }

    /**
     * Template delegation of the actual reader start process to the
     * underlying specific middleware reader
     * 
     * @throws OrcaBridgeException
     */
    protected abstract void doStartReader() throws OrcaBridgeException;

    /**
     * @see com.ubs.orca.orcabridge.IMessageReader#stopReader()
     */
    @Override
    public synchronized void stopReader() throws OrcaBridgeException
    {
        mState_ = STATE_STOPPING;
        LOG.debug( "Stopping Orca Bridge." );
        doStopReader();
        mState_ = STATE_STOPPED;
    }

    /**
     * Template delegation of the actual reader stop process to the
     * underlying specific middleware reader.
     * 
     * @throws OrcaBridgeException
     */
    protected abstract void doStopReader() throws OrcaBridgeException;

    /**
     * Getter for the message processor. This is protected as we only
     * want it exposed to the children of this class.
     * 
     * @return the message processor instance
     */
    protected IMessageProcessor getMessageProcessor()
    {
        return mMessageProcessor_;
    }

    /**
     * Setter for the message processor. This is mostly used for
     * spring injection.
     * 
     * @param aProcessor
     *            the message processor to set.
     */
    public void setMessageProcessor( IMessageProcessor aProcessor )
    {
        mMessageProcessor_ = aProcessor;
    }

    /**
     * Getter for the state of the reader.
     * 
     * @return the state of the reader
     */
    public String getState()
    {
        return mState_;
    }
}
