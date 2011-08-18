package com.ubs.gfit.buildpipeline.jbehave.web_stories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.jbehave.JbehavePages;
import com.ubs.gfit.buildpipeline.jbehave.steps.AbstractBuildPipelineSteps;
import com.ubs.gfit.buildpipeline.jbehave.steps.UserCanViewAndAlterReleaseContentSteps;

/**
 * TODO: Justify why you have written this class
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
