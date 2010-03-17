/*
 * FormatDateTag.java created on 13 Nov 2007 07:22:32 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.tags.attributes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Formats the date based on the format passed in as an attribute.
 * 
 * @author suggitpe
 * @version 1.0 13 Nov 2007
 */
public class FormatDateTag extends SimpleTagSupport
{

    private String format_;

    /**
     * Sets the format field to the specified value.
     * 
     * @param format
     *            The format to set.
     */
    public void setFormat( String format )
    {
        format_ = format;
    }

    /**
     * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
     */
    @Override
    public void doTag() throws JspException, IOException
    {
        JspWriter out = getJspContext().getOut();
        if ( format_ != null )
        {
            SimpleDateFormat f = new SimpleDateFormat( format_ );
            out.print( f.format( new Date() ) );
        }
        else
        {
            out.print( new Date().toString() );
        }
    }

}
