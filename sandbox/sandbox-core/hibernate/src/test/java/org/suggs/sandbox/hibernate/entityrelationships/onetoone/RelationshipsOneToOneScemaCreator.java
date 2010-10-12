/*
 * RelationshipsOneToOneScemaCreator.java created on 12 Oct 2010 07:41:33 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetoone;

import org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Schema creator for the one to one entity.
 * 
 * @author suggitpe
 * @version 1.0 12 Oct 2010
 */
public class RelationshipsOneToOneScemaCreator extends AbstractHibernateSchemaCreator {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( RelationshipsOneToOneScemaCreator.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator#getEntityListForSchemaCreation()
     */
    @Override
    protected List<Class<?>> getEntityListForSchemaCreation() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( OneToOneEntity.class );
        entityClassses.add( OneToOneOtherEntity.class );
        return entityClassses;
    }
}
