/*
 * Initialisee.java created on 21 Sep 2007 06:30:30 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.specific;

/**
 * A class that can be initialised in different ways
 * 
 * @author suggitpe
 * @version 1.0 21 Sep 2007
 */
public class Initialisee {

    @SuppressWarnings("unused")
    private static Initialisee instance = new Initialisee( "Static member" );
    @SuppressWarnings("unused")
    private String member;

    /**
     * Singleton approach to construction
     * 
     * @return an initialisee
     */
    public static Initialisee instance() {
        return new Initialisee( "Static method" );
    }

    /**
     * Constructs a new instance.
     */
    public Initialisee() {}

    /**
     * Constructs a new instance.
     * 
     * @param aType
     *            type of construction
     */
    private Initialisee( String aType ) {
        Logger.log( aType + " Constructor" );
    }

    static {
        Logger.log( "Static initialiser" );
    }

    {
        Logger.log( "Non-static initialiser" );
    }

    static {
        Logger.log( "Static initialiser 2" );
    }

    {
        Logger.log( "Non-static initialiser 2" );
    }

}
