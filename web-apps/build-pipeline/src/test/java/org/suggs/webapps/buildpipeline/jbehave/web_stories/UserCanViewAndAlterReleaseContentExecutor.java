package org.suggs.webapps.buildpipeline.jbehave.web_stories;

import org.suggs.webapps.buildpipeline.jbehave.steps.AbstractBuildPipelineSteps;
import org.suggs.webapps.buildpipeline.jbehave.steps.UserCanViewAndAlterReleaseContentSteps;
import org.suggs.webapps.buildpipeline.pages.JbehavePages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Executor class for the view and alter story.
 * <p/>
 * User: suggitpe
 * Date: 18/08/11
 * Time: 07:37
 */

public final class UserCanViewAndAlterReleaseContentExecutor extends AbstractJBehaveStoryExecutor {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanViewAndAlterReleaseContentExecutor.class );

    @Override
    protected String createStoryLocation() {
        return "**/user-can-view-and-alter-release-content.story";
    }

    @Override
    protected AbstractBuildPipelineSteps createSteps( JbehavePages aPagesObject ) {
        return new UserCanViewAndAlterReleaseContentSteps( aPagesObject );
    }
}
