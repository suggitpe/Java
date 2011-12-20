package org.suggs.webapps.buildpipeline.jbehave.web_stories;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe Date: 12/19/11 Time: 5:40 PM
 */

@RunWith(AnnotatedEmbedderRunner.class)
@UsingEmbedder(embedder = Embedder.class)
@Configure(storyLoader = LoadFromURL.class,
        stepPatternParser = AbstractStoryEmbedder.MyRegexPrefixCapturingPatternParser.class,
        storyReporterBuilder = AbstractStoryEmbedder.SuggsStoryReporterBuilder.class,
        parameterConverters = { AbstractStoryEmbedder.MyDateConverter.class })
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


    protected abstract String createStoryLocation();

    public static class MyRegexPrefixCapturingPatternParser extends RegexPrefixCapturingPatternParser {

        public MyRegexPrefixCapturingPatternParser() {
            super( "$" );
        }
    }

    public static class MyDateConverter extends ParameterConverters.DateConverter {

        public MyDateConverter() {
            super( new SimpleDateFormat( "dd-MM-yyyy" ) );
        }
    }

    public static class SuggsStoryReporterBuilder extends StoryReporterBuilder {

        /**
         * Constructs a new instance.
         */
        public SuggsStoryReporterBuilder() {
            /*
            Properties viewResources = new Properties();
            viewResources.put( "decorateNonHtml", "true" );
            this.withPathResolver( new FilePrintStreamFactory.ResolveToSimpleName() );
            this.withViewResources( viewResources );
            this.withFailureTrace( false );
            */
            withCodeLocation(CodeLocations.codeLocationFromClass( AbstractStoryEmbedder.class ));
            withDefaultFormats();
            withFormats( org.jbehave.core.reporters.Format.HTML);
        }
    }
}
