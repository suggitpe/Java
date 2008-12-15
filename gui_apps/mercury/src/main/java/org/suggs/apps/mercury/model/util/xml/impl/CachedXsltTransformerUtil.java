/*
 * CachedXsltTransformerHelper.java created on 8 Dec 2008 19:30:26 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml.impl;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Templates;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is an extension of the parent XSLT helper in that it
 * overrides the getTemplates method to implement a cache.
 * 
 * @author suggitpe
 * @version 1.0 8 Dec 2008
 */
public class CachedXsltTransformerUtil extends XsltTransformerUtil
{

    private static final Log LOG = LogFactory.getLog( CachedXsltTransformerUtil.class );

    private Map<String, Templates> mTemplateCache_ = new HashMap<String, Templates>();

    /**
     * @see org.suggs.apps.mercury.model.util.xml.impl.XsltTransformerUtil#getTemplates(java.lang.String)
     */
    @Override
    protected Templates getTemplates( String aXsltName ) throws MercuryUtilityException
    {
        if ( !mTemplateCache_.containsKey( aXsltName ) )
        {
            if ( LOG.isDebugEnabled() )
            {
                LOG.debug( "XSLT [" + aXsltName
                           + "] does not exit in the template cache, creating new one from parent" );
            }
            mTemplateCache_.put( aXsltName, super.getTemplates( aXsltName ) );
        }

        return mTemplateCache_.get( aXsltName );
    }

}
