package com.ubs.gfit.buildpipeline.dsl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.driver.Application;
import com.ubs.gfit.buildpipeline.pages.AbstractPages;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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

    private AbstractPages pages;

    public DSL( AbstractPages aPagesObject ) {
        pages = aPagesObject;
    }

    public void openApplication() {
        pages.homePage().open();
    }

    public void checkApplicationIsShown() {
        assertThat( pages.homePage().isShown(), is( true ) );
    }

    protected ReleaseVersion createVersionBuilder() {
        return new ReleaseVersion( application() );
    }

    private Application application() {
        return new Application();
    }

    protected Component createComponent( String aAComponent ) {
        throw new UnsupportedOperationException();
    }

    protected ReleaseVersion createReleaseWithDescription( String aDescription ) {
        return createVersionBuilder().withDescription( aDescription ).build();
    }

    protected ReleaseVersion createRelease() {
        return createVersionBuilder().build();
    }
}
