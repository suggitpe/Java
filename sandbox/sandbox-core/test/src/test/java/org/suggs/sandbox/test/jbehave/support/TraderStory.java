/*
 * TraderStory.java created on 2 Sep 2010 07:25:59 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.test.jbehave.support;

import org.suggs.sandbox.test.jbehave.trader.steps.TraderSteps;

import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.CasePreservingResolver;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class to maintain the key facets of the story. This will only allow us to run one story at a time.
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2010
 */
public abstract class TraderStory extends JUnitStory {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TraderStory.class );

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
        // here have chosen to use a case preserving story loader so that we can see how it loads the stories
        // differently.
        StoryPathResolver storyPathResolver = new CasePreservingResolver();

        Configuration config = new SuggsMostUsefulConfiguration();
        config.useStoryLoader( new LoadFromClasspath( embeddableClass ) );
        config.useStoryPathResolver( storyPathResolver );
        return config;
    }
}
