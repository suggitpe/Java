/*
 * DynamicAttributeTag.java created on 13 Nov 2007 07:42:47 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.tags.attributes;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Class
 * 
 * @author suggitpe
 * @version 1.0 13 Nov 2007
 */
public class DynamicAttributeTag extends SimpleTagSupport implements DynamicAttributes
{

    private Map<Object, Object> map_ = new Hashtable<Object, Object>();

    /**
     * @see javax.servlet.jsp.tagext.DynamicAttributes#setDynamicAttribute(java.lang.String,
     *      java.lang.String, java.lang.Object)
     */
    public void setDynamicAttribute( String uri, String name, Object value ) throws JspException
    {
        map_.put( name, value );
    }

    /**
     * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
     */
    @Override
    public void doTag() throws JspException, IOException
    {
        JspWriter out = getJspContext().getOut();
        Set<Object> keys = map_.keySet();
        out.print( "<ul>" );
        for ( Object o : keys )
        {
            Object value = map_.get( o );
            out.print( "<li>Attribute: Name=[" + o.toString() + "], value=[" + value.toString() + "]</li>" );
        }
        out.print( "</ul>" );
    }
}
