package org.suggs.sandbox_webapps.jbehave.stories;

import java.util.Arrays;
import java.util.List;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that will control the basic set of tests using Spring to allow for the test configuration.
 * <p/>
 * User: suggitpe
 * Date: 13/05/11
 * Time: 08:11
 */

@RunWith(SpringAnnotatedEmbedderRunner.class)
@Configure(storyLoader = LoadFromURL.class)
@UsingEmbedder(embedder = Embedder.class)
@UsingSpring(resources = { "classpath:xml/jbehave-configuration.xml" })
public class BasicNavigationOfWebApp extends InjectableEmbedder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BasicNavigationOfWebApp.class );

    @Test
    @Override
    public void run() throws Throwable {
        List<String> paths = createStoryPaths();
        if ( paths == null || paths.isEmpty() ) {
            throw new IllegalStateException( "No story paths found for test" );
        }
        LOG.info( "Running [" + this.getClass().getSimpleName() + "] with stories [" + paths + "]" );
        injectedEmbedder().runStoriesAsPaths( paths );
    }

    private List<String> createStoryPaths() {
        String storyLocation = CodeLocations.codeLocationFromClass( this.getClass() ).getFile();
        LOG.info( "Running stories from [" + storyLocation + "]" );
        StoryFinder finder = new StoryFinder();
        return finder.findPaths( storyLocation,
                Arrays.asList( "**/*.story" ),
                Arrays.asList( "" ),
                "file:" + storyLocation );
    }
}
