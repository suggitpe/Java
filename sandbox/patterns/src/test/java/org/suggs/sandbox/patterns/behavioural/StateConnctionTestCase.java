/*
 * StateConnctionTestCase.java created on 6 Aug 2009 07:13:19 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural;

import org.suggs.sandbox.patterns.behavioural.state.connection.IStateMachine;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class to test the State Connection package
 * 
 * @author suggitpe
 * @version 1.0 6 Aug 2009
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-state-connection-test-case.xml" })
public class StateConnctionTestCase
{

    // static logger
    private static final Log LOG = LogFactory.getLog( StateConnctionTestCase.class );

    @Resource(name = "stateMachine")
    IStateMachine mStateMachine_;

    @Test
    public void testConnectionStateMachine()
    {
        LOG.debug( "Testing connection state machine" );
        mStateMachine_.step( null );
    }

}
