/*
 * BasicConnectWithTransactionTest.java created on 22 Dec 2010 15:29:26 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Basic test to understand connection logic in db connections
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-basicconnection.xml" })
public class BasicConnectWithTransactionTest {

    private static final Log LOG = LogFactory.getLog( BasicConnectWithTransactionTest.class );

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionfactory;

    @Test
    public void performBasicConnectTest() {
        executeBasicAtomicOperation( new AtomicOperationCallback() {

            @Override
            public void performAtomicOperation( Session aSession ) {
                try {
                    //
                }
                catch ( Exception e ) {
                    throw new IllegalStateException( "duff" );
                }
            }
        } );
    }

    @Ignore
    @Test
    public void performBasicSaveProcess() {
        executeBasicAtomicOperation( new AtomicOperationCallback() {

            @Override
            public void performAtomicOperation( Session aSession ) {
                ReallyBasicEntity entity = new ReallyBasicEntity();
                entity.setStringField( "String Data" );
                Long id = (Long) aSession.save( entity );
                LOG.debug( "Created really basic entity with ID of [" + id + "]" );
            }
        } );
    }

    public void executeBasicAtomicOperation( AtomicOperationCallback aCallback ) {
        Session session = sessionfactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            try {
                LOG.info( ">>>>>>> About to perform operation ..." );
                aCallback.performAtomicOperation( session );
                LOG.info( ">>>>>>> Operation performed ... about to call flush" );
                session.flush();
                LOG.info( ">>>>>>> Flush called" );
            }
            catch ( Exception ex ) {
                LOG.error( "Rolling back transaction" );
                transaction.rollback();
            }
            finally {
                if ( !transaction.wasRolledBack() ) {
                    LOG.info( "Commiting transaction - well pretending to anyway" );
                    transaction.rollback();
                }
            }

        }
        finally {
            session.close();
        }
    }

    private interface AtomicOperationCallback {

        void performAtomicOperation( Session aSession );
    }
}
