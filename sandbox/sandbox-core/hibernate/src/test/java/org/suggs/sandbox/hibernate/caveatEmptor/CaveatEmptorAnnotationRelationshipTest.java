/*
 * CaveatEmptorRelationshipTest.java created on 11 May 2007 18:55:00 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

/**
 * Test object
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class CaveatEmptorAnnotationRelationshipTest extends AbstractCaveatEmptorRelationshipTest {

    /**
     * Constructs a new instance.
     */
    public CaveatEmptorAnnotationRelationshipTest() {
        super();
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
     */
    @Override
    protected String[] getConfigLocations() {
        return new String[] { "xml/ut-annotation-caveatemptor-relationships.xml" };
    }

}
