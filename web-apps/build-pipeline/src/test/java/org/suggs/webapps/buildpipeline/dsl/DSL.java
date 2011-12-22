package org.suggs.webapps.buildpipeline.dsl;

import org.suggs.webapps.buildpipeline.driver.Application;
import org.suggs.webapps.buildpipeline.pages.Pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * High level class to contain the dsl for the testing.
 * <p/>
 * User: suggitpe Date: 19/08/11 Time: 19:50
 */

public abstract class DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( DSL.class );

    private Application application;

    public DSL( Pages aPagesObject ) {
        application = new Application( aPagesObject );
    }

    public void openApplication() {
        application.openApplication();
    }

    public boolean applicationIsOpen() {
        return application.checkApplicationIsOpen();
    }

    public Component createComponent( String aComponentName ) {
        return application.createInstalledComponent( aComponentName );
    }

    public ReleaseVersion createRelease() {
        return application.createReleaseVersion();
    }

    public ReleaseVersion createReleaseWithDescription( String aDescription ) {
        return application.createReleaseVersion( aDescription );
    }

    public ReleaseVersion createReleaseWithComponentVersion( ComponentVersion aComponentVersion ) {
        return application.createReleaseVersion( aComponentVersion );
    }
}
