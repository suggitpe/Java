/*
 * TraderStory.java created on 2 Sep 2010 07:25:59 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.stories;

import org.suggs.sandbox.jbehave.trader.steps.TraderSteps;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.SilentStepMonitor;

/**
 * Abstract class to maintain the key facets of the story.
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2010
 */
public abstract class TraderStory extends JUnitStory {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TraderStory.class );

    /**
     * Constructs a new instance.
     */
    public TraderStory() {
        configuredEmbedder().embedderControls()
            .doGenerateViewAfterStories( true )
            .doIgnoreFailureInStories( false )
            .doIgnoreFailureInView( false );
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        InstanceStepsFactory factory = new InstanceStepsFactory( createConfiguration(), new TraderSteps() );
        return factory.createCandidateSteps();
    }

    private Configuration createConfiguration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        StoryPathResolver storyPathResolver = new UnderscoredCamelCaseResolver( ".story" );
        Configuration config = new MostUsefulConfiguration();
        config.useStoryLoader( new LoadFromClasspath( embeddableClass.getClassLoader() ) );
        config.useStoryReporterBuilder( createStoryReporterBuilder( embeddableClass ) );
        config.useParameterConverters( createParamaterConverters() );
        config.useStoryPathResolver( storyPathResolver );
        config.useStepMonitor( new SilentStepMonitor() );
        config.useStepPatternParser( new RegexPrefixCapturingPatternParser( "%" ) );
        return config;
    }

    private ParameterConverters createParamaterConverters() {
        ParameterConverters converters = new ParameterConverters();
        // converters.addConverters( new DateConverter( new SimpleDateFormat( "yyyy-MM-dd" ) ) );
        return converters;
    }

    private StoryReporterBuilder createStoryReporterBuilder( Class<? extends Embeddable> aClazz ) {
        URL codeLocation = CodeLocations.codeLocationFromClass( aClazz );
        Properties rendering = new Properties();
        rendering.put( "decorateNonHtml", "true" );
        StoryReporterBuilder builder = new StoryReporterBuilder();
        builder.withCodeLocation( codeLocation );
        builder.withDefaultFormats();
        builder.withFormats( Format.CONSOLE, Format.XML, Format.HTML, Format.TXT );
        builder.withViewResources( rendering );
        builder.withFailureTrace( false );
        return builder;
    }
}
