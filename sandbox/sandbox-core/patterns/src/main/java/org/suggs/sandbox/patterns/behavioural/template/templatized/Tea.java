/*
 * Tea.java created on 3 Sep 2007 15:23:48 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.template.templatized;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate templatized tea.
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class Tea extends CaffeineBeverage {

    private static final Log LOG = LogFactory.getLog( Tea.class );

    /**
     * @see org.suggs.sandbox.patterns.behavioural.template.templatized.CaffeineBeverage#brew()
     */
    @Override
    protected void brew() {
        LOG.debug( "Steeping tea bag" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.template.templatized.CaffeineBeverage#addCondiments()
     */
    @Override
    protected void addCondiments() {
        LOG.debug( "Adding lemon" );
    }

}
