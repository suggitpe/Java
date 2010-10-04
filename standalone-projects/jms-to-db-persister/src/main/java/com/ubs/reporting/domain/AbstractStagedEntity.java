/*
 * AbstractStagedEntity.java created on 28 Sep 2010 19:22:53 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.reporting.domain.support.StagedAuditInfo;
import com.ubs.reporting.domain.support.StagedAuditable;

/**
 * Abstract persistable domain class for all Staged Entities. The key reason for this is that this class
 * allows us to do things to all classes, such as timestamps etc.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@MappedSuperclass
public abstract class AbstractStagedEntity implements StagedAuditable {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( AbstractStagedEntity.class );

    @Embedded
    private StagedAuditInfo stagedAuditInfo = new StagedAuditInfo();

    /**
     * Returns the value of stagedAuditInfo.
     * 
     * @return Returns the stagedAuditInfo.
     */
    @Override
    public StagedAuditInfo getStagedAuditInfo() {
        return stagedAuditInfo;
    }

    /**
     * Sets the stagedAuditInfo field to the specified value.
     * 
     * @param aStagedAuditInfo
     *            The stagedAuditInfo to set.
     */
    public void setStagedAuditInfo( StagedAuditInfo aStagedAuditInfo ) {
        stagedAuditInfo = aStagedAuditInfo;
    }

}
