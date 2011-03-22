/*
 * TraverseStateMachine.java created on 7 Sep 2010 18:30:46 by suggitpe for project state-machine-lib
 * 
 */
package org.suggs.libs.statemachine.jbehave.stories;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Story implementation for the State Machine Tests.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */
@RunWith(SpringAnnotatedEmbedderRunner.class)
@Configure(storyLoader = LoadFromURL.class)
@UsingEmbedder(embedder = Embedder.class)
@UsingSpring(resources = { "classpath:xml/jbehave-configuration.xml", "classpath:xml/state-machine-steps.xml" })
public class TraverseStateMachine extends InjectableEmbedder {

    private static final Logger LOG = LoggerFactory.getLogger( TraverseStateMachine.class );

    /**
     * @see org.jbehave.core.Embeddable#run()
     */
    @Test
    @Override
    public void run() throws Throwable {
        List<String> paths = createStoryPaths();
        LOG.info( "Running [" + this.getClass().getSimpleName() + "] with stories [" + paths + "]" );
        injectedEmbedder().runStoriesAsPaths( paths );
    }

    /**
     * @return
     */
    private List<String> createStoryPaths() {
        String storyLocation = CodeLocations.codeLocationFromPath( "src/test/resources" ).getFile();
        URL codeUrl = CodeLocations.codeLocationFromClass( this.getClass() );
        LOG.info( "Running stories from [" + storyLocation + "]" );
        StoryFinder finder = new StoryFinder();
        return finder.findPaths( storyLocation,
                                 Arrays.asList( "**/*.story" ),
                                 Arrays.asList( "" ),
                                 "file:" + storyLocation );
    }
}
