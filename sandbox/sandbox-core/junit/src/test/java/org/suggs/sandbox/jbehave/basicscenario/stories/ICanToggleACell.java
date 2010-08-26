/*
 * ICanToggleACell.java created on 24 Aug 2010 19:23:27 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.jbehave.basicscenario.stories;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.InstanceStepsFactory;

/**
 * TODO Write javadoc for ICanToggleACell
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2010
 */
public class ICanToggleACell extends JUnitStory {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( ICanToggleACell.class );

    /**
     * Constructs a new instance.
     */
    public ICanToggleACell() {
        addSteps( new InstanceStepsFactory( new Configuration() {}, new GridSteps() ).createCandidateSteps() );
    }
}
