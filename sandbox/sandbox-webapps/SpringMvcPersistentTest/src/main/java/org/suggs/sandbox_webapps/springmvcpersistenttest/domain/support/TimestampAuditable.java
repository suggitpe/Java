/*
 * TimestampAuditable.java created on 31 Mar 2010 07:29:03 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support;

/**
 * Provides intent that a class will provide timestamp audit info. This is used within the hibernate interceptor to
 * update the class with good timestamp audit data.
 *
 * @author suggitpe
 * @version 1.0 31 Mar 2010
 */
public interface TimestampAuditable {

    /**
     * Provides a not null representation of the objects timestamp audit information.
     *
     * @return the timestamp audit information.
     */
    TimestampAuditInfo getTimestampAuditInfo();

}
