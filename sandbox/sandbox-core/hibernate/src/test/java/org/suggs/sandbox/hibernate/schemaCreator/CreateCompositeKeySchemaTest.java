/*
 * CreateCompositeKeySchemaTest.java created on 18 Mar 2010 21:56:30 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.schemaCreator;

import org.suggs.sandbox.hibernate.compositekeys.EntityObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Creates th schema for the CompositeKey class.
 * 
 * @author suggitpe
 * @version 1.0 18 Mar 2010
 */
public class CreateCompositeKeySchemaTest
{

    private static final Log LOG = LogFactory.getLog( CreateCompositeKeySchemaTest.class );

    @Test
    public void createCompositeKeySchema()
    {
        LOG.debug( "Creating schema for compositekeys" );
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure( "hibernate.cfg.xml" );
        cfg.addAnnotatedClass( EntityObject.class );
        SchemaExport export = new SchemaExport( cfg );
        export.setDelimiter( ";" );
        export.create( true, false );
    }

}
