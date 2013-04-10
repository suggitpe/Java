/*
 * TurkeyAdapter.java created on 31 Aug 2007 17:20:40 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adapter class that implements the Duck interface for an ITurkey object
 */
public class TurkeyAdapter implements IDuck {

    private static final Logger LOG = LoggerFactory.getLogger( TurkeyAdapter.class );
    private ITurkey turkey;

    public TurkeyAdapter( ITurkey aTurkey ) {
        turkey = aTurkey;
    }

    @Override
    public void fly() {
        LOG.debug( "Adapting the turkey fly" );
        for ( int i = 0; i < 5; ++i ) {
            turkey.fly();
        }
    }

    @Override
    public void quack() {
        LOG.debug( "Adapting the turkey gobble in quack" );
        turkey.gobble();
    }

}
