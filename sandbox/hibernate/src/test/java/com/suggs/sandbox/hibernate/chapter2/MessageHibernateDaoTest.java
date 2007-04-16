package com.suggs.sandbox.hibernate.chapter2;

/*
 * MessageTest.java created on 19 Mar 2007 16:50:51 by suggitpe for project SandBox - Hibernate
 * 
 */

import com.suggs.sandbox.hibernate.chapter2.Message;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.util.Assert;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MessageHibernateDaoTest extends AbstractDependencyInjectionSpringContextTests implements InitializingBean
{

    private static final Log LOG = LogFactory.getLog( MessageHibernateDaoTest.class );

    protected SessionFactory sessionFactory_;

    public MessageHibernateDaoTest()
    {
        super();
        setPopulateProtectedVariables( true );
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( sessionFactory_, "You must set the session factory object on this test" );
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#onSetUp()
     */
    protected void onSetUp() throws Exception
    {
        LOG.debug( "running on setup" );
        Configuration cfg = new Configuration();
        cfg.addResource( "hbm/manual/message.hbm.xml" );
        sessionFactory_ = cfg.buildSessionFactory();

        cleanDownMessagesTable();
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-messagetest.xml" };
    }

    public void setSessionFactory( SessionFactory aFact )
    {

        sessionFactory_ = aFact;
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory_;
    }

    /**
     * Test to create a new Message object but not to save it to the
     * datbase.
     */
    public void testMessage()
    {
        LOG.info( "----------------------------------- testMessage start" );
        LOG.debug( "Testing message creation" );
        Message m = new Message( "This is a test" );
        LOG.debug( "Created message with text [" + m.getText() + "]" );
        LOG.info( "----------------------------------- testMessage end" );
    }

    /**
     * Test to create a new object and the associate it with the
     * peristant context.
     */
    public void testNewObject()
    {
        LOG.info( "----------------------------------- testNewObject start" );
        Session sess = getSessionFactory().openSession();
        Transaction trans = sess.beginTransaction();

        Message msg = new Message( "Hello world" );
        sess.save( msg );

        trans.commit();
        sess.close();
        LOG.debug( "New object created [" + msg.toString() + "]" );
        LOG.info( "----------------------------------- testNewObject end" );
    }

    /**
     * Test to update an existing object in the database and then save
     * down the resultant state to the database.
     */
    public void testUpdatingObject()
    {
        LOG.info( "----------------------------------- testUpdatingObject start" );
        Message m1 = createNewMessageForTest( "This is a message to be updated" );

        Session sess = getSessionFactory().openSession();
        Transaction trans = sess.beginTransaction();

        Message m = (Message) sess.get( Message.class, m1.getId() );
        LOG.debug( m.getText() );
        m.setText( "This has been modified" );
        Message next = new Message( "This is an additional message" );
        m.setNextMessage( next );

        trans.commit();
        sess.close();
        LOG.info( "----------------------------------- testUpdatingObject end" );
    }

    /**
     * Test the extractiona nd iteraton of a table is objects
     */
    public void testGettingDatabaseObjects()
    {
        LOG.info( "----------------------------------- testGettingDatabaseObjects start" );
        createNewMessageForTest( "Test message 1" );
        createNewMessageForTest( "Test message 2" );
        createNewMessageForTest( "Test message 3" );
        createNewMessageForTest( "Test message 4" );

        Session s = getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        LOG.debug( "Testing Query approach" );
        Query q1 = s.createQuery( "from Message" );
        List l1 = q1.list();
        dumpListContents( l1 );

        // can't get this working
        /*
         * LOG.debug( "Testing SQL Query approach" ); SQLQuery q2 =
         * s.createSQLQuery( "select {m.*} from MESSAGES {m}" ); List
         * l2 = q2.list(); dumpListContents( l2 );
         */

        LOG.debug( "Testing Criteria approach" );
        Criteria c = s.createCriteria( Message.class );
        List l3 = c.list();
        dumpListContents( l3 );

        t.commit();
        s.close();

        LOG.info( "----------------------------------- testGettingDatabaseObjects end" );
    }

    /**
     * Dump the contents of the list to the screen.
     * 
     * @param aList
     *            The list from which to do the dump.
     */
    private void dumpListContents( List aList )
    {
        for ( Iterator i = aList.iterator(); i.hasNext(); )
        {
            Message m = (Message) i.next();
            LOG.debug( m.toString() );
        }
    }

    /**
     * Simple method that will create and persist an object to the
     * database.
     * 
     * @param aText
     *            The text to use in the new object
     * @return the newly created text message
     */
    private Message createNewMessageForTest( String aText )
    {
        LOG.debug( "---> creating a test message with [" + aText + "]" );
        Session s = getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Message m = new Message( aText );
        s.save( m );
        s.flush();
        t.commit();
        s.close();
        LOG.debug( "---> message created" );
        return m;
    }

    /**
     * Simple method to go to the database and clean down the messages
     * table.
     */
    private void cleanDownMessagesTable()
    {
        LOG.debug( "---> CLEANING MESSAGES" );
        Session s = getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        Criteria c = s.createCriteria( Message.class );
        List res = c.list();

        for ( Iterator i = res.iterator(); i.hasNext(); )
        {
            Message m = (Message) i.next();
            LOG.debug( "deleting message [" + m.getId() + "]" );
            s.delete( m );
        }

        t.commit();
        s.close();
        LOG.debug( "---> MESSAGES CLEANED" );
    }
}
