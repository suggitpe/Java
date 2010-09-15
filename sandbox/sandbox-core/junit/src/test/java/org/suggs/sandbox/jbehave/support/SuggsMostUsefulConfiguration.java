/*
 * SuggsMostUsefulConfiguration.java created on 15 Sep 2010 19:57:51 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.ConsoleOutput;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepFinder;
import org.jbehave.paranamer.NullParanamer;

/**
 * This is a useful configuration that should/could be used throughout the jbehave stories that I employ.
 * 
 * @author suggitpe
 * @version 1.0 15 Sep 2010
 */
public class SuggsMostUsefulConfiguration extends Configuration {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( SuggsMostUsefulConfiguration.class );

    /**
     * Constructs a new instance.
     */
    @SuppressWarnings("boxing")
    public SuggsMostUsefulConfiguration() {
        doDryRun( false );
        useKeywords( new LocalizedKeywords() );
        useStoryLoader( new LoadFromClasspath() );
        useStoryParser( new RegexStoryParser( keywords() ) );
        useFailureStrategy( new RethrowingFailure() );
        usePendingStepStrategy( new PassingUponPendingStep() );
        useDefaultStoryReporter( new ConsoleOutput() );
        useStoryReporterBuilder( new SuggsStoryReporterBuilder() );
        useStepCollector( new MarkUnmatchedStepsAsPending() );
        useStepFinder( new StepFinder() );
        useStepPatternParser( new RegexPrefixCapturingPatternParser( "$" ) );
        useStepMonitor( new SilentStepMonitor() );
        useStepdocReporter( new PrintStreamStepdocReporter() );
        useParanamer( new NullParanamer() );
        useViewGenerator( new FreemarkerViewGenerator() );
        useParameterConverters( new SuggsParamterConverters() );
    }
}
