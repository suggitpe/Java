package org.suggs.webapps.buildpipeline.dsl;

import org.suggs.webapps.buildpipeline.driver.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * High level class to contain the dsl for the testing.
 * <p/>
 * User: suggitpe
 * Date: 19/08/11
 * Time: 19:50
 */

public abstract class DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( DSL.class );

    private Application application = new Application();

    protected void openApplication() {
        application.openApplication();
    }

    protected boolean applicationIsOpen() {
        return application.checkApplicationIsOpen();
    }

    protected Component createComponent( String aComponentName ) {
        return application.createInstalledComponent( aComponentName );
    }

    protected ReleaseVersion createRelease() {
        return application.createReleaseVersion();
    }

    protected ReleaseVersion createReleaseWithDescription( String aDescription ) {
        return application.createReleaseVersion( aDescription );
    }

    protected ReleaseVersion createReleaseWithComponentVersion( ComponentVersion aComponentVersion ) {
        return application.createReleaseVersion( aComponentVersion );
    }
}
