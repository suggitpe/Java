/*
 * AbstractHibernateSchemaCreator.java created on 12 Oct 2010 07:35:40 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Abstract class that allows us to create DDl for a given number of entities.
 * 
 * @author suggitpe
 * @version 1.0 12 Oct 2010
 */
public abstract class AbstractHibernateSchemaCreator {

    private static final Log LOG = LogFactory.getLog( AbstractHibernateSchemaCreator.class );

    /**
     * Test to create the schema for the entities under test. This is a helper method to make life easier all
     * round.
     */
    @Test
    public void createSchema() {
        LOG.debug( "Creating schema for class under test" );
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure( "hibernate.cfg.xml" );
        for ( Class<?> clazz : getEntityListForSchemaCreation() ) {
            cfg.addAnnotatedClass( clazz );
        }
        SchemaExport export = new SchemaExport( cfg );
        export.setDelimiter( ";" );
        export.create( true, false );
    }

    /**
     * Builds a collection of classes to create the schema for.
     * 
     * @return all classes to create a schema from.
     */
    protected abstract List<Class<?>> getEntityListForSchemaCreation();
}
