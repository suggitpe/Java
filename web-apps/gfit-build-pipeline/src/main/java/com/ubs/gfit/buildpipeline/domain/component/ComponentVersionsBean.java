package com.ubs.gfit.buildpipeline.domain.component;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

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

    private Map<ComponentBean, List<String>> componentVersions = new HashMap<ComponentBean, List<String>>();

    public List<String> getVersionsForComponent( String aComponent ) {
        return componentVersions.get( new ComponentBean( aComponent ) );
    }

    public Map<ComponentBean, List<String>> getComponentVersions() {
        return componentVersions;
    }

    public List<ComponentBean> getComponents() {
        return getComponents( false );
    }

    public List<ComponentBean> getTestComponents() {
        return getComponents( true );
    }

    private List<ComponentBean> getComponents( boolean isTestSuite ) {
        List<ComponentBean> keys = new ArrayList<ComponentBean>( componentVersions.keySet() );
        List<ComponentBean> good = new ArrayList<ComponentBean>( );
        for( ComponentBean bean : keys ){
            if( bean.isTestSuite() ){
                good.add( bean);
            }
        }
        Collections.sort( good);
        return good;
    }

    public void setVersionsForComponent( ComponentBean aComponent, List<String> aListOfVersions ) {
        if ( componentVersions.containsKey( aComponent ) ) {
            componentVersions.remove( aComponent );
        }
        componentVersions.put( aComponent, aListOfVersions );
    }

    public void addVersion( ComponentBean aComponent, String aVersion ) {
        if ( !componentVersions.containsKey( aComponent ) ) {
            componentVersions.put( aComponent, new ArrayList<String>() );
        }
        componentVersions.get( aComponent ).add( aVersion );
    }
}
