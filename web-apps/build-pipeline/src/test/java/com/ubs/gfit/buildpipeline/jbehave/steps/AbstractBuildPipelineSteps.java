package com.ubs.gfit.buildpipeline.jbehave.steps;

import org.jbehave.core.annotations.AfterStories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.dsl.DSL;
import com.ubs.gfit.buildpipeline.pages.JbehavePages;

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

    private JbehavePages pages;

    public AbstractBuildPipelineSteps( JbehavePages aPages ) {
        //super( aPages );
        pages = aPages;
    }

    protected JbehavePages pages() {
        return pages;
    }

    @AfterStories
    public void closeBrowser() {
        pages().closeBrowser();
    }


}
