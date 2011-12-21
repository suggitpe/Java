package org.suggs.webapps.buildpipeline.jbehave.support;

import java.util.Arrays;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This abstract class should be used by all JBehave classes as their parent.  This sets up the core JBehave
 * configuration and bindings.
 * <p/>
 * User: suggitpe Date: 12/19/11 Time: 5:40 PM
 */

@RunWith(AnnotatedEmbedderRunner.class)
@UsingEmbedder(embedder = Embedder.class)
@Configure(storyLoader = LoadFromURL.class,
        stepPatternParser = RegexPrefixCapturingPatternParser.class,
        storyReporterBuilder = BuildPipelineStoryReporterBuilder.class,
        parameterConverters = { DateParameterConverter.class })
public abstract class AbstractStoryEmbedder extends InjectableEmbedder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractStoryEmbedder.class );

    private static final String STORY_LOCATION = CodeLocations.codeLocationFromClass( AbstractStoryEmbedder.class ).getFile();

    @Test
    @Override
    public void run() throws Throwable {
        StoryFinder finder = new StoryFinder();
        injectedEmbedder().runStoriesAsPaths( finder.findPaths( STORY_LOCATION,
                Arrays.asList( createStoryLocation() ),
                Arrays.asList( "" ),
                "file:" + STORY_LOCATION ) );
    }


    /**
     * This is the key templated method call.  This will satisfy the regex we are looking for to find teh relevant
     * stories.
     *
     * @return
     */
    protected abstract String createStoryLocation();
}
