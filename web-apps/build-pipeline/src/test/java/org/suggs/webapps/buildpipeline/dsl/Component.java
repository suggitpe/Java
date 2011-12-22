package org.suggs.webapps.buildpipeline.dsl;

/**
 * Represents a component within the architecture that is being built.
 * <p/>
 * User: suggitpe Date: 30/08/11 Time: 14:37
 */

public interface Component {


    public ComponentVersion addVersion( String aComponentVersionNumber );

    public String getName();
}
