/*
 * TraderStoriesUsingSpringAnnotatedEmbedder.java created on 7 Sep 2010 20:10:21 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.stories;

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
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.spring.SpringAnnotatedEmbedderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Spring injected test case for the Trader scenario.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */

@RunWith(SpringAnnotatedEmbedderRunner.class)
@Configure()
@UsingEmbedder(embedder = Embedder.class)
@UsingSpring(resources = { "classpath:xml/trader/steps.xml", "classpath:xml/trader/configuration.xml" })
public class TraderStoriesUsingSpringAnnotatedEmbedder extends InjectableEmbedder {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TraderStoriesUsingSpringAnnotatedEmbedder.class );

    @Test
    @Override
    public void run() throws Throwable {
        injectedEmbedder().runStoriesAsPaths( storyPaths() );
    }

    protected List<String> storyPaths() {
        StoryFinder finder = new StoryFinder();
        String codeLocation = CodeLocations.codeLocationFromClass( this.getClass() ).getFile();
        return finder.findPaths( codeLocation, Arrays.asList( "**/trader*.story" ), Arrays.asList( "" ) );
    }

}
