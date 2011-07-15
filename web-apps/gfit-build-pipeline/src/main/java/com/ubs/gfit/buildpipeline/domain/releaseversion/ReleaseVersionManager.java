package com.ubs.gfit.buildpipeline.domain.releaseversion;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acts as a provider of all release versions, this should be the core interface with the persistence mechanism..
 * <p/>
 * User: suggitpe
 * Date: 06/07/11
 * Time: 07:52
 */

public final class ReleaseVersionManager {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionManager.class );

    private static ReleaseVersionManager instance = new ReleaseVersionManager();
    private int lastVersion = 0;

    private Map<String, ReleaseVersion> versions = new HashMap<String, ReleaseVersion>();

    public static ReleaseVersionManager instance() {
        return instance;
    }

    public void createVersion( ReleaseVersion aVersion ) {
        if ( aVersion.isNew() ) {
            aVersion.setVersion( Integer.toString( ++lastVersion ));
            aVersion.setCreateDate( new Date(System.currentTimeMillis()) );
        }
        versions.put( aVersion.getVersion(), aVersion );
    }
    public ReleaseVersion getVersion(String aVersion){
        return versions.get( aVersion);
    }

    public Collection<ReleaseVersion> getAllReleaseVersions() {
        return versions.values();
    }


}
