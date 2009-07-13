/*
 * BundleData.java created on 10 Jul 2009 19:53:36 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool;

/**
 * Bean object for the bundle domain layer
 * 
 * @author suggitpe
 * @version 1.0 10 Jul 2009
 */
public class BundleData
{

    public Long id;
    public String state;
    public String location;
    public String bundleName;

    public BundleData( long aId, String aState, String aLocation, String aBundleName )
    {
        id = new Long( aId );
        state = aState;
        location = aLocation;
        bundleName = aBundleName;
    }
}
