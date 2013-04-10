/*
 * IDoubleDaoService.java created on 20 Nov 2009 19:25:20 by suggitpe for project SandBox - JUnit
 * 
 */
package org.suggs.sandbox.test.junit;

import java.util.Date;

/**
 * This is a DAO object that has the ability to get doubles in a number of ways.
 */
public interface IDoubleDaoService {

    Double doubleForDate( Date aDate ) throws DoubleDaoException;

}
