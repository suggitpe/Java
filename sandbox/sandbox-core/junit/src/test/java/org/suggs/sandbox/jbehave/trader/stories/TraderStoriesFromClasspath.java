/*
 * TraderStoriesFromClasspath.java created on 7 Sep 2010 19:09:58 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.stories;

import org.suggs.sandbox.jbehave.trader.steps.TraderSteps;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.SilentStepMonitor;

import static org.jbehave.core.reporters.StoryReporterBuilder.Format.CONSOLE;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.HTML;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.TXT;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.XML;

/**
 * Allows us to run a collection of stories.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */
public class TraderStoriesFromClasspath extends JUnitStories {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TraderStoriesFromClasspath.class );

    @Override
    public List<CandidateSteps> candidateSteps() {
        InstanceStepsFactory factory = new InstanceStepsFactory( configuration(), new TraderSteps() );
        return factory.createCandidateSteps();
    }

    /**
     * @see org.jbehave.core.junit.JUnitStories#storyPaths()
     */
    @Override
    protected List<String> storyPaths() {
        String codelocation = CodeLocations.codeLocationFromClass( this.getClass() ).getFile();
        StoryFinder finder = new StoryFinder();
        return finder.findPaths( codelocation, Arrays.asList( "**/trader*.story" ), null );
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Configuration config = new MostUsefulConfiguration();
        config.useStoryLoader( new LoadFromClasspath( embeddableClass ) );
        config.useStoryReporterBuilder( createStoryReporterBuilder( embeddableClass ) );
        config.useParameterConverters( createParamaterConverters() );
        config.useStepPatternParser( new RegexPrefixCapturingPatternParser( "%" ) );
        config.useStepMonitor( new SilentStepMonitor() );
        return config;
    }

    private ParameterConverters createParamaterConverters() {
        ParameterConverters converters = new ParameterConverters();
        converters.addConverters( new ParameterConverters.DateConverter( new SimpleDateFormat( "yyyy-MM-dd" ) ) );
        return converters;
    }

    private StoryReporterBuilder createStoryReporterBuilder( Class<? extends Embeddable> aEmbeddableClass ) {
        Properties viewResources = new Properties();
        viewResources.put( "decorateNonHtml", "true" );
        StoryReporterBuilder builder = new StoryReporterBuilder();
        builder.withCodeLocation( CodeLocations.codeLocationFromClass( aEmbeddableClass ) );
        builder.withDefaultFormats();
        builder.withViewResources( viewResources );
        builder.withFormats( CONSOLE, TXT, HTML, XML );
        return builder;
    }

}
