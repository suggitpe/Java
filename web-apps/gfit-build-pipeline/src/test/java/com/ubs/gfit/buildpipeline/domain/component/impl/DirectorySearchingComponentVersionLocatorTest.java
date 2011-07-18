package com.ubs.gfit.buildpipeline.domain.component.impl;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.domain.component.ComponentVersionsBean;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Test class for the DirectorySearchingComponentVersionLocator class
 * <p/>
 * User: suggitpe
 * Date: 12/07/11
 * Time: 19:24
 */

public class DirectorySearchingComponentVersionLocatorTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( DirectorySearchingComponentVersionLocatorTest.class );

    private DirectorySearchingComponentVersionLocator locator;

    private static final String BASE_DIR = "/tmp/testData";

    @BeforeClass
    public static void setupTestDirectories() throws IOException {
        LOG.debug( "Creating test directories");
        new File( BASE_DIR + "/CAL/1.1" ).mkdirs();
        new File( BASE_DIR + "/CAL/1.2" ).mkdirs();
        new File( BASE_DIR + "/CAL/1.3" ).mkdirs();
        new File( BASE_DIR + "/CAL/1.4" ).mkdirs();
        new File( BASE_DIR + "/FDD/1.1" ).mkdirs();
        new File( BASE_DIR + "/OTHER/1.1" ).mkdirs();
        new File( BASE_DIR + "/foo.txt" ).createNewFile();
    }

    @Before
    public void onSetup() {
        locator = new DirectorySearchingComponentVersionLocator();
        locator.setComponentInstallDirectory( BASE_DIR );
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenDirectoryDoesNotExist() {
        locator.setComponentInstallDirectory( "/foo/bar" );
        locator.getComponentVersions();
    }

    @Test
    public void shouldReadTestDirectoryVersions() {
        ComponentVersionsBean wrapper = locator.getComponentVersions();
        assertThat(wrapper.getVersionsForComponent( "CAL") , is(not( nullValue() )));
        assertThat( locator.getComponentVersions().getVersionsForComponent( "CAL" ).size(), equalTo( 4 ) );
        assertThat( locator.getComponentVersions().getVersionsForComponent( "FDD" ).size(), equalTo( 1 ) );
    }
}
