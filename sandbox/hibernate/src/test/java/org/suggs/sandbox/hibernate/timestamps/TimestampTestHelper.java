/*
 * TimestampTestHelper.java created on 24 Dec 2010 16:00:19 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

final class TimestampTestHelper {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(TimestampTestHelper.class);

    public static TimestampedEntity createTimestampEntity() {
        TimestampedEntity entity = new TimestampedEntity("deleteMe",
                Calendar.getInstance().getTime(),
                9876);
        TimestampedChildEntity child = new TimestampedChildEntity();
        child.setChildInteger(9999);
        child.setChildString("This is a child string");
        entity.addChild(child);
        return entity;
    }
}
