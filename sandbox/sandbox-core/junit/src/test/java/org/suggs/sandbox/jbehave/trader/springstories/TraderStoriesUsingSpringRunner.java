/*
 * TraderStoriesUsingSpringRunner.java created on 17 Sep 2010 07:22:23 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.springstories;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.spring.UsingSpring;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.spring.SpringAnnotatedEmbedderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Execute the stories using a spring junt4 runner
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2010
 */
@RunWith(SpringAnnotatedEmbedderRunner.class)
@Configure(storyLoader = LoadFromURL.class)
@UsingEmbedder(embedder = Embedder.class)
@UsingSpring(resources = { "classpath:xml/trader/configuration.xml", "classpath:xml/trader/steps.xml" })
public class TraderStoriesUsingSpringRunner extends InjectableEmbedder {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TraderStoriesUsingSpringRunner.class );

    /**
     * @see org.jbehave.core.Embeddable#run()
     */
    @Test
    @Override
    public void run() throws Throwable {
        injectedEmbedder().runStoriesAsPaths( createStoryPaths() );
    }

    private List<String> createStoryPaths() {
        String storyLocation = CodeLocations.codeLocationFromPath( "src/test/resources" ).getFile();
        StoryFinder finder = new StoryFinder();
        return finder.findPaths( storyLocation,
                                 Arrays.asList( "**/trader*.story" ),
                                 Arrays.asList( "" ),
                                 "file:" + storyLocation );
    }
}
