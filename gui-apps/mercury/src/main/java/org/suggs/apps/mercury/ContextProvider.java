/*
 * ContextProvider.java created on 16 Sep 2008 18:18:48 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class is a single implementation of the Mercury application
 * context. The point of this class is o encapsulate the access to the
 * beans behind a singleton interface.
 * 
 * @author suggitpe
 * @version 1.0 16 Sep 2008
 */
public class ContextProvider
{

    private ApplicationContext mContext_;
    private static ContextProvider mInstance_;

    static
    {
        mInstance_ = new ContextProvider();
    }

    /**
     * Constructs a new instance.
     */
    private ContextProvider()
    {
        super();
        mContext_ = new ClassPathXmlApplicationContext( "xml/mercury.xml" );
    }

    /**
     * Getter for the singleton instance
     * 
     * @return the singleton instance
     */
    public static ContextProvider instance()
    {
        return mInstance_;
    }

    /**
     * Accessor to the underlying beans hidden behind the application
     * context
     * 
     * @param beanName
     *            the name of the bean to get
     * @return the Object that the beanName references in the spring
     *         context
     */
    public Object getBean( String beanName )
    {
        return mContext_.getBean( beanName );
    }

}
