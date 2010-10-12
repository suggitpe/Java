/*
 * RelationshipsManyToManyUnidirectionalSchemaCreator.java created on 12 Oct 2010 19:53:41 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytomany;

import org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Schema creator for Many To Many Unidirectional
 * 
 * @author suggitpe
 * @version 1.0 12 Oct 2010
 */
public class RelationshipsManyToManyUnidirectionalSchemaCreator extends AbstractHibernateSchemaCreator {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( RelationshipsManyToManyUnidirectionalSchemaCreator.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator#getEntityListForSchemaCreation()
     */
    @Override
    protected List<Class<?>> getEntityListForSchemaCreation() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( ManyToManyUnidirectionalEntity.class );
        entityClassses.add( ManyToManyUnidirectionalOtherEntity.class );
        return entityClassses;
    }
}
