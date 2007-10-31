/*
 * FooApplet.java created on 29 Oct 2007 19:12:29 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.applets.example;

import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JLabel;

/**
 * The most simple applet in the world and is simply to highlight how
 * to embed one into an application.
 * 
 * @author suggitpe
 * @version 1.0 29 Oct 2007
 */
public class FooApplet extends JApplet
{

    /**
     * @see java.applet.Applet#init()
     */
    @Override
    public void init()
    {
        JLabel l = new JLabel( "This is an example of an Applet" );
        l.setHorizontalAlignment( JLabel.CENTER );
        getContentPane().add( l, BorderLayout.CENTER );
    }

}
