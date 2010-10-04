/*
 * StagedAuditable.java created on 4 Oct 2010 07:14:23 by suggitpe for project jms-to-db-persister
 * 
 */
package com.ubs.reporting.domain.support;

/**
 * Provides intent that a class will provide staging audit info. This will be used by the framework to
 * allocate certain attributes to the class as it persisted.
 * 
 * @author suggitpe
 * @version 1.0 4 Oct 2010
 */
public interface StagedAuditable {

    /**
     * Getter for the staged data.
     */
    StagedAuditInfo getStagedAuditInfo();

}
