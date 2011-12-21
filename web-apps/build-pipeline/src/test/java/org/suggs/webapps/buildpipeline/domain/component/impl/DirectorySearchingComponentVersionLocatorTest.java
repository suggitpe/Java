package org.suggs.webapps.buildpipeline.domain.component.impl;

import org.suggs.webapps.buildpipeline.domain.component.ComponentBean;
import org.suggs.webapps.buildpipeline.domain.component.ComponentVersionsBean;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Test class for the DirectorySearchingComponentVersionLocator class
 * <p/>
 * User: suggitpe Date: 12/07/11 Time: 19:24
 */

public class DirectorySearchingComponentVersionLocatorTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( DirectorySearchingComponentVersionLocatorTest.class );

    private DirectorySearchingComponentVersionLocator locator;

    private static final String BASE_DIR = "/tmp/testData";

    @BeforeClass
    public static void setupTestDirectories() throws IOException {
        LOG.debug( "Creating test directories" );
        new File( BASE_DIR + "/CAL/1.1" ).mkdirs();
        new File( BASE_DIR + "/CAL/1.2" ).mkdirs();
        new File( BASE_DIR + "/CAL/1.3" ).mkdirs();
        new File( BASE_DIR + "/CAL/1.4" ).mkdirs();
        new File( BASE_DIR + "/FDD/1.1" ).mkdirs();
        new File( BASE_DIR + "/OTHER/1.1" ).mkdirs();
        new File( BASE_DIR + "/TestSuite/1.1" ).mkdirs();
        new File( BASE_DIR + "/TestSuite/1.2" ).mkdirs();
        new File( BASE_DIR + "/TestSuite/1.3" ).mkdirs();
        new File( BASE_DIR + "/TestSuite/1.4" ).mkdirs();
        new File( BASE_DIR + "/foo.txt" ).createNewFile();
    }

    @Before
    public void onSetup() {
        locator = new DirectorySearchingComponentVersionLocator();
        locator.setComponentInstallDirectory( BASE_DIR );
        locator.addTestComponent( "TestSuite" );
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenDirectoryDoesNotExist() {
        locator.setComponentInstallDirectory( "/foo/bar" );
        locator.getComponentVersions();
    }

    @Test
    public void shouldReadTestDirectoryVersions() {
        ComponentVersionsBean wrapper = locator.getComponentVersions();
        assertThat( wrapper.getVersionsForComponent( "CAL" ), is( not( nullValue() ) ) );
        assertThat( wrapper.getVersionsForComponent( "CAL" ).size(), equalTo( 4 ) );
        assertThat( wrapper.getVersionsForComponent( "FDD" ).size(), equalTo( 1 ) );
    }

    @Test
    public void shouldAllowDiscreteRetrievalOfComponentsBasedOnThoseAvailable() {
        List<ComponentBean> components = locator.getComponentVersions().getComponents();
        for ( ComponentBean bean : components ) {
            LOG.info( "Checking that bean [" + bean + "] is not a test suite" );
            assertThat( "Bean [" + bean + "] is not a component", bean.isTestSuite(), is( false ) );
        }
    }

    @Test
    public void shouldAllowDiscreteRetrievalOfTestSuitesBasedOnThoseAvailable() {
        List<ComponentBean> suites = locator.getComponentVersions().getTestSuites();
        for ( ComponentBean bean : suites ) {
            LOG.info( "Checking that bean [" + bean + "] is a test suite" );
            assertThat( "Bean [" + bean + "] is not a test suite", bean.isTestSuite(), is( true ) );
        }
    }
}
