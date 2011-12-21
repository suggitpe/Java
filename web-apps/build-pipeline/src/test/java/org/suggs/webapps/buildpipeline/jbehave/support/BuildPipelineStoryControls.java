package org.suggs.webapps.buildpipeline.jbehave.support;

import org.jbehave.core.embedder.StoryControls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe Date: 12/21/11 Time: 12:17 PM
 */

public class BuildPipelineStoryControls extends StoryControls {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BuildPipelineStoryControls.class );

    public BuildPipelineStoryControls() {
        doDryRun( false );
        doSkipScenariosAfterFailure( false );
    }
}
