/*
 * CaveatEmptorRelationshipTest.java created on 11 May 2007 18:55:00 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;


public class CaveatEmptorAnnotationRelationshipTest extends AbstractCaveatEmptorRelationshipTest
{

    /**
     * Constructs a new instance.
     */
    public CaveatEmptorAnnotationRelationshipTest()
    {
        super();
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-annotation-caveatemptor-relationships.xml" };
    }

}
