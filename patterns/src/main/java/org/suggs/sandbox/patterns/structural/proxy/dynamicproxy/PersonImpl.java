/*
 * PersonImpl.java created on 14 Sep 2007 06:09:27 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.dynamicproxy;

/**
 * Concrete implementation of the person bean
 */
public class PersonImpl implements IPerson {

    private String name;
    private String gender;
    private String interests;
    private int rating;
    private int ratingCount = 0;

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public int getHotOrNotRating() {
        if (ratingCount == 0) {
            return 0;
        }
        return rating / ratingCount;
    }

    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setGender(String aGender) {
        gender = aGender;
    }

    @Override
    public void setHotOrNotRating(int hotOrNot) {
        rating += hotOrNot;
        ++ratingCount;
    }

    @Override
    public void setInterests(String aInterests) {
        interests = aInterests;
    }

    @Override
    public void setName(String aName) {
        name = aName;
    }
}
