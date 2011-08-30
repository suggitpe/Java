package com.ubs.gfit.buildpipeline.dsl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.driver.Application;

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

    protected Component createComponent( String aComponent ) {
        throw new UnsupportedOperationException();
    }

    protected ReleaseVersion createReleaseWithDescription( String aDescription ) {
        return application.createReleaseVersion( aDescription );
    }

    protected ReleaseVersion createRelease() {
        return application.createReleaseVersion();
    }

    protected ReleaseVersion createReleaseWithComponentVersion( ComponentVersion aComponentVersion ) {
        return application.createReleaseVersion( aComponentVersion );
    }
}
