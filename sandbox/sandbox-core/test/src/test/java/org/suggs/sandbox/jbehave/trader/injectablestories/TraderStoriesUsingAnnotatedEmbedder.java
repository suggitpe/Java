/*
 * TraderStoriesUsingAnnotatedEmbedder.java created on 7 Sep 2010 19:46:56 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.injectablestories;

import org.suggs.sandbox.jbehave.support.AbstractInjectableStoryEmbedder;
import org.suggs.sandbox.jbehave.trader.steps.TraderSteps;

import org.jbehave.core.annotations.UsingSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Execute trader stories using the annotation runner.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */

@UsingSteps(instances = { TraderSteps.class })
public class TraderStoriesUsingAnnotatedEmbedder extends AbstractInjectableStoryEmbedder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TraderStoriesUsingAnnotatedEmbedder.class );

    @Override
    protected String doGetStoryRegex() {
        return "**/trader*.story";
    }

}
