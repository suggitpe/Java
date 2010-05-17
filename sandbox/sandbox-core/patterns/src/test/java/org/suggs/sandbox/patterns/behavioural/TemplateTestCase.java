/*
 * TemplateTestCase.java created on 3 Sep 2007 07:29:37 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.behavioural.template.untemplatized.CaffeineBeverage;
import org.suggs.sandbox.patterns.behavioural.template.untemplatized.Coffee;
import org.suggs.sandbox.patterns.behavioural.template.untemplatized.Tea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Test case for the template pattern tests
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class TemplateTestCase extends AbstractPatternTestCase {

    private static final Log LOG = LogFactory.getLog( TemplateTestCase.class );

    @Test
    public void testNonTemplatized() {
        LOG.debug( "**** Calling prepareRecipe for coffee" );

        CaffeineBeverage coffee = new Coffee();
        coffee.prepareReceipe();

        LOG.debug( "**** Calling prepareRecipe for tea" );
        CaffeineBeverage tea = new Tea();
        tea.prepareReceipe();
    }

    @Test
    public void testTemplatized() {
        LOG.debug( "**** Calling prepareRecipe for coffee" );
        org.suggs.sandbox.patterns.behavioural.template.templatized.CaffeineBeverage coffee = new org.suggs.sandbox.patterns.behavioural.template.templatized.Coffee();
        coffee.prepareReceipe();

        LOG.debug( "**** Calling prepareRecipe for tea" );
        org.suggs.sandbox.patterns.behavioural.template.templatized.CaffeineBeverage tea = new org.suggs.sandbox.patterns.behavioural.template.templatized.Tea();
        tea.prepareReceipe();
    }
}
