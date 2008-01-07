/*
 * index.java created on 20 Dec 2007 07:40:14 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.designpatterns.model2.control;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Control to set up for the index page.
 * 
 * @author suggitpe
 * @version 1.0 20 Dec 2007
 */
public class index extends AbstractControl
{

    /**
     * @see org.suggs.sandbox_webapps.designpatterns.model2.IControl#doLogic(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public boolean doLogic( HttpServletRequest request, HttpServletResponse response )
                    throws ServletException, IOException
    {
        try
        {
            ServletContext ctx = request.getSession().getServletContext();

            String dir = ctx.getRealPath( URL_BASE );
            File file = new File( dir + "/" + NEWS_FILE );
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fact.newDocumentBuilder();
            Document doc = null;

            if ( file.exists() )
            {
                doc = builder.parse( file );
            }

            if ( doc != null )
            {
                NodeList nodes = doc.getElementsByTagName( "story" );
                Properties[] ads = new Properties[nodes.getLength()];
                for ( int i = 0; i < nodes.getLength(); ++i )
                {
                    Element e = (Element) nodes.item( i );
                    ads[i] = new Properties();
                    ads[i].setProperty( "link", e.getAttribute( "link" ) );
                    ads[i].setProperty( "title", e.getAttribute( "title" ) );
                    ads[i].setProperty( "story", e.getAttribute( "story" ) );
                }
                request.setAttribute( "news", ads );
            }
        }
        catch ( SAXException se )
        {
            throw new ServletException( se );
        }
        catch ( ParserConfigurationException pce )
        {
            throw new ServletException( pce );
        }

        return true;
    }
}
