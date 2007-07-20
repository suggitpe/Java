/*
 * CaveatEmptorRelationshipTest.java created on 11 May 2007 18:55:00 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

/**
 * TODO Write javadoc for CaveatEmptorHbmRelationshipTest
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
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
