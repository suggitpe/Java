package org.suggs.webapps.buildpipeline.jbehave.web_stories;

import org.suggs.webapps.buildpipeline.jbehave.steps.AbstractBuildPipelineSteps;
import org.suggs.webapps.buildpipeline.pages.JbehavePages;

import java.util.List;

import javax.naming.Context;

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
 * Class that will execute the JBehave tests in the correct way using the correct framework interactions.
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 19:08
 */

public abstract class AbstractJBehaveStoryExecutor extends JUnitStories {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractJBehaveStoryExecutor.class );

    private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
    private JbehavePages pages = new JbehavePages( driverProvider );
    private SeleniumContext context = new SeleniumContext();
    //private ContextView contextView = new LocalFrameContextView().sized( 500, 100 );
    private ContextView contextView = new ContextView() {
        @Override
        public void show( String message ) {
            LOG.info( "Context update: " + message );
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
                .useStepMonitor( new SeleniumStepMonitor( contextView, context, new SilentStepMonitor() ) )
                .useStoryLoader( new LoadFromClasspath( embeddableClass ) )
                .useStoryReporterBuilder( buildStoryReporterBuilder( embeddableClass ) );
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        Configuration configuration = configuration();
        return new InstanceStepsFactory( configuration, createSteps( pages ),
                new PerStoriesWebDriverSteps( driverProvider ),
                new WebDriverScreenshotOnFailure( driverProvider, configuration.storyReporterBuilder() ) ).createCandidateSteps();
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths( codeLocationFromClass( this.getClass() ).getFile(), asList( createStoryLocation() ), null );
    }

    private StoryReporterBuilder buildStoryReporterBuilder( Class<?> aEmbeddableClass ) {
        return new StoryReporterBuilder()
                .withCodeLocation( CodeLocations.codeLocationFromClass( aEmbeddableClass ) )
                .withDefaultFormats()
                .withFormats( Format.HTML );
    }

    protected abstract String createStoryLocation();

    protected abstract AbstractBuildPipelineSteps createSteps( JbehavePages aPagesObject );
}
