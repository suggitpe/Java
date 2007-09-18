/*
 * PersonImpl.java created on 14 Sep 2007 06:09:27 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.dynamicproxy;

/**
 * Concrete implementation of the person bean
 * 
 * @author suggitpe
 * @version 1.0 14 Sep 2007
 */
public class PersonImpl implements IPerson
{

    private String mName_;
    private String mGender_;
    private String mInterests_;
    private int mRating_;
    private int mRatingCount_ = 0;

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson#getGender()
     */
    public String getGender()
    {
        return mGender_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson#getHotOrNotRating()
     */
    public int getHotOrNotRating()
    {
        if ( mRatingCount_ == 0 )
        {
            return 0;
        }
        return mRating_ / mRatingCount_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson#getInterests()
     */
    public String getInterests()
    {
        return mInterests_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson#getName()
     */
    public String getName()
    {
        return mName_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson#setGender(java.lang.String)
     */
    public void setGender( String gender )
    {
        mGender_ = gender;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson#setHotOrNotRating(int)
     */
    public void setHotOrNotRating( int hotOrNot )
    {
        mRating_ += hotOrNot;
        ++mRatingCount_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson#setInterests(java.lang.String)
     */
    public void setInterests( String interests )
    {
        mInterests_ = interests;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson#setName(java.lang.String)
     */
    public void setName( String name )
    {
        mName_ = name;
    }
}
