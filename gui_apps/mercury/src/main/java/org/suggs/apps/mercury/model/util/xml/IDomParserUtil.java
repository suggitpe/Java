/*
 * IDomParserUtil.java created on 9 Dec 2008 08:57:38 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;

import org.w3c.dom.Document;

/**
 * This util is used for all DOM parser based activities.
 * 
 * @author suggitpe
 * @version 1.0 9 Dec 2008
 */
public interface IDomParserUtil
{

    /**
     * @param aFilename
     * @param aSchemaLocation
     * @return
     * @throws MercuryUtilityException
     */
    Document createDocFromXmlFile( String aFilename, String aSchemaLocation )
                    throws MercuryUtilityException;

}
