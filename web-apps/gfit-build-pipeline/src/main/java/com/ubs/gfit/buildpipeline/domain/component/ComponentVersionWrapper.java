package com.ubs.gfit.buildpipeline.domain.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the available component versions.
 * <p/>
 * User: suggitpe
 * Date: 12/07/11
 * Time: 08:17
 */

public class ComponentVersionWrapper {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ComponentVersionWrapper.class );

    private Map<Component, List<String>> componentVersions = new HashMap<Component, List<String>>();

    public List<String> getVersionsForComponent( Component aComponent ) {
        return componentVersions.get( aComponent );
    }

    public Map<Component, List<String>> getComponentVersions(){
        return componentVersions;
    }

    public void setVersionsForComponent( Component aComponent, List<String> aListOfVersions ) {
        if ( componentVersions.containsKey( aComponent ) ) {
            componentVersions.remove( aComponent );
        }
        componentVersions.put( aComponent, aListOfVersions );
    }

    public void addVersion( Component aComponent, String aVersion ) {
        if ( !componentVersions.containsKey( aComponent ) ) {
            componentVersions.put( aComponent, new ArrayList<String>() );
        }
        componentVersions.get( aComponent ).add( aVersion );
    }
}
