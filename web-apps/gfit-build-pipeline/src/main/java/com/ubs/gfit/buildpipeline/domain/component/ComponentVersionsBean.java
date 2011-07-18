package com.ubs.gfit.buildpipeline.domain.component;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the available component versions.
 * <p/>
 * User: suggitpe
 * Date: 12/07/11
 * Time: 08:17
 */

public class ComponentVersionsBean {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ComponentVersionsBean.class );

    private Map<String, List<String>> componentVersions = new HashMap<String, List<String>>();

    public List<String> getVersionsForComponent( String aComponent ) {
        return componentVersions.get( aComponent );
    }

    public Map<String, List<String>> getComponentVersions() {
        return componentVersions;
    }

    public List<String> getComponentNames() {
        List<String> keys = new ArrayList<String> ( componentVersions.keySet() );
        Collections.sort( keys);
        return keys;
    }

    public void setVersionsForComponent( String aComponent, List<String> aListOfVersions ) {
        if ( componentVersions.containsKey( aComponent ) ) {
            componentVersions.remove( aComponent );
        }
        componentVersions.put( aComponent, aListOfVersions );
    }

    public void addVersion( String aComponent, String aVersion ) {
        if ( !componentVersions.containsKey( aComponent ) ) {
            componentVersions.put( aComponent, new ArrayList<String>() );
        }
        componentVersions.get( aComponent ).add( aVersion );
    }
}
