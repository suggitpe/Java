package org.suggs.webapps.buildpipeline.dsl;

/**
 * Abstraction of what we need from a release version.
 * <p/>
 * User: suggitpe Date: 30/08/11 Time: 18:08
 */

public interface ReleaseVersion {

    String getDescription();

    String getVersionNumber();

    String getVersionOfComponent( Component aComponent );
}
