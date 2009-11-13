/*
 * IHighLevelInterface.java created on 12 Nov 2009 13:11:43 by suggitpe for project SandBox - JUnit
 * 
 */
package org.suggs.sandbox.junit;

/**
 * Interface used for proving how JUnit can work
 * 
 * @author suggitpe
 * @version 1.0 12 Nov 2009
 */
public interface IHighLevelInterface
{

    /**
     * Test behaviour
     * 
     * @return a boolean of some sort
     * @throws Exception
     */
    boolean performHighLevelCheck() throws Exception;

    /**
     * Test behaviour
     * 
     * @param aNumber
     * @return a String from a Number
     * @throws Exception
     */
    Integer buildIntegerFromNumber( String aNumber ) throws Exception;

}
