package org.suggs.webapps.buildpipeline.dsl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 30/08/11
 * Time: 14:36
 */

public final class ComponentVersion {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ComponentVersion.class );

    private Component component;
    private String versionNumber;

    public ComponentVersion( Component aComponent, String aVersionNumber ) {
        component = aComponent;
        versionNumber = aVersionNumber;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public Component getComponent() {
        return component;
    }
}
