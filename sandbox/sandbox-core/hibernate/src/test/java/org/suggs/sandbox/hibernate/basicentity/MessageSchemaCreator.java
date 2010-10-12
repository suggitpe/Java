/*
 * MessageSchemaCreator.java created on 12 Oct 2010 19:58:36 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.basicentity;

import org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Schema Creator for Message
 * 
 * @author suggitpe
 * @version 1.0 12 Oct 2010
 */
public class MessageSchemaCreator extends AbstractHibernateSchemaCreator {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( MessageSchemaCreator.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator#getEntityListForSchemaCreation()
     */
    @Override
    protected List<Class<?>> getEntityListForSchemaCreation() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( Message.class );
        return entityClassses;
    }
}
