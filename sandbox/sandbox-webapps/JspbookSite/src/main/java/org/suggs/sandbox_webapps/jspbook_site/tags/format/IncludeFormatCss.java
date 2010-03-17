/*
 * IncludeFormatCss.java created on 16 Jan 2008 08:21:04 by suggitpe for project JspbookSite
 * 
 */
package org.suggs.sandbox_webapps.jspbook_site.tags.format;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * TODO Write javadoc for IncludeFormatCss
 * 
 * @author suggitpe
 * @version 1.0 16 Jan 2008
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
        out.print( "<link rel=\"stylesheet\" type=\"text/css\" href=\"/JspbookSite/format.css\" />" );
    }

}
