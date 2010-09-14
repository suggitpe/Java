/*
 * TraderStoriesUsingAnnotatedEmbedder.java created on 7 Sep 2010 19:46:56 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.injectablestories;

import org.suggs.sandbox.jbehave.trader.injectablestories.TraderStoriesUsingAnnotatedEmbedder.MyDateConverter;
import org.suggs.sandbox.jbehave.trader.injectablestories.TraderStoriesUsingAnnotatedEmbedder.MyRegexPrefixCapturingPatternParser;
import org.suggs.sandbox.jbehave.trader.injectablestories.TraderStoriesUsingAnnotatedEmbedder.MyReportBuilder;
import org.suggs.sandbox.jbehave.trader.injectablestories.TraderStoriesUsingAnnotatedEmbedder.MyStoryLoader;
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
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
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
@Configure(storyLoader = MyStoryLoader.class,
    stepPatternParser = MyRegexPrefixCapturingPatternParser.class,
    storyReporterBuilder = MyReportBuilder.class,
    parameterConverters = { MyDateConverter.class })
@UsingEmbedder(embedder = Embedder.class)
@UsingSteps(instances = { TraderSteps.class })
public class TraderStoriesUsingAnnotatedEmbedder extends InjectableEmbedder {

    private static final Log LOG = LogFactory.getLog( TraderStoriesUsingAnnotatedEmbedder.class );

    @Test
    @Override
    public void run() {
        injectedEmbedder().configuration().toString();
        StoryFinder finder = new StoryFinder();
        String codeLocation = CodeLocations.codeLocationFromClass( this.getClass() ).getFile();
        injectedEmbedder().runStoriesAsPaths( finder.findPaths( codeLocation,
                                                                Arrays.asList( "**/trader*.story" ),
                                                                Arrays.asList( "" ) ) );
    }

    public static class MyReportBuilder extends StoryReporterBuilder {

        public MyReportBuilder() {
            this.withFormats( Format.CONSOLE, Format.TXT, Format.HTML, Format.XML ).withDefaultFormats();
        }
    }

    public static class MyStoryLoader extends LoadFromClasspath {

        public MyStoryLoader() {
            super( TraderStoriesUsingAnnotatedEmbedder.class );
            LOG.info( "################ Classloader ..." );
        }
    }

    public static class MyRegexPrefixCapturingPatternParser extends RegexPrefixCapturingPatternParser {

        public MyRegexPrefixCapturingPatternParser() {
            super( "%" );
        }
    }

    public static class MyDateConverter extends DateConverter {

        public MyDateConverter() {
            super( new SimpleDateFormat( "yyyy-MM-dd" ) );
        }
    }
}
