/*
 * CaveatEmptorObjectTest.java created on 13 Apr 2007 19:33:17 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

public class CaveatEmptorHbmObjectTest extends AbstractCaveatEmptorObjectTest
{

    /**
     * Constructs a new instance.
     */
    public CaveatEmptorHbmObjectTest()
    {
        super();
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-caveat-emptor-objects.xml" };
    }

}
