package org.suggs.webapps.buildpipeline.jbehave.web_stories;

import org.suggs.webapps.buildpipeline.jbehave.steps.UserCanAccessApplicationSteps;
import org.suggs.webapps.buildpipeline.jbehave.support.AbstractStoryEmbedder;

import org.jbehave.core.annotations.UsingSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Executor for the application access story.
 * <p/>
 * User: suggitpe Date: 17/08/11 Time: 19:19
 */

@UsingSteps(instances = { UserCanAccessApplicationSteps.class })
public final class UserCanAccessApplicationExecutor extends AbstractStoryEmbedder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanAccessApplicationExecutor.class );

    @Override
    protected String createStoryIncludes() {
        return "**/user-can-access-application.story";
    }
}
