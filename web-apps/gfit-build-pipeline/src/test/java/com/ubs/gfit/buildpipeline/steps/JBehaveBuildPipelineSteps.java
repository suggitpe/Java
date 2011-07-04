package com.ubs.gfit.buildpipeline.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.pages.Pages;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 19:09
 */

public final class JBehaveBuildPipelineSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( JBehaveBuildPipelineSteps.class );

    private Pages pages;

    public JBehaveBuildPipelineSteps( Pages aPages ){
        pages = aPages;
    }
}
