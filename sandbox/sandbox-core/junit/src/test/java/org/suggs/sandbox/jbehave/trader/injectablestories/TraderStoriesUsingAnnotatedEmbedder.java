/*
 * TraderStoriesUsingAnnotatedEmbedder.java created on 7 Sep 2010 19:46:56 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.injectablestories;

import org.suggs.sandbox.jbehave.support.SuggsStoryReporterBuilder;
import org.suggs.sandbox.jbehave.trader.injectablestories.TraderStoriesUsingAnnotatedEmbedder.MyDateConverter;
import org.suggs.sandbox.jbehave.trader.injectablestories.TraderStoriesUsingAnnotatedEmbedder.MyRegexPrefixCapturingPatternParser;
import org.suggs.sandbox.jbehave.trader.steps.TraderSteps;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Execute trader stories using the annotation runner.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */
@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyLoader = LoadFromURL.class,
    stepPatternParser = MyRegexPrefixCapturingPatternParser.class,
    storyReporterBuilder = SuggsStoryReporterBuilder.class,
    parameterConverters = { MyDateConverter.class })
@UsingEmbedder(embedder = Embedder.class)
@UsingSteps(instances = { TraderSteps.class })
public class TraderStoriesUsingAnnotatedEmbedder extends InjectableEmbedder {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TraderStoriesUsingAnnotatedEmbedder.class );
    private static final String STORY_LOCATION = CodeLocations.codeLocationFromPath( "src/test/resources" )
        .getFile();

    @Test
    @Override
    public void run() {
        StoryFinder finder = new StoryFinder();
        injectedEmbedder().runStoriesAsPaths( finder.findPaths( STORY_LOCATION,
                                                                Arrays.asList( "**/trader*.story" ),
                                                                Arrays.asList( "" ),
                                                                "file:" + STORY_LOCATION ) );
    }

    public static class MyRegexPrefixCapturingPatternParser extends RegexPrefixCapturingPatternParser {

        public MyRegexPrefixCapturingPatternParser() {
            super( "%" );
        }
    }

    public static class MyDateConverter extends DateConverter {

        public MyDateConverter() {
            super( new SimpleDateFormat( "dd-MM-yyyy" ) );
        }
    }
}
