package com.ubs.gfit.buildpipeline.domain.releaseversion;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent a release version of an application (be that a single component or multiple components).
 * <p/>
 * User: suggitpe
 * Date: 06/07/11
 * Time: 10:41
 */

public final class ReleaseVersion implements Comparable<ReleaseVersion> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersion.class );

    private String version = "NEW";
    private String description;
    private Date createDate;
    private Map<String, String> componentVersions = new HashMap<String, String>();

    public ReleaseVersion() {
    }

    @Override
    public int compareTo( ReleaseVersion aOtherVersion ) {
        // note the - to reverse the order
        return -( createDate.compareTo( aOtherVersion.getCreateDate() ) );
    }

    public boolean isNew() {
        return ( version.equals( "NEW" ) );
    }

    public String getVersion() {
        return version;
    }

    public void setVersion( String aVersion ) {
        version = aVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String aDescription ) {
        description = aDescription;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate( Date aDate ) {
        createDate = aDate;
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
}

