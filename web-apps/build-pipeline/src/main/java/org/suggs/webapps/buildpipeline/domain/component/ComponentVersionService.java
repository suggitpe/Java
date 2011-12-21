package org.suggs.webapps.buildpipeline.domain.component;

/**
 * Service that will provide the means to find out all of the versions of a component.
 * <p/>
 * User: suggitpe Date: 11/07/11 Time: 20:17
 */

public interface ComponentVersionService {

    ComponentVersionsBean getComponentVersions();
}
