package org.suggs.webapps.buildpipeline.dsl;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 30/08/11
 * Time: 14:37
 */

public interface Component {


    public ComponentVersion addVersion( String aComponentVersionNumber );

    public String getName();
}
