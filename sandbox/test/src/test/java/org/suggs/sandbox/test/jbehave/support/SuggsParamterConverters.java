/*
 * SuggsParamterConverters.java created on 15 Sep 2010 07:31:29 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.test.jbehave.support;

import java.text.SimpleDateFormat;

import org.jbehave.core.steps.ParameterConverters;

/**
 * Default set of parameter converters.
 * 
 * @author suggitpe
 * @version 1.0 15 Sep 2010
 */
public class SuggsParamterConverters extends ParameterConverters {

    /**
     * Constructs a new instance.
     */
    public SuggsParamterConverters() {
        addConverters( new DateConverter( new SimpleDateFormat( "yyyy-MM-dd" ) ) );
    }
}
