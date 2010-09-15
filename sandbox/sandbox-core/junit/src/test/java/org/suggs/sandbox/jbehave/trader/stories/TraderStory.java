/*
 * TraderStory.java created on 2 Sep 2010 07:25:59 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.stories;

import org.suggs.sandbox.jbehave.support.SuggsMostUsefulConfiguration;
import org.suggs.sandbox.jbehave.trader.steps.TraderSteps;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

/**
 * Abstract class to maintain the key facets of the story. This will only allow us to run one story at a time.
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
    public TraderStory() {}

    @Override
    public List<CandidateSteps> candidateSteps() {
        InstanceStepsFactory factory = new InstanceStepsFactory( configuration(), new TraderSteps() );
        return factory.createCandidateSteps();
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Configuration config = new SuggsMostUsefulConfiguration();
        config.useStoryLoader( new LoadFromClasspath( embeddableClass ) );
        StoryPathResolver storyPathResolver = new UnderscoredCamelCaseResolver( ".story" );
        config.useStoryPathResolver( storyPathResolver );
        return config;
    }

}
