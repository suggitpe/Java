/*
 * ICanToggleACellUsingAnnotatedStory.java created on 16 Sep 2010 19:38:48 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.basicscenario.injectablestories;

import org.suggs.sandbox.jbehave.basicscenario.steps.GridSteps;
import org.suggs.sandbox.jbehave.support.AbstractInjectableStoryEmbedder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.annotations.UsingSteps;

/**
 * Annotated version of the game of life story.
 * 
 * @author suggitpe
 * @version 1.0 16 Sep 2010
 */
@UsingSteps(instances = { GridSteps.class })
public class ICanToggleACellUsingAnnotatedStory extends AbstractInjectableStoryEmbedder {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( ICanToggleACellUsingAnnotatedStory.class );

    /**
     * @see org.suggs.sandbox.jbehave.support.AbstractInjectableStoryEmbedder#doGetStoryRegex()
     */
    @Override
    protected String doGetStoryRegex() {
        return "**/i*.story";
    }
}
