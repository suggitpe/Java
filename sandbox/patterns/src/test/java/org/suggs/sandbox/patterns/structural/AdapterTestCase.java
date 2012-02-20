/*
 * AdapterTestCase.java created on 31 Aug 2007 06:38:14 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.structural.adapter.IDuck;
import org.suggs.sandbox.patterns.structural.adapter.MallardDuck;
import org.suggs.sandbox.patterns.structural.adapter.TurkeyAdapter;
import org.suggs.sandbox.patterns.structural.adapter.WildTurkey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

/**
 * Test case for the adapter pattern
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class AdapterTestCase extends AbstractPatternTestCase {

    private static final Logger LOG = LoggerFactory.getLogger( AdapterTestCase.class );

    @Test
    public void testTurkeyAdapter() {
        IDuck mallard = new MallardDuck();
        WildTurkey turkey = new WildTurkey();

        IDuck turkeyAdapter = new TurkeyAdapter( turkey );

        LOG.debug( "The Turkey says ..." );
        turkey.gobble();
        turkey.fly();

        LOG.debug( "The duck says ..." );
        testDuck( mallard );

        LOG.debug( "The Turkey Adapter says ..." );
        testDuck( turkeyAdapter );

    }

    private void testDuck( IDuck aDuck ) {
        LOG.debug( "Testing a duck object [" + aDuck.getClass().getSimpleName() + "]" );
        aDuck.quack();
        aDuck.fly();
    }
}
