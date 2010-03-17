/*
 * FooBodyTag.java created on 15 Nov 2007 07:34:17 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.tags.test;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Tag to shoe how to pass thre tag body through to the JSP
 * 
 * @author suggitpe
 * @version 1.0 15 Nov 2007
 */
public class FooBodyTag extends SimpleTagSupport
{

    /**
     * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
     */
    @Override
    public void doTag() throws JspException, IOException
    {
        // this effectively invokes the context.getOut with the output
        getJspBody().invoke( null );
    }
}
