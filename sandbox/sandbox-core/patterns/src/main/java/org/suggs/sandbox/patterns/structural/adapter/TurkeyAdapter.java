/*
 * TurkeyAdapter.java created on 31 Aug 2007 17:20:40 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.adapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Adapter class that implements the Duck interface for an ITurkey object
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class TurkeyAdapter implements IDuck {

    private static final Log LOG = LogFactory.getLog( TurkeyAdapter.class );
    private ITurkey turkey;

    /**
     * Constructs a new instance.
     * 
     * @param aTurkey
     */
    public TurkeyAdapter( ITurkey aTurkey ) {
        turkey = aTurkey;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.adapter.IDuck#fly()
     */
    @Override
    public void fly() {
        LOG.debug( "Adapting the turkey fly" );
        for ( int i = 0; i < 5; ++i ) {
            turkey.fly();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.adapter.IDuck#quack()
     */
    @Override
    public void quack() {
        LOG.debug( "Adapting the turkey gobble in quack" );
        turkey.gobble();
    }

}
