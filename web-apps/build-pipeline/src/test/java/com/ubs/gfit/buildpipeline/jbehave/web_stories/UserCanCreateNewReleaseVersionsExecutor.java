package com.ubs.gfit.buildpipeline.jbehave.web_stories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.pages.JbehavePages;
import com.ubs.gfit.buildpipeline.jbehave.steps.AbstractBuildPipelineSteps;
import com.ubs.gfit.buildpipeline.jbehave.steps.UserCanCreateNewReleaseVersionsSteps;

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
