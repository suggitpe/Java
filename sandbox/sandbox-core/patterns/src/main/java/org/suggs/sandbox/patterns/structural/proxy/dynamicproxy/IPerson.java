/*
 * IPerson.java created on 14 Sep 2007 06:04:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.dynamicproxy;

/**
 * Interface to show the behaviour of a person.
 * 
 * @author suggitpe
 * @version 1.0 14 Sep 2007
 */
public interface IPerson {

    /**
     * Getter for the name
     * 
     * @return the name
     */
    String getName();

    /**
     * Getter for the gender
     * 
     * @return the gender
     */
    String getGender();

    /**
     * Getter for the interests
     * 
     * @return the interests
     */
    String getInterests();

    /**
     * Getter for the hot or not rating
     * 
     * @return the hot or not rating
     */
    int getHotOrNotRating();

    /**
     * Setter for the name
     * 
     * @param aName
     *            the name to set
     */
    void setName( String aName );

    /**
     * Setter for the gender
     * 
     * @param aGender
     *            the gender to set
     */
    void setGender( String aGender );

    /**
     * Setter for the interests
     * 
     * @param aInterests
     *            the interests to set
     */
    void setInterests( String aInterests );

    /**
     * Setter for the hot or not rating
     * 
     * @param aHotOrNot
     *            the hot or not rating
     */
    void setHotOrNotRating( int aHotOrNot );
}
