/*
 * DaoException.java created on 19 May 2010 07:16:17 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.junit;

/**
 * TODO Write javadoc for DaoException
 * 
 * @author suggitpe
 * @version 1.0 19 May 2010
 */
public class DaoException extends Exception {

    private static final long serialVersionUID = 6819084697034950257L;

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the exception message
     */
    public DaoException( String aMsg ) {
        super( aMsg );
    }
}
