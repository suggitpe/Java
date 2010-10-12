/*
 * RelationshipsOneToManyBidirectionalSchemaCreator.java created on 12 Oct 2010 07:44:22 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetomany;

import org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Schema Creator for One To Many Bidirectional Entity
 * 
 * @author suggitpe
 * @version 1.0 12 Oct 2010
 */
public class RelationshipsOneToManyBidirectionalSchemaCreator extends AbstractHibernateSchemaCreator {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( RelationshipsOneToManyBidirectionalSchemaCreator.class );

    @Override
    protected List<Class<?>> getEntityListForSchemaCreation() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( OneToManyBidirectionalEntity.class );
        entityClassses.add( OneToManyBidirectionalOtherEntity.class );
        return entityClassses;
    }
}
