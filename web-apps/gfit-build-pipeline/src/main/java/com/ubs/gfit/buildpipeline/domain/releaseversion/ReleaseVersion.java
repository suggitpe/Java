package com.ubs.gfit.buildpipeline.domain.releaseversion;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent a release version of an application (be that a single component or multiple components).
 * <p/>
 * User: suggitpe
 * Date: 06/07/11
 * Time: 10:41
 */

public final class ReleaseVersion {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersion.class );

    private int version = 0;
    private String description;
    private Map<String, String> componentVersions = new HashMap<String, String>( );

    public ReleaseVersion() {
    }

    public boolean isNew() {
        return ( version == 0 );
    }

    public int getVersion() {
        return version;
    }

    public void setVersion( int aVersion ) {
        version = aVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String aDescription ) {
        description = aDescription;
    }

    public Map<String, String> getComponentVersions() {
        return componentVersions;
    }

    public void setComponentVersions( Map<String, String> aComponentVersions ) {
        componentVersions = aComponentVersions;
    }

    public void addComponentVersion( String aComponentName, String aVersion ) {
        componentVersions.put( aComponentName, aVersion );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        ReleaseVersion that = ( ReleaseVersion ) o;

        if ( version != that.version ) return false;
        if ( componentVersions != null ? !componentVersions.equals( that.componentVersions ) : that.componentVersions != null )
            return false;
        if ( description != null ? !description.equals( that.description ) : that.description != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = version;
        result = 31 * result + ( description != null ? description.hashCode() : 0 );
        result = 31 * result + ( componentVersions != null ? componentVersions.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        return "ReleaseVersion{" +
                "version=" + version +
                ", description='" + description + '\'' +
                ", componentVersions=" + componentVersions +
                '}';
    }
}
