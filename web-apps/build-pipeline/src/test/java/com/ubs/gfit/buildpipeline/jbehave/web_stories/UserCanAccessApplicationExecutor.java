package com.ubs.gfit.buildpipeline.jbehave.web_stories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.pages.JbehavePages;
import com.ubs.gfit.buildpipeline.jbehave.steps.AbstractBuildPipelineSteps;
import com.ubs.gfit.buildpipeline.jbehave.steps.UserCanAccessApplicationSteps;

/**
 * Executor for the application access story.
 * <p/>
 * User: suggitpe
 * Date: 17/08/11
 * Time: 19:19
 */

public final class UserCanAccessApplicationExecutor extends AbstractJBehaveStoryExecutor {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanAccessApplicationExecutor.class );

    @Override
    protected String createStoryLocation() {
        return "**/user-can-access-application.story";
    }

    @Override
    protected AbstractBuildPipelineSteps createSteps( JbehavePages aPagesObject ) {
        return new UserCanAccessApplicationSteps( aPagesObject );
    }
}
