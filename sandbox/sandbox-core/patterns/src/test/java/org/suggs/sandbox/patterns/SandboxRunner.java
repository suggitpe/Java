/*
 * SandboxRunner.java created on 20 Aug 2009 19:38:05 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.Description;
import org.junit.runner.Request;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

/**
 * TODO Write javadoc for SandboxRunner
 * 
 * @author suggitpe
 * @version 1.0 20 Aug 2009
 */
public class SandboxRunner extends Runner {

    private static final Log LOG = LogFactory.getLog( SandboxRunner.class );
    private final Runner delegate;

    /**
     * Constructs a new instance.
     * 
     * @param testClass
     * @throws Exception
     */
    public SandboxRunner( Class<?> testClass ) throws Exception {
        Request classRequest = Request.aClass( testClass );
        delegate = classRequest.getRunner();
        LOG.debug( "New runner created for " + testClass );
    }

    /**
     * @see org.junit.runner.Runner#getDescription()
     */
    @Override
    public Description getDescription() {
        return delegate.getDescription();
    }

    /**
     * @see org.junit.runner.Runner#run(org.junit.runner.notification.RunNotifier)
     */
    @Override
    public void run( RunNotifier aParamRunNotifier ) {
        LOG.debug( "-------------------- " + this.toString() );
        delegate.run( aParamRunNotifier );
        LOG.debug( "--------------------" );

    }
}
