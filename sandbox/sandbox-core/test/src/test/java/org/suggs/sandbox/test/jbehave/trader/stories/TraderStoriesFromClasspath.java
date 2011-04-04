/*
 * TraderStoriesFromClasspath.java created on 7 Sep 2010 19:09:58 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.test.jbehave.trader.stories;

import org.suggs.sandbox.test.jbehave.support.SuggsMostUsefulConfiguration;
import org.suggs.sandbox.test.jbehave.support.SuggsParamterConverters;
import org.suggs.sandbox.test.jbehave.trader.steps.TraderSteps;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allows us to run a collection of stories.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */
public class TraderStoriesFromClasspath extends JUnitStories {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TraderStoriesFromClasspath.class );

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
        List<String> paths = finder.findPaths( codelocation, Arrays.asList( "**/Trader*.story" ), null );
        return paths;
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Configuration config = new SuggsMostUsefulConfiguration();
        config.useStoryLoader( new LoadFromClasspath( embeddableClass ) );
        config.useParameterConverters( new SuggsParamterConverters() );
        return config;
    }
}
