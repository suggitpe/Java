/*
 * TimestampAuditable.java created on 31 Mar 2010 07:29:03 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;


/**
 * Provides intent that a class will provide timestamp audit info. This is used within the hibernate
 * interceptor to update the class with good timestamp autid data.
 */
public interface TimestampAuditable {

    TimestampAuditInfo getTimestampAuditInfo();

}
