package org.suggs.webapps.buildpipeline.jbehave.web_stories;

import org.suggs.webapps.buildpipeline.jbehave.steps.AbstractBuildPipelineSteps;
import org.suggs.webapps.buildpipeline.jbehave.steps.UserCanCreateReleaseVersionsSteps;
import org.suggs.webapps.buildpipeline.pages.impl.JbehavePages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe Date: 15/09/11 Time: 18:14
 */

public class UserCanCreateReleaseVersionsExecutor extends AbstractJBehaveStoryExecutor {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanCreateReleaseVersionsExecutor.class );

    @Override
    protected String createStoryLocation() {
        return "**/user-can-create-release-versions.story";
    }

    @Override
    protected AbstractBuildPipelineSteps createSteps( JbehavePages aPagesObject ) {
        return new UserCanCreateReleaseVersionsSteps( aPagesObject );
    }
}
