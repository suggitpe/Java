package com.ubs.gfit.buildpipeline.domain.component.impl;

import java.io.File;
import java.io.IOException;

import org.hamcrest.core.IsNot;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.domain.component.Component;
import com.ubs.gfit.buildpipeline.domain.component.ComponentVersionWrapper;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * TODO: Justify why you have written this class
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

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenDirectoryDoesNotExist() {
        locator.setComponentInstallDirectory( "/foo/bar" );
        locator.getComponentVersions();
    }

    @Test
    public void shouldReadTestDirectoryVersions() {
        ComponentVersionWrapper wrapper = locator.getComponentVersions();
        assertThat(wrapper.getVersionsForComponent( Component.CAL) , is(not( nullValue() )));
        assertThat( locator.getComponentVersions().getVersionsForComponent( Component.CAL ).size(), equalTo( 4 ) );
        assertThat( locator.getComponentVersions().getVersionsForComponent( Component.FDD ).size(), equalTo( 1 ) );
    }
}
