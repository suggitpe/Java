/*
 * JaxbXmlConnectionStoreManagerTest.java created on 2 Oct 2008 18:44:56 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl.JaxbXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.util.IFileManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This test tests the Jaxb Xml Connection Store Manager using a Mock
 * for the File Manager.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2008
 */
public class JaxbXmlConnectionStoreManagerUnitTest
{

    private static final Log LOG = LogFactory.getLog( JaxbXmlConnectionStoreManagerUnitTest.class );
    private JaxbXmlConnectionStoreManager mManager_;
    private IFileManager mFileManager_;

    @Before
    public void setUp() throws Exception
    {
        LOG.debug( "------------------- JaxbXmlConnectionStoreManagerTest" );
        mManager_ = new JaxbXmlConnectionStoreManager();
        mFileManager_ = EasyMock.createStrictMock( IFileManager.class );
        mManager_.setFileManager( mFileManager_ );
    }

    /**
     * This test will ensure that the xml connection store manager
     * will read data from the persistent store, transform it from the
     * String clob and then return it in a form that is understood
     */
    @Test
    public void testReadConnectionData()
    {
        // mManager_.readConnectionData();

    }

    /**
     * This test will ensure that when we pass in a map object that
     * this is then serialised back down into the correct form into
     * the clob.
     */
    @Test
    public void testSaveConnectionData()
    {
        // mManager_.saveConnectionData();
    }

}
