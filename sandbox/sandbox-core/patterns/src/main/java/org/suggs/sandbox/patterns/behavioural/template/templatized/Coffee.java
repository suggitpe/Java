/*
 * Coffee.java created on 3 Sep 2007 15:23:41 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.template.templatized;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate templatized coffee.
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class Coffee extends CaffeineBeverage {

    private static final Log LOG = LogFactory.getLog( Coffee.class );

    /**
     * @see org.suggs.sandbox.patterns.behavioural.template.templatized.CaffeineBeverage#brew()
     */
    @Override
    protected void brew() {
        LOG.debug( "Brewing coffee grinds" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.template.templatized.CaffeineBeverage#addCondiments()
     */
    @Override
    protected void addCondiments() {
        LOG.debug( "Add in milk and sugar to the coffee" );
    }

}
