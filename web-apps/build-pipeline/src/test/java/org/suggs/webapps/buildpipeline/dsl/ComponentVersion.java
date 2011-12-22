package org.suggs.webapps.buildpipeline.dsl;

/**
 * Represents a version of a component.
 * <p/>
 * User: suggitpe Date: 30/08/11 Time: 14:36
 */

public interface ComponentVersion {

    public String getVersionNumber();

    public Component getComponent();
}
