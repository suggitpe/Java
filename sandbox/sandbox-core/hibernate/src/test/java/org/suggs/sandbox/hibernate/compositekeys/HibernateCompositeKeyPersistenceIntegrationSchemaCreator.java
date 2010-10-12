/*
 * HibernateCompositeKeyPersistenceIntegrationSchemaCreator.java created on 12 Oct 2010 19:55:21 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositekeys;

import org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Schema creator for Composite Key Persistence Integration.
 * 
 * @author suggitpe
 * @version 1.0 12 Oct 2010
 */
public class HibernateCompositeKeyPersistenceIntegrationSchemaCreator extends AbstractHibernateSchemaCreator {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( HibernateCompositeKeyPersistenceIntegrationSchemaCreator.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractHibernateSchemaCreator#getEntityListForSchemaCreation()
     */
    @Override
    protected List<Class<?>> getEntityListForSchemaCreation() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( EntityObject.class );
        return entityClassses;
    }
}
