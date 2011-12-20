/*
 * TraverseStateMachine.java created on 7 Sep 2010 18:30:46 by suggitpe for project state-machine-lib
 * 
 */
package org.suggs.libs.statemachine.jbehave.spring_stories;

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
     * This is the core executable method in the process
     * @see org.jbehave.core.Embeddable#run()
     */
    @Test
    @Override
    public void run() throws Exception {
        List<String> paths = createStoryPaths();
        if( paths == null || paths.isEmpty() ){
            throw new IllegalStateException( "No story paths found for state machine" );
        }
        LOG.info( "Running [" + this.getClass().getSimpleName() + "] with spring_stories [" + paths + "]" );
        injectedEmbedder().runStoriesAsPaths( paths );
    }

    private List<String> createStoryPaths() {
        String storyLocation = CodeLocations.codeLocationFromClass( this.getClass() ).getFile();
        LOG.info( "Running spring_stories from [" + storyLocation + "]" );
        StoryFinder finder = new StoryFinder();
        return finder.findPaths( storyLocation,
                Arrays.asList( "**/*.story" ),
                Arrays.asList( "" ),
                "file:" + storyLocation );
    }
}
