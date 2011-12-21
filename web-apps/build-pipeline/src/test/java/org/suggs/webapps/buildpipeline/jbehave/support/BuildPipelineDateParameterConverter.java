package org.suggs.webapps.buildpipeline.jbehave.support;

import java.text.SimpleDateFormat;

import org.jbehave.core.steps.ParameterConverters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class representation of the date converter.  This class exists so that we can annotate the JBehave tests.
 * <p/>
 * User: suggitpe Date: 12/21/11 Time: 7:23 AM
 */

public class BuildPipelineDateParameterConverter extends ParameterConverters.DateConverter {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BuildPipelineDateParameterConverter.class );

    public BuildPipelineDateParameterConverter() {
        super( new SimpleDateFormat( "dd-MM-yyyy" ) );
    }

}
