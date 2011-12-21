package org.suggs.webapps.buildpipeline.domain.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent a component including any metadata that may come with it..
 * <p/>
 * User: suggitpe Date: 26/07/11 Time: 07:18
 */

public final class ComponentBean implements Comparable<ComponentBean> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ComponentBean.class );

    private String componentName;
    private Boolean isTestSuite;

    public ComponentBean( String aComponentName ) {
        this( aComponentName, false );
    }

    public ComponentBean( String aComponentName, boolean aIsTestSuite ) {
        componentName = aComponentName;
        isTestSuite = aIsTestSuite;
    }

    @Override
    public int compareTo( ComponentBean aOtherVersion ) {
        return componentName.compareTo( aOtherVersion.getComponentName() );
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName( String aComponentName ) {
        componentName = aComponentName;
    }

    public Boolean isTestSuite() {
        return isTestSuite;
    }

    public void setTestSuite( Boolean aTestSuite ) {
        isTestSuite = aTestSuite;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        ComponentBean that = ( ComponentBean ) o;

        if ( componentName != null ? !componentName.equals( that.componentName ) : that.componentName != null )
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = componentName != null ? componentName.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "ComponentBean{" +
                "componentName='" + componentName + '\'' +
                ", isTestSuite=" + isTestSuite +
                '}';
    }
}
