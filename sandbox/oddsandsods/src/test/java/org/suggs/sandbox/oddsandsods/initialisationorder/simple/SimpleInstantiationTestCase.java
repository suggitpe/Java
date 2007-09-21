/*
 * SimpleInstantiationTestCase.java created on 20 Sep 2007 18:01:36 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.simple;

import junit.framework.TestCase;

/**
 * Test case for the simple instantiation classes. The purpose of this
 * test is to show you that the order of execution is as follows:
 * <ol>
 * <li>SuperClass constructor is called</li>
 * <li>SuperClass constructor calls printThree which is overloaded by
 * the base class</li>
 * <li>printThree in the TestClass uses the uninitialised variable of
 * 'three' and thus prints 0 (the default int initialisation value)</li>
 * <li>The TestClass three initialiser is called</li>
 * <li>The TestClass constructor is called</li>
 * <li>The printThree method is directly called</li>
 * </ol>
 * Thus the output is 0 and then 3.
 * 
 * @author suggitpe
 * @version 1.0 20 Sep 2007
 */
public class SimpleInstantiationTestCase extends TestCase
{

    public void testBuildTestClass()
    {
        System.out.println( "=====================================" );
        TestClass t = new TestClass();
        System.out.println( "Calling print three from the test case" );
        t.printThree();
        System.out.println( "=====================================" );
    }

}
