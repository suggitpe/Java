/*
 * TimestampTestHelper.java created on 24 Dec 2010 16:00:19 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for the timestamp tests.
 * 
 * @author suggitpe
 * @version 1.0 24 Dec 2010
 */
final class TimestampTestHelper {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TimestampTestHelper.class );

    public static TimestampedEntity createTimestampEntity() {
        TimestampedEntity entity = new TimestampedEntity( "deleteMe",
                                                          Calendar.getInstance().getTime(),
                                                          Integer.valueOf( 9876 ) );
        TimestampedChildEntity child = new TimestampedChildEntity();
        child.setChildInteger( Integer.valueOf( 9999 ) );
        child.setChildString( "This is a child string" );
        entity.addChild( child );
        return entity;
    }
}
