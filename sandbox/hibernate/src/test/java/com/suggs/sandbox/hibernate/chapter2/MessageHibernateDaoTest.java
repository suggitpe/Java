package com.suggs.sandbox.hibernate.chapter2;

/*
 * MessageTest.java created on 19 Mar 2007 16:50:51 by suggitpe for project SandBox - Hibernate
 * 
 */

import com.suggs.sandbox.hibernate.support.AbstractHibernateSpringTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.Assert;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class MessageHibernateDaoTest extends AbstractHibernateSpringTest
{

    private static final Log LOG = LogFactory.getLog( MessageHibernateDaoTest.class );

    public MessageHibernateDaoTest()
    {
        super();
    }

    /**
     * @see com.suggs.sandbox.hibernate.common.AbstractHibernateSpringTest#doCleanUpOldData()
     */
    protected void doCleanUpOldData()
    {
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
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-messagetest.xml" };
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

        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { Message.class };
            }

            public String getTestName()
            {
                return "testNewObject";
            }

            public void preTestSetup( Session aSession )
            {
            }

            public void runTest( Session aSession )
            {
                Message msg = new Message( "Hello world" );
                aSession.save( msg );
                LOG.debug( "New object created [" + msg.toString() + "]" );
            }
        } );

    }

    /**
     * Test to update an existing object in the database and then save
     * down the resultant state to the database.
     */
    public void testUpdatingObject()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { Message.class };
            }

            public String getTestName()
            {
                return "testUpdatingObject";
            }

            public void preTestSetup( Session aSession )
            {
                Message m = new Message( "This is a message to be updated" );
                aSession.save( m );
            }

            public void runTest( Session aSession )
            {
                Criteria c = aSession.createCriteria( Message.class );
                c.add( Restrictions.like( "text", "This is a message%" ) );
                List l = c.list();

                Assert.isTrue( l.size() == 1, "Expecting 1 object in the database but we actually got [" + l.size() + "]" );
                Message m = (Message) l.get( 0 );
                LOG.debug( "current text = [" + m.getText() + "]" );
                m.setText( "This is the changed text for message obj" );

                Message next = new Message( "This is the next message" );

                m.setNextMessage( next );

            }
        } );

    }

    /**
     * Test the extraction and iteraton of a table is objects
     */
    public void testGettingDatabaseObjects()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { Message.class };
            }

            public String getTestName()
            {
                return "testGettingDatabaseObjects";
            }

            public void preTestSetup( Session aSession )
            {
                addTestMessages( aSession );
            }

            public void runTest( Session aSession )
            {
                // use a query
                LOG.debug( "Testing query approach" );
                Query q = aSession.createQuery( "from Message" );
                List l1 = q.list();
                dumpListContents( l1 );

                // use a criterion
                LOG.debug( "Testing criterion approach" );
                Criteria c = aSession.createCriteria( Message.class );
                List l2 = c.list();
                dumpListContents( l2 );

            }
        } );
    }

    public void testHqlQuery()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { Message.class };
            }

            public String getTestName()
            {
                return "testHqlQuery";
            }

            public void preTestSetup( Session aSession )
            {
                addTestMessages( aSession );
            }

            public void runTest( Session aSession )
            {
                LOG.debug( "Test getting unique result with a HQl" );
                Collection<String> col = new ArrayList<String>();
                col.add( "Test message [1]" );
                col.add( "Test message [3]" );

                StringBuffer query = new StringBuffer( "from Message as msg where msg.text in (" );
                Object[] params = col.toArray();
                for ( int i = 0; i < params.length; ++i )
                {
                    if ( i != 0 )
                    {
                        query.append( "," );
                    }
                    query.append( ":colParam" ).append( i );
                }
                query.append( ")" );

                LOG.debug( "query: " + query.toString() );
                Query h = aSession.createQuery( query.toString() );
                for ( int i = 0; i < params.length; ++i )
                {
                    h.setParameter( "colParam" + i, params[i] );
                }

                List l3 = h.list();
                dumpListContents( l3 );

            }
        } );
    }

    /**
     * Simple one that allows us to add a few messages to the db
     * 
     * @param aSession
     */
    private void addTestMessages( Session aSession )
    {
        int numMsgs = 4;
        Message last = null;
        for ( int i = 0; i < numMsgs; ++i )
        {
            Message m = new Message( "Test message [" + i + "]" );
            if ( i != 0 )
            {
                m.setNextMessage( last );
            }
            last = m;
            aSession.save( m );
        }
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

}
