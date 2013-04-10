/*
 * FacadeTestCase.java created on 31 Aug 2007 06:38:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.suggs.sandbox.patterns.AbstractPatternTest;
import org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson;
import org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.PersonImpl;
import org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.PersonProxyHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

/**
 * Test case for the facade pattern
 */
public class ProxyTest extends AbstractPatternTest {

    private static final Logger LOG = LoggerFactory.getLogger( ProxyTest.class );

    @Test
    public void testPersonOwnerProxy() {
        LOG.debug( "Testing a dynamic proxy implementation for the owner proxy" );

        IPerson joe = getPersonFromPersistenceLayer( "Joe Javabean" );
        IPerson joeProxy = PersonProxyHelper.getOwnerProxy( joe );
        LOG.debug( "Owner proxy getName is [" + joeProxy.getName() + "]" );

        joeProxy.setInterests( "bowling, Go" );
        try {
            joeProxy.setHotOrNotRating( 6 );
        }
        catch ( Exception e ) {
            LOG.warn( "Setting the hot or not under the owner proxy gives rise to a ["
                      + e.getClass().getSimpleName() + "] exception" );

        }
        LOG.debug( "The hot or not rating for [" + joeProxy.getName() + "] is ["
                   + joeProxy.getHotOrNotRating() + "]" );
    }

    @Test
    public void testPersonNonOwnerProxy() {
        LOG.debug( "Testing a dynamic proxy implementation for the non owner proxy" );

        IPerson bob = getPersonFromPersistenceLayer( "Billy bob Javabean" );
        IPerson bobProxy = PersonProxyHelper.getNonOwnerProxy( bob );
        LOG.debug( "Non Owner proxy getName is [" + bobProxy.getName() + "]" );

        try {
            bobProxy.setInterests( "Playing with his pooh!" );
        }
        catch ( Exception e ) {
            LOG.warn( "Setting the interests under the non owner proxy gives rise to a ["
                      + e.getClass().getSimpleName() + "] exception" );
        }

        bobProxy.setHotOrNotRating( 9 );
        LOG.debug( "The hot or not rating for [" + bobProxy.getName() + "] is ["
                   + bobProxy.getHotOrNotRating() + "]" );
    }

    private IPerson getPersonFromPersistenceLayer( String aName ) {
        PersonImpl ret = new PersonImpl();

        ret.setName( aName );
        ret.setGender( "Male" );
        ret.setHotOrNotRating( 5 );
        ret.setInterests( "Not much, y'know just kinda kicking stones and stuff!" );

        return ret;
    }
}
