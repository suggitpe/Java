/*
 * CaveatEmptorRelationshipTest.java created on 11 May 2007 18:55:00 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;


public class CaveatEmptorHbmRelationshipTest extends AbstractCaveatEmptorRelationshipTest
{

    /**
     * Constructs a new instance.
     */
    public CaveatEmptorHbmRelationshipTest()
    {
        super();
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
     */
    @Override
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-hbm-caveatemptor-relationships.xml" };
    }

}
