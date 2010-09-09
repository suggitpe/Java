/*
 * TraderStoriesFromURL.java created on 7 Sep 2010 19:39:52 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.trader.stories;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;

/**
 * Modification to the classpath located story finder in that it uses a URL to locate the stories.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */
public class TraderStoriesFromURL extends TraderStoriesFromClasspath {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TraderStoriesFromURL.class );

    @Override
    public Configuration configuration() {
        Configuration config = super.configuration();
        config.useStoryLoader( new LoadFromURL() );
        return config;
    }

    @Override
    public List<String> storyPaths() {
        String codeLocation = CodeLocations.codeLocationFromClass( this.getClass() ).getFile();
        StoryFinder finder = new StoryFinder();
        return finder.findPaths( codeLocation,
                                 Arrays.asList( "**/trader*.story" ),
                                 Arrays.asList( "" ),
                                 "file:" + codeLocation );

    }
}
