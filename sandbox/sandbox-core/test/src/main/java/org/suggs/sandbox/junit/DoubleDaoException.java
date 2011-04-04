/*
 * DoubleDaoException.java created on 20 Nov 2009 19:28:50 by suggitpe for project SandBox - JUnit
 * 
 */
package org.suggs.sandbox.junit;

/**
 * Simple exception for the double dao
 * 
 * @author suggitpe
 * @version 1.0 20 Nov 2009
 */
public class DoubleDaoException extends DaoException {

    private static final long serialVersionUID = -6830896281578386473L;

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the exception message
     */
    public DoubleDaoException( String aMsg ) {
        super( aMsg );
    }

}
