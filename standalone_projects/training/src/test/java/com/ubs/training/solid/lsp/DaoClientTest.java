/*
 * DaoClientTest.java created on 1 Oct 2009 14:49:08 by suggitpe for project Graduate Training
 * 
 */
package com.ubs.training.solid.lsp;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO Write javadoc for DaoClientTest
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2009
 */
public class DaoClientTest
{

    private DaoClient mClient_;
    IBeanDao mMockDao;

    @Before
    public void doBefore()
    {
        mClient_ = new DaoClient();

        mMockDao = EasyMock.createMock( IBeanDao.class );
        mClient_.setDao( mMockDao );
    }

    @Test
    public void checkMyDaoClient()
    {
        EasyMock.expect( mMockDao.readBean() ).andReturn( new PersistableBean() );

        EasyMock.replay( mMockDao );

        mClient_.doSomething();

        EasyMock.verify( mMockDao );
    }
}
