/*
 * AddressEntry.java created on 22 Oct 2008 07:23:50 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.wizards;


/**
 * Address Entry bean object
 * 
 * @author suggitpe
 * @version 1.0 22 Oct 2008
 */
public class AddressEntry
{

    private String firstName_;
    private String lastName_;
    private String emailAddress_;

    /**
     * Constructs a new instance.
     */
    public AddressEntry()
    {
    }

    /**
     * Constructs a new instance.
     * 
     * @param firstName
     * @param lastName
     * @param emailAddress
     */
    public AddressEntry( String firstName, String lastName, String emailAddress )
    {
        firstName_ = firstName;
        lastName_ = lastName;
        emailAddress_ = emailAddress;
    }

    /**
     * Returns the value of firstName.
     * 
     * @return Returns the firstName.
     */
    public String getFirstName()
    {
        return firstName_;
    }

    /**
     * Sets the firstName field to the specified value.
     * 
     * @param firstName
     *            The firstName to set.
     */
    public void setFirstName( String firstName )
    {
        firstName_ = firstName;
    }

    /**
     * Returns the value of lastName.
     * 
     * @return Returns the lastName.
     */
    public String getLastName()
    {
        return lastName_;
    }

    /**
     * Sets the lastName field to the specified value.
     * 
     * @param lastName
     *            The lastName to set.
     */
    public void setLastName( String lastName )
    {
        lastName_ = lastName;
    }

    /**
     * Returns the value of emailAddress.
     * 
     * @return Returns the emailAddress.
     */
    public String getEmailAddress()
    {
        return emailAddress_;
    }

    /**
     * Sets the emailAddress field to the specified value.
     * 
     * @param emailAddress
     *            The emailAddress to set.
     */
    public void setEmailAddress( String emailAddress )
    {
        emailAddress_ = emailAddress;
    }

}
