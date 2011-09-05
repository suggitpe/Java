package org.suggs.webapps.buildpipeline.driver;

import org.suggs.webapps.buildpipeline.dsl.ComponentVersion;
import org.suggs.webapps.buildpipeline.dsl.ReleaseVersion;
import org.suggs.webapps.buildpipeline.pages.SeleniumPages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 30/08/11
 * Time: 14:55
 */

public final class Application {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Application.class );

    private SeleniumPages pages = new SeleniumPages();

    public void openApplication() {
        pages.homePage().open();
    }

    public boolean checkApplicationIsOpen() {
        return pages.homePage().isShown();
    }

    public String getDescriptionFor( String aVersionNumber ) {
        pages.releaseVersionShow().openWithVersion( aVersionNumber );
        return pages.releaseVersionShow().getDescription();
    }

    public ReleaseVersionImpl createReleaseVersion( String aDescription ) {
        pages.releaseVersionForm().openForNew();
        pages.releaseVersionForm().setDescription( aDescription );
        pages.releaseVersionForm().completeNew();
        String versionNumber = pages.releaseVersionShow().getVersion();

        ReleaseVersionImpl releaseVersion = new ReleaseVersionImpl( this );
        releaseVersion.setVersionNumber( versionNumber );
        return releaseVersion;
    }

    public ReleaseVersionImpl createReleaseVersion() {
        return createReleaseVersion( "Foo" );
    }

    public ReleaseVersion createReleaseVersion( ComponentVersion aComponentVersion ) {
        pages.releaseVersionForm().openForNew();
        pages.releaseVersionForm().setDescription( "Foo" );
        pages.releaseVersionForm().setComponentVersion( aComponentVersion.getComponent().getName(), aComponentVersion.getVersionNumber() );
        pages.releaseVersionForm().completeNew();
        String versionNumber = pages.releaseVersionShow().getVersion();

        ReleaseVersionImpl releaseVersion = new ReleaseVersionImpl( this );
        releaseVersion.setVersionNumber( versionNumber );
        return releaseVersion;
    }

    public ComponentImpl createInstalledComponent( String aComponentName ) {
        try {
            File componentInstallDir = new File( readComponentInstallDirectory() );
            if ( !componentInstallDir.exists() ) {
                throw new IllegalStateException( "Cannot read application component install dir" );
            }
            return new ComponentImpl( this, FileUtils.createFreshDirectory( aComponentName, componentInstallDir ) );
        }
        catch ( IOException ioe ) {
            throw new IllegalStateException( "Unable to create installed component" );
        }
    }

    private String readComponentInstallDirectory() throws IOException {
        URL url = ClassLoader.getSystemResource( "real.properties" );
        Properties properties = new Properties();
        properties.load( new FileInputStream( new File( url.getFile() ) ) );
        return properties.getProperty( "component.install.dir" );
    }


    public ComponentVersionImpl createInstalledComponentVersion( ComponentImpl aComponent, String aComponentVersionNumber ) {
        File versionInstallDirectory = FileUtils.createFreshDirectory( aComponentVersionNumber, aComponent.getComponentInstallDirectory() );
        return new ComponentVersionImpl( aComponent, aComponentVersionNumber, versionInstallDirectory );
    }
}
