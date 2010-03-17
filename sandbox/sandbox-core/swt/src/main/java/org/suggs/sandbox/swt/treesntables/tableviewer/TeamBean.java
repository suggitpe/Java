/*
 * TeamBean.java created on 3 Dec 2008 06:56:36 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.tableviewer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will be a container class for team players
 * 
 * @author suggitpe
 * @version 1.0 3 Dec 2008
 */
public class TeamBean
{

    private List<PlayerBean> players_ = new ArrayList<PlayerBean>();
    private String name_;
    private int year_;

    /**
     * Constructs a new instance.
     * 
     * @param name
     * @param year
     */
    public TeamBean( String name, int year )
    {
        name_ = name;
        year_ = year;
    }

    /**
     * Returns the value of players.
     * 
     * @return Returns the players.
     */
    public List<PlayerBean> getPlayers()
    {
        return players_;
    }

    /**
     * Sets the players field to the specified value.
     * 
     * @param players
     *            The players_ to set.
     */
    public void setPlayers( List<PlayerBean> players )
    {
        this.players_ = players;
    }

    /**
     * Adds a new player to the team
     * 
     * @param player
     */
    public void addPlayer( PlayerBean player )
    {
        players_.add( player );
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
     * Returns the value of year.
     * 
     * @return Returns the year.
     */
    public int getYear()
    {
        return year_;
    }

    /**
     * Sets the year field to the specified value.
     * 
     * @param year
     *            The year to set.
     */
    public void setYear( int year )
    {
        this.year_ = year;
    }

}
