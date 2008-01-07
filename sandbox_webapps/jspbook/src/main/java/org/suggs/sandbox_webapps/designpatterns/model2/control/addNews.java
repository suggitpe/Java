/*
 * addNews.java created on 20 Dec 2007 18:24:26 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.designpatterns.model2.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Class to manage the addition of news to the repository
 * 
 * @author suggitpe
 * @version 1.0 20 Dec 2007
 */
public class addNews extends AbstractControl
{

    /**
     * @see org.suggs.sandbox_webapps.designpatterns.model2.IControl#doLogic(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public boolean doLogic( HttpServletRequest request, HttpServletResponse response )
                    throws ServletException, IOException
    {
        ServletContext ctx = request.getSession().getServletContext();

        try
        {
            String title = request.getParameter( "title" );
            String link = request.getParameter( "link" );
            String story = request.getParameter( "story" );

            if ( title != null && !title.equals( "" ) && link != null && !link.equals( "" )
                 && story != null && !story.trim().equals( "" ) )
            {
                String dir = ctx.getRealPath( URL_BASE );
                File file = new File( dir + "/" + NEWS_FILE );

                // build or read the xml document from the file
                DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = fact.newDocumentBuilder();
                Document doc = null;

                if ( file.exists() )
                {
                    doc = builder.parse( file );
                }
                else
                {
                    doc = builder.newDocument();
                    Element root = doc.createElement( "news" );
                    doc.appendChild( root );
                }

                // build the new xml node
                Element news = doc.createElement( "story" );
                news.setAttribute( "title", title );
                news.setAttribute( "link", link );
                news.setAttribute( "story", story );
                doc.getDocumentElement().appendChild( news );

                // this is where the new file gets created
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer trans = tf.newTransformer();
                DOMSource src = new DOMSource( doc );
                StreamResult res = new StreamResult( new FileOutputStream( file ) );
                trans.transform( src, res );

                ctx.getRequestDispatcher( URL_BASE + "/addNewsThanks.jsp" ).forward( request,
                                                                                     response );
            }
            else
            {
                Properties an = new Properties();
                an.setProperty( "title", ( title == null ? "" : title ) );
                an.setProperty( "link", ( link == null ? "" : link ) );
                an.setProperty( "story", ( story == null ? "" : story ) );
                request.setAttribute( "form", an );
            }
        }
        catch ( TransformerException te )
        {
            throw new IOException( te.getMessage() );
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
