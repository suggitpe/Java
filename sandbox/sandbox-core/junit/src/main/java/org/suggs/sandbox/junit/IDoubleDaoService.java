/*
 * IDoubleDaoService.java created on 20 Nov 2009 19:25:20 by suggitpe for project SandBox - JUnit
 * 
 */
package org.suggs.sandbox.junit;

import java.util.Date;

/**
 * This is a DAO object that has the ability to get doubles in a
 * number of ways.
 * 
 * @author suggitpe
 * @version 1.0 20 Nov 2009
 */
public interface IDoubleDaoService
{

    /**
     * Method to get a double for a given date
     * 
     * @param aDate
     * @return a Double for the given params
     * @throws DoubleDaoException
     */
    Double doubleForDate( Date aDate ) throws DoubleDaoException;

}
