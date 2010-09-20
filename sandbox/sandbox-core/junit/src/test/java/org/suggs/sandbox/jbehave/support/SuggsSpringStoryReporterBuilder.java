/*
 * SuggsSpringStoryReporterBuilder.java created on 17 Sep 2010 20:03:41 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.configuration.spring.SpringStoryReporterBuilder;
import org.jbehave.core.reporters.FilePrintStreamFactory.FilePathResolver;

/**
 * I should notr have to do this<br/>
 * TODO: get jbehave guys to strip this out
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2010
 */
public class SuggsSpringStoryReporterBuilder extends SpringStoryReporterBuilder {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( SuggsSpringStoryReporterBuilder.class );

    public void setPathResolver( FilePathResolver pathResolver ) {
        this.withPathResolver( pathResolver );
    }

    public void getPathResolver() {
        this.pathResolver();
    }
}
