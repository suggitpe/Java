package org.suggs.webapps.buildpipeline.domain.releaseversion;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acts as a provider of all release versions, this should be the core interface with the persistence mechanism..
 * <p/>
 * User: suggitpe Date: 06/07/11 Time: 07:52
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

    public void addVersion( ReleaseVersion aVersion ) {
        if ( aVersion.isNew() ) {
            aVersion.setVersion( Integer.toString( ++lastVersion ) );
            aVersion.setCreateDate( new Date( System.currentTimeMillis() ) );
        }
        LOG.debug( "Adding a new version [" + aVersion + "]" );
        versions.put( aVersion.getVersion(), aVersion );
    }

    public ReleaseVersion getVersion( String aVersion ) {
        return versions.get( aVersion );
    }

    public List<ReleaseVersion> getAllReleaseVersions() {
        List<ReleaseVersion> values = new ArrayList<ReleaseVersion>( versions.values() );
        Collections.sort( values );
        return values;
    }

    public void remove( String aReleaseVersion ) {
        versions.remove( aReleaseVersion );
    }

    public void deleteAllReleaseVersions() {
        LOG.warn( "Removing all release versions" );
        versions.clear();
    }
}
