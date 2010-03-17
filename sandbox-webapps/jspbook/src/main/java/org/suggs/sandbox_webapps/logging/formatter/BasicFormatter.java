/*
 * BasicFormatter.java created on 1 Nov 2007 18:24:12 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.logging.formatter;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * This is a basic formatter that can be used by Logger
 * 
 * @author suggitpe
 * @version 1.0 1 Nov 2007
 */
public class BasicFormatter extends Formatter
{

    /**
     * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
     */
    @Override
    public String format( LogRecord record )
    {
        Date date = new Date( record.getMillis() );
        String lvl = record.getLevel().getName();
        StringBuffer buff = new StringBuffer( "[" ).append( lvl )
            .append( " " )
            .append( date.toString() )
            .append( "]" )
            .append( record.getMessage() )
            .append( "\n" );
        Throwable t = record.getThrown();
        if ( t != null )
        {
            buff.append( "\nException thrown:\n" ).append( t.toString() );
        }
        return buff.toString();

    }

}
