/*
 * CaveatEmptorObjectTest.java created on 13 Apr 2007 19:33:17 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

/**
 * 
 * Test object
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class CaveatEmptorAnnotationObjectTest extends AbstractCaveatEmptorObjectTest
{

    /**
     * Constructs a new instance.
     */
    public CaveatEmptorAnnotationObjectTest()
    {
        super();
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
     */
    @Override
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-annotation-caveatemptor-objects.xml" };
    }

}
