/*
 * AbstractInjectableStoryEmbedder.java created on 16 Sep 2010 19:32:21 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.test.jbehave.support;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.test.jbehave.support.AbstractInjectableStoryEmbedder.MyDateConverter;
import org.suggs.sandbox.test.jbehave.support.AbstractInjectableStoryEmbedder.MyRegexPrefixCapturingPatternParser;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Abstract class that will maintain all of the basic configuration for an injectable embedder.
 *
 * @author suggitpe
 * @version 1.0 16 Sep 2010
 */
@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyLoader = LoadFromURL.class,
        stepPatternParser = MyRegexPrefixCapturingPatternParser.class,
        storyReporterBuilder = SuggsStoryReporterBuilder.class,
        parameterConverters = {MyDateConverter.class}
)
@UsingEmbedder(embedder = Embedder.class)
public abstract class AbstractInjectableStoryEmbedder extends InjectableEmbedder {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(AbstractInjectableStoryEmbedder.class);

    private static final String STORY_LOCATION = AbstractInjectableStoryEmbedder.class.getClassLoader().getResource("").getFile();

    @Test
    @Override
    public void run() {

        StoryFinder finder = new StoryFinder();
        LOG.debug("Finding stories in [" + STORY_LOCATION + "]");
        injectedEmbedder().runStoriesAsPaths(finder.findPaths(STORY_LOCATION,
                Arrays.asList(doGetStoryRegex()),
                Arrays.asList(""),
                "file:" + STORY_LOCATION));
    }

    protected abstract String doGetStoryRegex();

    public static class MyRegexPrefixCapturingPatternParser extends RegexPrefixCapturingPatternParser {

        public MyRegexPrefixCapturingPatternParser() {
            super("$");
        }
    }

    public static class MyDateConverter extends DateConverter {

        public MyDateConverter() {
            super(new SimpleDateFormat("dd-MM-yyyy"));
        }
    }
}
