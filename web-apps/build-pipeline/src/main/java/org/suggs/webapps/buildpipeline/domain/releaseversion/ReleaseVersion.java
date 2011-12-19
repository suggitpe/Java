package org.suggs.webapps.buildpipeline.domain.releaseversion;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent a release version of an application (be that a single component or multiple components).
 * <p/>
 * User: suggitpe Date: 06/07/11 Time: 10:41
 */

public final class ReleaseVersion implements Comparable<ReleaseVersion> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersion.class );

    private static final String NEW = "NEW";
    private String version = NEW;
    private String description;
    private Date createDate;
    private Map<String, String> componentVersions = new HashMap<String, String>();

    public ReleaseVersion() {
    }

    @Override
    public int compareTo( ReleaseVersion aOtherVersion ) {
        // note the '-' to reverse the order
        return -( createDate.compareTo( aOtherVersion.getCreateDate() ) );
    }

    public boolean isNew() {
        return version.equals( NEW );
    }

    public String getVersion() {
        return version;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();    //To change body of overridden methods use File | Settings | File Templates.
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
        createDate = ( Date ) aDate.clone();
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

        if ( componentVersions != null ? !componentVersions.equals( that.componentVersions ) : that.componentVersions != null )
            return false;
        if ( createDate != null ? !createDate.equals( that.createDate ) : that.createDate != null ) return false;
        if ( description != null ? !description.equals( that.description ) : that.description != null ) return false;
        if ( version != null ? !version.equals( that.version ) : that.version != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + ( description != null ? description.hashCode() : 0 );
        result = 31 * result + ( createDate != null ? createDate.hashCode() : 0 );
        result = 31 * result + ( componentVersions != null ? componentVersions.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        return "ReleaseVersionImpl{" +
                "version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", componentVersions=" + componentVersions +
                '}';
    }
}

