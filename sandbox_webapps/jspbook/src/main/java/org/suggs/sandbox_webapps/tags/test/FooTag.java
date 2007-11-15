/*
 * FooTag.java created on 12 Nov 2007 19:56:23 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.tags.test;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Hello world implementation of a custom jsp tag.
 * 
 * @author suggitpe
 * @version 1.0 12 Nov 2007
 */
public class FooTag extends SimpleTagSupport
{

    /**
     * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
     */
    @Override
    public void doTag() throws JspException, IOException
    {
        JspWriter out = getJspContext().getOut();
        out.print( "FooTag ... hello world" );
    }

}
