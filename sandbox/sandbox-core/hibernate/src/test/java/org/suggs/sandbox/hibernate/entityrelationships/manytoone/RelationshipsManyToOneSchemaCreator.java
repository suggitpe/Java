/*
 * RelationshipsManyToOneSchemaCreator.java created on 12 Oct 2010 07:45:45 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytoone;

import org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Schema creator for Many To One Entity
 * 
 * @author suggitpe
 * @version 1.0 12 Oct 2010
 */
public class RelationshipsManyToOneSchemaCreator extends AbstractHibernateSchemaCreator {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( RelationshipsManyToOneSchemaCreator.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator#getEntityListForSchemaCreation()
     */
    @Override
    protected List<Class<?>> getEntityListForSchemaCreation() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( ManyToOneEntity.class );
        entityClassses.add( ManyToOneOtherEntity.class );
        return entityClassses;
    }
}
