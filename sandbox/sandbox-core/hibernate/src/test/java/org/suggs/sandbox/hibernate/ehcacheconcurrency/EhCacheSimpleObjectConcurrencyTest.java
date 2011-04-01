package org.suggs.sandbox.hibernate.ehcacheconcurrency;

import org.suggs.sandbox.hibernate.basicentity.ReallyBasicEntity;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Resource;

import org.junit.After;
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

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Proves that the ehCache implementation is safe for use in concurrent processing of transactional data.
 * <p/>
 * User: suggitpe Date: 31/03/11 Time: 20:09
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-ehcache-concurrency.xml" })
public class EhCacheSimpleObjectConcurrencyTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( EhCacheSimpleObjectConcurrencyTest.class );

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    private Long idForTest;
    private final ReentrantLock lock = new ReentrantLock();

    @Before
    public void onSetup() {
        LOG.debug( "---------- Setting up basic entity into the database" );
        idForTest = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            ReallyBasicEntity entity = new ReallyBasicEntity( "Some string", 12345, new Date( System.currentTimeMillis() ) );
            session.save( entity );
            idForTest = entity.getId();
            transaction.commit();
            session.evict( entity );
        }
        catch ( Exception exception ) {
            transaction.rollback();
            throw new IllegalStateException( "Failed to create persistent object in setup" );
        }
        finally {
            session.close();
        }
        LOG.debug( "---------- Setting up complete executing test" );
    }

    @After
    public void onTeardown() {
        LOG.debug( "---------- Test complete" );
    }

    public void readExistingEntityFromCache() {
        LOG.debug( "Reading entity with ID [" + idForTest + "] from the database" );
        Session session = sessionFactory.openSession();
        ReallyBasicEntity readEntity = ( ReallyBasicEntity ) session.get( ReallyBasicEntity.class, idForTest );
        LOG.debug( "Retrieved object [" + readEntity + "]" );
        assertThat( readEntity, notNullValue() );
        // TODO: how do I assert that it did not come from a database
    }

    @Test
    public void twoThreadsDoNotInterfereWithEachOthersSimpleObjects() throws InterruptedException {

        LOG.debug( "Starting two threads: one reader and one writer" );
        Thread reader = new Thread( new SimpleObjectReaderThread(), "reader" );
        Thread writer = new Thread( new SimpleObjectWriterThread(), "writer" );
        reader.start();
        writer.start();
        writer.join();
        reader.join();
    }

    class SimpleObjectReaderThread implements Runnable {

        @Override
        public void run() {
            lock();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            try {
                LOG.debug( "Reader thread: reading entity" );
                ReallyBasicEntity entity = ( ReallyBasicEntity ) session.get( ReallyBasicEntity.class, idForTest );

                unlock();
                lock();

                session.flush();

                unlock();
                lock();

                LOG.debug( "Committing ..." );
                transaction.commit();
            }
            catch ( Exception exception ) {
                exception.printStackTrace();
                transaction.rollback();
            }
            finally {
                session.close();
                unlock();
            }
        }
    }

    class SimpleObjectWriterThread implements Runnable {

        @Override
        public void run() {
            Thread.yield();
            lock();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            try {
                LOG.debug( "Writer thread: reading entity" );
                ReallyBasicEntity entity = ( ReallyBasicEntity ) session.get( ReallyBasicEntity.class, idForTest );
                entity.setSomeString( "Updated String" );

                session.flush();

                unlock();
                lock();

                LOG.debug( "Committing ..." );
                transaction.commit();
            }
            catch ( Exception exception ) {
                exception.printStackTrace();
                transaction.rollback();
            }
            finally {
                session.close();
                unlock();
            }
        }
    }

    private void lock() {
        LOG.debug( "...locking " + Thread.currentThread().getName() );
        lock.lock();
        LOG.debug( "...locked " + Thread.currentThread().getName() );
    }

    private void unlock() {
        LOG.debug( "...unlocking " + Thread.currentThread().getName() );
        lock.unlock();
        sleep( 10 );
        Thread.yield();
        LOG.debug( "...unlocked " + Thread.currentThread().getName() );
    }

    private void sleep( long millis ) {
        try {
            Thread.sleep( millis );
        }
        catch ( InterruptedException e ) {
        }
    }
}
