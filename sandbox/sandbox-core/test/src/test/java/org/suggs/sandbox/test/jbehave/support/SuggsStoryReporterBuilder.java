/*
 * SuggsStoryReporterBuilder.java created on 15 Sep 2010 07:20:31 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.support;

import java.util.Properties;

import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;

import static org.jbehave.core.reporters.StoryReporterBuilder.Format.CONSOLE;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.HTML;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.STATS;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.TXT;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.XML;

/**
 * Class to provide some level of consistency over the format of final reports for JBehave.
 * 
 * @author suggitpe
 * @version 1.0 15 Sep 2010
 */
public class SuggsStoryReporterBuilder extends StoryReporterBuilder {

    /**
     * Constructs a new instance.
     */
    public SuggsStoryReporterBuilder() {
        Properties viewResources = new Properties();
        viewResources.put( "decorateNonHtml", "true" );
        this.withPathResolver( new FilePrintStreamFactory.ResolveToSimpleName() );
        this.withFormats( CONSOLE, TXT, HTML, XML, STATS );
        this.withViewResources( viewResources );
        this.withFailureTrace( false );
    }
}
