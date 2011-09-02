package org.suggs.webapps.buildpipeline.jbehave.web_stories;

import org.suggs.webapps.buildpipeline.jbehave.steps.AbstractBuildPipelineSteps;
import org.suggs.webapps.buildpipeline.jbehave.steps.UserCanAccessApplicationSteps;
import org.suggs.webapps.buildpipeline.pages.JbehavePages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
