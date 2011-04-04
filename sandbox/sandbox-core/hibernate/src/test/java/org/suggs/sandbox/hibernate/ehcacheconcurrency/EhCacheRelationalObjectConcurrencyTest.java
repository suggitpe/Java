package org.suggs.sandbox.hibernate.ehcacheconcurrency;

import org.suggs.sandbox.hibernate.basicentity.BasicRelationshipEntity;
import org.suggs.sandbox.hibernate.basicentity.BasicRelationshipOtherEntity;

import java.util.Iterator;
import java.util.Set;
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

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe Date: 01/04/11 Time: 15:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-ehcache-concurrency.xml" })
public class EhCacheRelationalObjectConcurrencyTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( EhCacheRelationalObjectConcurrencyTest.class );

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    private Long idForTest;
    private Long relatedIdForTest;
    private final ReentrantLock lock = new ReentrantLock();

    @Before
    public void onSetup() {
        LOG.debug( "---------- Setting up basic relationship entity into the database" );
        idForTest = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            BasicRelationshipEntity entity = new BasicRelationshipEntity( "Something of interest" );
            BasicRelationshipOtherEntity other = new BasicRelationshipOtherEntity( "something else" );
            entity.addOther( other );

            session.save( entity );
            idForTest = entity.getId();
            relatedIdForTest = other.getId();
            transaction.commit();
            session.evict( entity );
        }
        catch ( Exception exception ) {
            transaction.rollback();
            throw new IllegalStateException( "Failed to create persistent objects in setup" );
        }
        finally {
            session.close();
        }
        LOG.debug( "---------- Setting up complete executing test" );
    }

    @After
    public void onTearDown() {
        LOG.debug( "---------- Test complete" );
    }

    @Test
    public void readExistingEntityFromCache() {
        LOG.debug( "Reading entity with ID [" + idForTest + "] from the database" );
        Session session = sessionFactory.openSession();
        BasicRelationshipEntity readEntity = ( BasicRelationshipEntity ) session.get( BasicRelationshipEntity.class, idForTest );
        LOG.debug( "Retrieved object [" + readEntity + "]" );
        assertThat( readEntity, notNullValue() );

        BasicRelationshipOtherEntity other = readEntity.getOthers().iterator().next();
        LOG.debug( "Retrieved other object [" + other + "]" );
        assertThat( other, notNullValue() );
    }

    @Test
    public void twoThreadsDoNotInterfereWithEachOthersRelatedObjects() throws InterruptedException {
        LOG.debug( "Starting two threads: one reader and one writer" );
        Thread reader = new Thread( new RelationalObjectReader(), "reader" );
        Thread writer = new Thread( new RelatedObjectWriter(), "writer" );
        reader.start();
        writer.start();
        writer.join();
        reader.join();
    }

    class RelationalObjectReader implements Runnable {

        @Override
        public void run() {
            lock();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            try {
                LOG.debug( "Reader thread: reading entity" );
                BasicRelationshipEntity entity = ( BasicRelationshipEntity ) session.get( BasicRelationshipEntity.class, idForTest );
                Set<BasicRelationshipOtherEntity> others = entity.getOthers();
                for( Iterator<BasicRelationshipOtherEntity> iter = others.iterator(); iter.hasNext() ; ){
                    BasicRelationshipOtherEntity other = iter.next();
                }

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

    class RelatedObjectWriter implements Runnable {

        @Override
        public void run() {
            Thread.yield();
            lock();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            try {
                LOG.debug( "Writer thread: reading entity" );
                BasicRelationshipOtherEntity entity = ( BasicRelationshipOtherEntity ) session.get( BasicRelationshipOtherEntity.class, relatedIdForTest );
                entity.setStringData( "Updated string data" );

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
