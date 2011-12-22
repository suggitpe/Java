package org.suggs.webapps.buildpipeline.jbehave.web_stories;

import org.suggs.webapps.buildpipeline.jbehave.steps.UserCanCreateReleaseVersionsSteps;
import org.suggs.webapps.buildpipeline.jbehave.support.AbstractStoryEmbedder;

import org.jbehave.core.annotations.UsingSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Executor for the release creation stories.
 * <p/>
 * User: suggitpe Date: 15/09/11 Time: 18:14
 */

@UsingSteps(instances = { UserCanCreateReleaseVersionsSteps.class })
public class UserCanCreateReleaseVersionsExecutor extends AbstractStoryEmbedder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanCreateReleaseVersionsExecutor.class );

    @Override
    protected String createStoryIncludes() {
        return "**/user-can-create-release-versions.story";
    }

}
