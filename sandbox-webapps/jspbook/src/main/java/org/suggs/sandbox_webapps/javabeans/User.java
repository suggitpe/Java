/*
 * User.java created on 6 Nov 2007 07:35:32 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.javabeans;

import java.io.Serializable;

/**
 * Simple java bean to represent a user. To be used in te context of
 * the sandbox webapp.
 * 
 * @author suggitpe
 * @version 1.0 6 Nov 2007
 */
public class User implements Serializable
{

    private String name_;
    private String password_;

    /**
     * Constructs a new instance.
     */
    public User()
    {
        super();
    }

    /**
     * Returns the value of name.
     * 
     * @return Returns the name.
     */
    public String getName()
    {
        return name_;
    }

    /**
     * Sets the name field to the specified value.
     * 
     * @param name
     *            The name to set.
     */
    public void setName( String name )
    {
        this.name_ = name;
    }

    /**
     * Returns the value of password.
     * 
     * @return Returns the password.
     */
    public String getPassword()
    {
        return password_;
    }

    /**
     * Sets the password field to the specified value.
     * 
     * @param password
     *            The password to set.
     */
    public void setPassword( String password )
    {
        this.password_ = password;
    }

}
