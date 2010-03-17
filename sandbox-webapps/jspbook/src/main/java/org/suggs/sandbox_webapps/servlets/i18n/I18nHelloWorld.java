/*
 * I18nHelloWorld.java created on 8 Jan 2008 07:38:56 by suggitpe for project SandBoxWebApps - JSP Book
 */
package org.suggs.sandbox_webapps.servlets.i18n;

import org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * i18n Hello World servlet to show the use of different character
 * sets
 * 
 * @author suggitpe
 * @version 1.0 8 Jan 2008
 */
public class I18nHelloWorld extends AbstractBaseGetHttpServlet
{

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#buildReponse(java.io.PrintWriter,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void buildReponse( PrintWriter out, HttpServletRequest request,
                                 HttpServletResponse response ) throws IOException,
                    ServletException
    {
        // Chinese
        StringBuffer buff = new StringBuffer( "<h3>\u4f60\u597d\u4e16\u754c!</h3>\n" ).append( "<h3>Hello World!</h3>\n" )
            .append( "<h3>Bonjour Monde!</h3>\n" )
            .append( "<h3>Hallo Welt!</h3>\n" )
            .append( "<h3>Ciao Mondo!</h3>\n" )
            // Japanese
            .append( "<h3>\u3053\u3093\u306b\u3061\u306f\u4e16\u754c!</h3>\n" )
            // Korean
            .append( "<h3>\uc5ec\ubcf4\uc138\uc694 \uc138\uacc4!</h3>\n" )
            .append( "<h3>\u00a1Hola Mundo!</h3>\n" );
        out.println( buff.toString() );
    }

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "i18n Hello World";
    }

}
