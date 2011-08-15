/*
 * SingletonImpl.java created on 24 Aug 2007 05:54:33 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an implementation of the Singleton pattern for demonstration only. Internally I have fleshed out
 * some of the issues to emphasise and also to discuss other approaches to the features of the pattern.
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2007
 */
public final class SingletonImpl {

    private static final Logger LOG = LoggerFactory.getLogger( SingletonImpl.class );

    /**
     * The single instance of the class.This is a static instance so that it can be instantiated by a static
     * method. The use of volatile here denotes that the instance should not be thread-local and thus this
     * should only be used in a 1.5 JVM or higher.
     */
    private volatile static SingletonImpl instance;
    private long constructionDateTime;

    /**
     * Constructs a new instance. The key thing to note here is that this constructor is private and therefore
     * only this class can instantiate an instance of itself.
     */
    private SingletonImpl() {
        constructionDateTime = System.currentTimeMillis();
    }

    /**
     * This is the key method in the pattern. Here we have the double locked approach to avoiding the
     * inefficient synchronised calls. Theoretically this impl of the pattern will only fall into synchronised
     * issues when we first call the instance method.
     * 
     * @return the singleton instance of the class
     */
    public static final SingletonImpl instance() {
        if ( instance == null ) {
            synchronized ( SingletonImpl.class ) {
                if ( instance == null ) {
                    LOG.info( "Creating a new instance of the singleton object" );
                    instance = new SingletonImpl();
                }
            }
        }
        LOG.debug( "Returning a copy of the singleton instance" );
        return instance;
    }

    /**
     * Getter for the construction time
     * 
     * @return the construction time in milles
     */
    public long getConstructionTime() {
        return constructionDateTime;
    }

}
