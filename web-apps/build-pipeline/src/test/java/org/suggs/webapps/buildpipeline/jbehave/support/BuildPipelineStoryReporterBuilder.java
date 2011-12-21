package org.suggs.webapps.buildpipeline.jbehave.support;

import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration for the report construction once the tests have completed.
 * <p/>
 * User: suggitpe Date: 12/21/11 Time: 7:25 AM
 */

public class BuildPipelineStoryReporterBuilder extends StoryReporterBuilder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BuildPipelineStoryReporterBuilder.class );

    public BuildPipelineStoryReporterBuilder() {
        withCodeLocation( CodeLocations.codeLocationFromClass( BuildPipelineStoryReporterBuilder.class ) );
        withDefaultFormats();
        withFormats( org.jbehave.core.reporters.Format.HTML );
    }
}
