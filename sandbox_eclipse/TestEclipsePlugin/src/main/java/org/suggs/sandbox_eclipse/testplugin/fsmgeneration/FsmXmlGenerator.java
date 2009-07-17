/*
 * FsmXmlGenerator.java created on 3 Apr 2008 06:33:55 by suggitpe for project Sandbox - TestEclipsePlugin
 * 
 */
package org.suggs.sandbox_eclipse.testplugin.fsmgeneration;

import org.eclipse.core.resources.IFile;

/**
 * This class will
 * 
 * @author suggitpe
 * @version 1.0 3 Apr 2008
 */
public class FsmXmlGenerator
{

    private IFile mModel_;

    /**
     * Constructs a new instance.
     * 
     * @param aModel
     */
    public FsmXmlGenerator( IFile aModel )
    {
        mModel_ = aModel;
    }

    /**
     * This method will do the underlying FSM Spring XML generation
     * 
     * @param aKey
     *            the key from which to generate the XML
     * @return the FSM Spring XML
     */
    public String generateXml( String aKey )
    {
        mModel_.getClass();
        StringBuffer buff = new StringBuffer( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" ).append( "<beans>\n" )
            .append( "\t<bean id=\"duffBean\" class=\"org.suggs.sandbox.scribbler.duffBeanClass\">\n" )
            .append( "\t\t<property name=\"sampleProperty1\" value=\"sampleName\">\n" )
            .append( "\t</bean>\n" )
            .append( "</beans>\n" );
        return buff.toString();
    }
}
