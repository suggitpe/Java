/*
 * AbstractControl.java created on 21 Dec 2007 07:37:15 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.designpatterns.model2.control;

import org.suggs.sandbox_webapps.designpatterns.model2.IControl;

/**
 * Abstract class for the IControl interface
 * 
 * @author suggitpe
 * @version 1.0 21 Dec 2007
 */
public abstract class AbstractControl implements IControl
{

    protected final String URL_BASE = "/staticcontent/jsp/designpatterns/model2";

    protected final String NEWS_FILE = "news.xml";

}
