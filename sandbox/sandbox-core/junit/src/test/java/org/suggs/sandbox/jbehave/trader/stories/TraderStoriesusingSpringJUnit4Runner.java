/*
 * TraderStoriesusingSpringJUnit4Runner.java created on 7 Sep 2010 20:10:21 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.stories;

import org.suggs.sandbox.jbehave.trader.steps.TraderSteps;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring injected test case for the Trader scenario.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/trader/steps.xml" })
public class TraderStoriesusingSpringJUnit4Runner {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TraderStoriesusingSpringJUnit4Runner.class );

    @Resource(name = "traderSteps")
    protected TraderSteps traderSteps;

    @Test
    public void runStoriesAsPaths() {
        embedder().runStoriesAsPaths( storyPaths() );
    }

    private Embedder embedder() {
        return new CommonEmbedder() {

            @Override
            protected List<Object> doGetStepsForEmbedder() {
                return Arrays.asList( (Object) traderSteps );
            }
        };
    }

    protected List<String> storyPaths() {
        StoryFinder finder = new StoryFinder();
        String codeLocation = CodeLocations.codeLocationFromClass( this.getClass() ).getFile();
        return finder.findPaths( codeLocation, Arrays.asList( "**/trader*.story" ), Arrays.asList( "" ) );
    }

}
