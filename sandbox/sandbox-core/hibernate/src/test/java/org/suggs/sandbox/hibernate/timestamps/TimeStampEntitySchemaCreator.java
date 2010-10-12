/*
 * TimeStampEntitySchemaCreator.java created on 12 Oct 2010 07:39:16 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Schema creator for Timestamp entity.
 * 
 * @author suggitpe
 * @version 1.0 12 Oct 2010
 */
public class TimeStampEntitySchemaCreator extends AbstractHibernateSchemaCreator {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TimeStampEntitySchemaCreator.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator#getEntityListForSchemaCreation()
     */
    @Override
    protected List<Class<?>> getEntityListForSchemaCreation() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( TimestampedEntity.class );
        entityClassses.add( TimestampedChildEntity.class );
        return entityClassses;
    }
}
