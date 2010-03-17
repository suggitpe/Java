/*
 * AuditRequestWrapper.java created on 26 Nov 2007 19:29:48 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.wrapper;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Auditing wrapper that wraps requests and in doing so audits them
 * 
 * @author suggitpe
 * @version 1.0 26 Nov 2007
 */
public class AuditRequestWrapper extends HttpServletRequestWrapper
{

    private Logger logger_;

    /**
     * Constructs a new instance.
     * 
     * @param aLogger
     *            the logger for the wrapper
     * @param aRequest
     *            the http servlet request to wrap
     */
    public AuditRequestWrapper( Logger aLogger, HttpServletRequest aRequest )
    {
        super( aRequest );
        logger_ = aLogger;
    }

    /**
     * @see javax.servlet.ServletRequestWrapper#getContentType()
     */
    @Override
    public String getContentType()
    {
        String type = super.getContentType();
        logger_.fine( "ContentTYpe=[" + type + "]" );
        return type;
    }

    /**
     * @see javax.servlet.ServletRequestWrapper#getContentLength()
     */
    @Override
    public int getContentLength()
    {
        int len = super.getContentLength();
        logger_.fine( "ContentLength=[" + len + "]" );
        return len;
    }

    /**
     * @see javax.servlet.http.HttpServletRequestWrapper#getDateHeader(java.lang.String)
     */
    @Override
    public long getDateHeader( String s )
    {
        long date = super.getDateHeader( s );
        logger_.fine( "DateHeader<" + s + ">=[" + date + "]" );
        return date;
    }

    /**
     * @see javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.String)
     */
    @Override
    public String getHeader( String s )
    {
        String header = super.getHeader( s );
        logger_.fine( "Header<" + s + ">=[" + header + "]" );
        return header;
    }

    /**
     * @see javax.servlet.http.HttpServletRequestWrapper#getIntHeader(java.lang.String)
     */
    @Override
    public int getIntHeader( String s )
    {
        int header = super.getIntHeader( s );
        logger_.fine( "IntHeader<" + s + ">=[" + header + "]" );
        return header;
    }

    /**
     * @see javax.servlet.http.HttpServletRequestWrapper#getQueryString()
     */
    @Override
    public String getQueryString()
    {
        String s = super.getQueryString();
        logger_.fine( "QueryString=[" + s + "]" );
        return s;
    }
}
