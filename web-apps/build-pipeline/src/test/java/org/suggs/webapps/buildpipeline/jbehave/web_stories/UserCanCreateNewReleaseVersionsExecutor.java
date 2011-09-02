package org.suggs.webapps.buildpipeline.jbehave.web_stories;

import org.suggs.webapps.buildpipeline.jbehave.steps.AbstractBuildPipelineSteps;
import org.suggs.webapps.buildpipeline.jbehave.steps.UserCanCreateNewReleaseVersionsSteps;
import org.suggs.webapps.buildpipeline.pages.JbehavePages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Executor class for the new release version story
 * <p/>
 * User: suggitpe
 * Date: 18/08/11
 * Time: 19:23
 */

public final class UserCanCreateNewReleaseVersionsExecutor extends AbstractJBehaveStoryExecutor {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanCreateNewReleaseVersionsExecutor.class );

    @Override
    protected String createStoryLocation() {
        return "**/user-can-create-new-release-versions.story";
    }

    @Override
    protected AbstractBuildPipelineSteps createSteps( JbehavePages aPagesObject ) {
        return new UserCanCreateNewReleaseVersionsSteps(aPagesObject);
    }
}
