package org.suggs.webapps.buildpipeline.jbehave.steps;

import org.suggs.webapps.buildpipeline.dsl.DSL;
import org.suggs.webapps.buildpipeline.pages.impl.JbehavePages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for all JBehave steps implementations
 * <p/>
 * User: suggitpe
 * Date: 17/08/11
 * Time: 19:28
 */

public abstract class AbstractBuildPipelineSteps extends DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractBuildPipelineSteps.class );

    public AbstractBuildPipelineSteps( JbehavePages aPages ) {
        super( aPages );
    }


}
