/*
 * CommonEmbedder.java created on 9 Sep 2010 07:09:04 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.stories;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.SilentStepMonitor;

/**
 * Common embedder class that all JBehave tests can utilise
 * 
 * @author suggitpe
 * @version 1.0 9 Sep 2010
 */
public abstract class CommonEmbedder extends Embedder {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( CommonEmbedder.class );

    @Override
    public List<CandidateSteps> candidateSteps() {
        InstanceStepsFactory factory = new InstanceStepsFactory( configuration(), doGetStepsForEmbedder() );
        return factory.createCandidateSteps();
    }

    /**
     * Builder method for the inclusion of steps for a given test.
     * 
     * @return List of Objects
     */
    protected abstract List<Object> doGetStepsForEmbedder();

    @Override
    public Configuration configuration() {
        Class<? extends Embedder> embeddable = this.getClass();
        Configuration config = new MostUsefulConfiguration();
        config.useStoryLoader( new LoadFromClasspath( embeddable.getClassLoader() ) );
        config.useStoryReporterBuilder( createStoryBuilder( embeddable ) );
        config.useParameterConverters( createParameterConverters() );
        config.useStepPatternParser( new RegexPrefixCapturingPatternParser( "%" ) );
        config.useStepMonitor( new SilentStepMonitor() );
        return config;
    }

    private StoryReporterBuilder createStoryBuilder( Class<? extends Embedder> clazz ) {
        StoryReporterBuilder builder = new StoryReporterBuilder();
        builder.withCodeLocation( CodeLocations.codeLocationFromClass( clazz ) );
        builder.withDefaultFormats();
        builder.withFormats( Format.XML, Format.TXT, Format.HTML, Format.CONSOLE );
        return builder;
    }

    private ParameterConverters createParameterConverters() {
        ParameterConverters converter = new ParameterConverters();
        converter.addConverters( new ParameterConverters.DateConverter( new SimpleDateFormat( "yyyy-MM-dd" ) ) );
        return converter;
    }
}
