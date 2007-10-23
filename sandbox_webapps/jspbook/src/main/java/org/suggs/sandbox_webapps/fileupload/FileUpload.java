/*
 * FileUpload.java created on 19 Oct 2007 07:12:19 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.fileupload;

import org.suggs.sandbox_webapps.support.AbstractBasePostHttpServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Shows how to manage file uploads in a servlet
 * 
 * @author suggitpe
 * @version 1.0 19 Oct 2007
 */
public class FileUpload extends AbstractBasePostHttpServlet
{

    // public static final int MAX_IN_MEM_FILE_SIZE = 10240;
    public static final int MAX_IN_MEM_FILE_SIZE = 0;
    public static final String TEMP_FILE_CACHE = "c:\temp";

    /**
     * @see org.suggs.sandbox_webapps.support.AbstractBasePostHttpServlet#buildReponse(java.io.PrintWriter,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void buildReponse( PrintWriter out, HttpServletRequest request, HttpServletResponse response ) throws IOException,
                    ServletException
    {
        out.println( "File upload success.  Click <a href=\"/jspbook/files\">here</a> to see the uploaded files.<br/>" );

        ServletContext ctx = getServletContext();
        String path = ctx.getRealPath( "/files" );

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold( MAX_IN_MEM_FILE_SIZE );
        // factory.setRepository( new File( TEMP_FILE_CACHE ) );
        factory.setRepository( new File( path ) );

        ServletFileUpload upload = new ServletFileUpload( factory );
        // set to no limit
        upload.setSizeMax( -1 );

        try
        {
            List l = upload.parseRequest( request );
            for ( int i = 0; i < l.size(); ++i )
            {
                FileItem item = (FileItem) l.get( i );
                item.write( new File( path + "/" + item.getName() ) );
            }
        }
        catch ( FileUploadException fue )
        {
            System.err.println( "Error trying to parse the http servlet request for file upload [" + fue.getMessage() + "]" );
            fue.printStackTrace();
        }
        catch ( Exception e )
        {
            System.err.println( "Error trying to write ile item [" + e.getMessage() + "]" );
            e.printStackTrace();
        }
    }

    /**
     * @see org.suggs.sandbox_webapps.support.AbstractBasePostHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "File Upload";
    }

}
