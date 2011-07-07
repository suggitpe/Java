package com.ubs.gfit.buildpipeline.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acts as a service provider of all release versions.
 * <p/>
 * User: suggitpe
 * Date: 06/07/11
 * Time: 07:52
 */

public final class ReleaseVersionManager {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionManager.class );

    private static ReleaseVersionManager instance = new ReleaseVersionManager();

    private List<ReleaseVersion> versions = new ArrayList<ReleaseVersion>();

    public static ReleaseVersionManager instance() {
        return instance;
    }

    public void createVersion( ReleaseVersion aVersion ) {
        versions.add( aVersion );
    }

    public List<ReleaseVersion> getAllReleaseVersions() {
        return versions;
    }



}
