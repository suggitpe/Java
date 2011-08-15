/*
 * ICanToggleACellUsingAnnotatedStory.java created on 16 Sep 2010 19:38:48 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.test.jbehave.basicscenario.injectablestories;

import org.suggs.sandbox.test.jbehave.basicscenario.steps.GridSteps;
import org.suggs.sandbox.test.jbehave.support.AbstractInjectableStoryEmbedder;

import org.jbehave.core.annotations.UsingSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Annotated version of the game of life story.
 * 
 * @author suggitpe
 * @version 1.0 16 Sep 2010
 */
@UsingSteps(instances = { GridSteps.class })
public class ICanToggleACellUsingAnnotatedStory extends AbstractInjectableStoryEmbedder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ICanToggleACellUsingAnnotatedStory.class );

    /**
     * @see org.suggs.sandbox.test.jbehave.support.AbstractInjectableStoryEmbedder#doGetStoryRegex()
     */
    @Override
    protected String doGetStoryRegex() {
        return "**/i*.story";
    }
}
