/*
 * IPerson.java created on 14 Sep 2007 06:04:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.dynamicproxy;

/**
 * Interface to show the behaviour of a person.
 */
public interface IPerson {

    String getName();

    String getGender();

    String getInterests();

    int getHotOrNotRating();

    void setName(String aName);

    void setGender(String aGender);

    void setInterests(String aInterests);

    void setHotOrNotRating(int aHotOrNot);
}
