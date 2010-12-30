/*
 * BasicConnectWithTransactionTest.java created on 22 Dec 2010 15:29:26 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOG = LoggerFactory.getLogger( BasicConnectWithTransactionTest.class );

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionfactory;

    @Before
    public void onSetup() {
        LOG.debug( "----------------------" );
    }

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

    @Test
    public void performBasicSaveProcess() {
        executeBasicAtomicOperation( new AtomicOperationCallback() {

            @Override
            public void performAtomicOperation( Session aSession ) {
                ReallyBasicEntity entity = new ReallyBasicEntity( "String Data", 345 );
                Long id = (Long) aSession.save( entity );
                LOG.debug( "Created really basic entity with ID of [" + id + "]" );
            }
        } );
    }

    @Test
    public void performBasicCreateFlushUpdateFlush() {
        executeBasicAtomicOperation( new AtomicOperationCallback() {

            @Override
            public void performAtomicOperation( Session aSession ) {
                ReallyBasicEntity entity = new ReallyBasicEntity( "Some String", 999 );
                Long id = (Long) aSession.save( entity );

                LOG.debug( "Created really basic entity with ID of [" + id + "] ... flushing" );
                aSession.flush();

                LOG.debug( "Flushed object, about to update and then flush again" );
                entity.setIntField( 888 );
                aSession.flush();

                LOG.debug( "Flushed object, about to update and then flush again" );
                entity.setStringField( "rah rah rah" );
                aSession.flush();
            }
        } );
    }

    private void executeBasicAtomicOperation( AtomicOperationCallback aCallback ) {
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
                LOG.error( "Rolling back transaction", ex );
                transaction.rollback();
            }
            finally {
                if ( !transaction.wasRolledBack() ) {
                    LOG.info( "Commiting transaction" );
                    transaction.commit();
                }
            }
        }
        catch ( Exception exc ) {
            LOG.error( "Caught issues", exc );
        }
        finally {
            session.close();
        }
    }

    private interface AtomicOperationCallback {

        void performAtomicOperation( Session aSession );
    }
}
