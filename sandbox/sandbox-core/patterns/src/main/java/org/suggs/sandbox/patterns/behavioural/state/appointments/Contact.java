/*
 * IContact.java created on 3 Aug 2009 19:42:50 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.appointments;

import java.io.Serializable;

/**
 * Domain model bean for a contact
 * 
 * @author suggitpe
 * @version 1.0 3 Aug 2009
 */
public class Contact implements Serializable
{

    private String mFirstName_;
    private String mLastName_;
    private String mTitle_;
    private String mOrganisation_;

    /**
     * Constructs a new instance.
     */
    public Contact()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aFirstName
     *            contact first name
     * @param aLastName
     *            contact last name
     * @param aTitle
     *            contact title
     * @param aOrganisation
     *            contact organisation
     */
    public Contact( String aFirstName, String aLastName, String aTitle, String aOrganisation )
    {
        mFirstName_ = aFirstName;
        mLastName_ = aLastName;
        mTitle_ = aTitle;
        mOrganisation_ = aOrganisation;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer ret = new StringBuffer( "Contact: " );
        ret.append( "firstName=[" ).append( mFirstName_ ).append( "], " );
        ret.append( "lastName=[" ).append( mLastName_ ).append( "], " );
        ret.append( "title=[" ).append( mTitle_ ).append( "], " );
        ret.append( "organisation=[" ).append( mOrganisation_ ).append( "]" );
        return ret.toString();
    }

    /**
     * Getter
     * 
     * @return first name
     */
    public String getFirstName()
    {
        return mFirstName_;
    }

    /**
     * Getter
     * 
     * @return last name
     */
    public String getLastName()
    {
        return mLastName_;
    }

    /**
     * Getter
     * 
     * @return title
     */
    public String getTitle()
    {
        return mTitle_;
    }

    /**
     * Getter
     * 
     * @return organisation
     */
    public String getOrganisation()
    {
        return mOrganisation_;
    }

    /**
     * Setter
     * 
     * @param aName
     *            first name
     */
    public void setFirstName( String aName )
    {
        mFirstName_ = aName;
    }

    /**
     * Setter
     * 
     * @param aName
     *            last name
     */
    public void setLastName( String aName )
    {
        mLastName_ = aName;
    }

    /**
     * Setter
     * 
     * @param aTitle
     *            title
     */
    public void setTitle( String aTitle )
    {
        mTitle_ = aTitle;
    }

    /**
     * Setter
     * 
     * @param aOrg
     *            organisation
     */
    public void setOrganisation( String aOrg )
    {
        mOrganisation_ = aOrg;
    }

}
