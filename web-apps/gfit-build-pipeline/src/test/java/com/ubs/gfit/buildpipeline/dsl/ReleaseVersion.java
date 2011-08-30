package com.ubs.gfit.buildpipeline.dsl;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 30/08/11
 * Time: 18:08
 */
public interface ReleaseVersion {

    String getDescription();

    String getVersionNumber();

    String getVersionOfComponent( Component aComponent );
}
