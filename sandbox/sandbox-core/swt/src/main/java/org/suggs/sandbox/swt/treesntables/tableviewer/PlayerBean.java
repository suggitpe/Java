/*
 * PlayerBean.java created on 2 Dec 2008 19:50:05 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.treesntables.tableviewer;

/**
 * This is the player bean class to represent the data that will be
 * populated into the table.
 * 
 * @author suggitpe
 * @version 1.0 2 Dec 2008
 */
public class PlayerBean
{

    private String firstname_;
    private String lastname_;
    private double pointsPerGame_;
    private double reboundsPerGame_;
    private double assistsPerGame_;

    /**
     * Constructs a new instance.
     * 
     * @param firstname
     * @param lastname
     * @param points
     * @param rebounds
     * @param assists
     */
    public PlayerBean( String firstname, String lastname, double points, double rebounds,
                       double assists )
    {
        firstname_ = firstname;
        lastname_ = lastname;
        pointsPerGame_ = points;
        reboundsPerGame_ = rebounds;
        assistsPerGame_ = assists;
    }

    /**
     * Returns the value of firstname.
     * 
     * @return Returns the firstname.
     */
    public String getFirstname()
    {
        return firstname_;
    }

    /**
     * Sets the firstname field to the specified value.
     * 
     * @param firstname
     *            The firstname to set.
     */
    public void setFirstname( String firstname )
    {
        this.firstname_ = firstname;
    }

    /**
     * Returns the value of lastname.
     * 
     * @return Returns the lastname.
     */
    public String getLastname()
    {
        return lastname_;
    }

    /**
     * Sets the lastname field to the specified value.
     * 
     * @param lastname
     *            The lastname to set.
     */
    public void setLastname( String lastname )
    {
        this.lastname_ = lastname;
    }

    /**
     * Returns the value of pointsPerGame.
     * 
     * @return Returns the pointsPerGame.
     */
    public double getPointsPerGame()
    {
        return pointsPerGame_;
    }

    /**
     * Sets the pointsPerGame field to the specified value.
     * 
     * @param pointsPerGame
     *            The pointsPerGame to set.
     */
    public void setPointsPerGame( double pointsPerGame )
    {
        this.pointsPerGame_ = pointsPerGame;
    }

    /**
     * Returns the value of reboundsPerGame.
     * 
     * @return Returns the reboundsPerGame.
     */
    public double getReboundsPerGame()
    {
        return reboundsPerGame_;
    }

    /**
     * Sets the reboundsPerGame field to the specified value.
     * 
     * @param reboundsPerGame
     *            The reboundsPerGame to set.
     */
    public void setReboundsPerGame( double reboundsPerGame )
    {
        this.reboundsPerGame_ = reboundsPerGame;
    }

    /**
     * Returns the value of assistsPerGame.
     * 
     * @return Returns the assistsPerGame.
     */
    public double getAssistsPerGame()
    {
        return assistsPerGame_;
    }

    /**
     * Sets the assistsPerGame field to the specified value.
     * 
     * @param assistsPerGame
     *            The assistsPerGame_ to set.
     */
    public void setAssistsPerGame( double assistsPerGame )
    {
        this.assistsPerGame_ = assistsPerGame;
    }

}
