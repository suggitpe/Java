/*
 * IncludeFormatCss.java created on 12 Nov 2007 20:06:41 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.tags.format;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * JSP custom tag that will provide a link to the format stylesheet.
 * 
 * @author suggitpe
 * @version 1.0 12 Nov 2007
 */
public class IncludeFormatCss extends SimpleTagSupport
{

    /**
     * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
     */
    @Override
    public void doTag() throws JspException, IOException
    {
        JspWriter out = getJspContext().getOut();
        out.print( "<link rel=\"stylesheet\" type=\"text/css\" href=\"/format.css\" />" );
    }

}
