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
public class Contact implements Serializable {

    private static final long serialVersionUID = 6156746893874120268L;
    private String firstName;
    private String lastName;
    private String title;
    private String organisation;

    /**
     * Constructs a new instance.
     */
    public Contact() {
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
    public Contact( String aFirstName, String aLastName, String aTitle, String aOrganisation ) {
        firstName = aFirstName;
        lastName = aLastName;
        title = aTitle;
        organisation = aOrganisation;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer( "Contact: " );
        ret.append( "firstName=[" ).append( firstName ).append( "], " );
        ret.append( "lastName=[" ).append( lastName ).append( "], " );
        ret.append( "title=[" ).append( title ).append( "], " );
        ret.append( "organisation=[" ).append( organisation ).append( "]" );
        return ret.toString();
    }

    /**
     * Getter
     * 
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter
     * 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter
     * 
     * @return organisation
     */
    public String getOrganisation() {
        return organisation;
    }

    /**
     * Setter
     * 
     * @param aName
     *            first name
     */
    public void setFirstName( String aName ) {
        firstName = aName;
    }

    /**
     * Setter
     * 
     * @param aName
     *            last name
     */
    public void setLastName( String aName ) {
        lastName = aName;
    }

    /**
     * Setter
     * 
     * @param aTitle
     *            title
     */
    public void setTitle( String aTitle ) {
        title = aTitle;
    }

    /**
     * Setter
     * 
     * @param aOrg
     *            organisation
     */
    public void setOrganisation( String aOrg ) {
        organisation = aOrg;
    }

}
