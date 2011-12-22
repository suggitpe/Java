package org.suggs.webapps.buildpipeline.jbehave.support;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

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
        parameterConverters = { BuildPipelineDateParameterConverter.class })
public abstract class AbstractStoryEmbedder extends InjectableEmbedder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractStoryEmbedder.class );

    private static final String STORY_LOCATION = codeLocationFromClass( AbstractStoryEmbedder.class ).getFile();

    @Test
    @Override
    public void run() throws Throwable {
        StoryFinder finder = new StoryFinder();
        List<String> urls = finder.findPaths( STORY_LOCATION,
                Arrays.asList( createStoryIncludes() ),
                Arrays.asList( createStoryExcludes() ), "file:" + STORY_LOCATION );
        LOG.info( "Running stories: [" + urls + "]" );
        injectedEmbedder().runStoriesAsPaths( urls );
    }


    /**
     * This is the delegation to the test class to provide the search string to find the stories.
     *
     * @return
     */
    protected abstract String createStoryIncludes();

    /**
     * This is an optional overridable method to state any excludes.
     *
     * @return
     */
    protected String createStoryExcludes() {
        return "";
    }
}
