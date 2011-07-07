package com.ubs.gfit.buildpipeline.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 06/07/11
 * Time: 10:41
 */

public class ReleaseVersion {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersion.class );

    private int version;

    private String description;

    public ReleaseVersion() {
    }

    public ReleaseVersion( int aVersion, String aDescription ) {
        version = aVersion;
        description = aDescription;
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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        ReleaseVersion that = ( ReleaseVersion ) o;

        if ( version != that.version ) return false;
        if ( description != null ? !description.equals( that.description ) : that.description != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = version;
        result = 31 * result + ( description != null ? description.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        return "ReleaseVersion{" +
                "version=" + version +
                ", description='" + description + '\'' +
                '}';
    }
}
