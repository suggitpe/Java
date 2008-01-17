/*
 * IControl.java created on 20 Dec 2007 07:35:23 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.jspbook_site.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the control objects that are called by the main
 * control filter.
 * 
 * @author suggitpe
 * @version 1.0 20 Dec 2007
 */
public interface IControl
{

    public boolean doLogic( HttpServletRequest aRequest, HttpServletResponse aResponse )
                    throws ServletException, IOException;

}
