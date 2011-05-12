package org.suggs.sandbox_webapps.jbehave.web_stories;

import org.suggs.sandbox_webapps.jbehave.pages.Pages;
import org.suggs.sandbox_webapps.jbehave.steps.JBehaveWebTestSteps;

import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.web.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 10/05/11
 * Time: 07:51
 */

public class JBehaveWebTestStories extends JUnitStories {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( JBehaveWebTestStories.class );

    private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
    private Pages pages = new Pages( driverProvider );
    private SeleniumContext context = new SeleniumContext();
    //private ContextView contextView = new LocalFrameContextView().sized( 500, 100 );
    private ContextView contextView = new ContextView() {
        @Override
        public void show( String message ) {
            LOG.info( message );
        }

        @Override
        public void close() {
        }
    };

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        return new SeleniumConfiguration()
                .useSeleniumContext( context )
                .useWebDriverProvider( driverProvider )
                .useStepMonitor( new SeleniumStepMonitor( contextView, context, new SilentStepMonitor() ) )
                .useStoryLoader( new LoadFromClasspath( embeddableClass ) )
                .useStoryReporterBuilder( new StoryReporterBuilder()
                        .withCodeLocation( CodeLocations.codeLocationFromClass( embeddableClass ) )
                        .withDefaultFormats()
                        .withFormats( Format.TXT, Format.HTML, Format.XML ) );
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        Configuration configuration = configuration();
        return new InstanceStepsFactory( configuration, new JBehaveWebTestSteps( pages ),
                new PerStoriesWebDriverSteps( driverProvider ),
                new WebDriverScreenshotOnFailure( driverProvider, configuration.storyReporterBuilder() ) ).createCandidateSteps();
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths( codeLocationFromClass( this.getClass() ).getFile(), asList( "**/*.story" ), null );
    }
}
