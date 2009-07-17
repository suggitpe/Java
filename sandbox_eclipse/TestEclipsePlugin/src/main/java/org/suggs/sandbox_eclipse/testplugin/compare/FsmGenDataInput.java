/*
 * FsmGenDataInput.java created on 14 Apr 2008 18:33:41 by suggitpe for project Sandbox - TestEclipsePlugin
 * 
 */
package org.suggs.sandbox_eclipse.testplugin.compare;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

/**
 * TODO Write javadoc for FsmGenDataInput
 * 
 * @author suggitpe
 * @version 1.0 14 Apr 2008
 */
public class FsmGenDataInput implements ITypedElement, IStreamContentAccessor
{

    private String mString_;
    private String mName_;

    /**
     * Constructs a new instance.
     * 
     * @param aString
     * @param aName
     */
    public FsmGenDataInput( String aString, String aName )
    {
        mString_ = aString;
        mName_ = aName;
    }

    /**
     * @see org.eclipse.compare.ITypedElement#getImage()
     */
    public Image getImage()
    {
        return null;
    }

    /**
     * @see org.eclipse.compare.ITypedElement#getName()
     */
    public String getName()
    {
        return mName_;
    }

    /**
     * @see org.eclipse.compare.ITypedElement#getType()
     */
    public String getType()
    {
        return "String";
    }

    /**
     * @see org.eclipse.compare.IStreamContentAccessor#getContents()
     */
    public InputStream getContents() throws CoreException
    {
        return new ByteArrayInputStream( mString_.getBytes() );
    }

}
