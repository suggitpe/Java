/*
 * CaveatEmptorObjectTest.java created on 13 Apr 2007 19:33:17 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

/**
 * 
 * TODO Write javadoc for CaveatEmptorHbmObjectTest
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
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
    @Override
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-hbm-caveatemptor-objects.xml" };
    }

}
