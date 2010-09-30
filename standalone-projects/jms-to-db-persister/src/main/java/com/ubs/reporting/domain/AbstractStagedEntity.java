/*
 * AbstractStagedEntity.java created on 28 Sep 2010 19:22:53 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract persistable domain class for all Staged Entities.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@MappedSuperclass
public abstract class AbstractStagedEntity implements Serializable {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( AbstractStagedEntity.class );
    private static final long serialVersionUID = 7750670266771915516L;

    @Column(name = "STG_EFFECTIVE_DATE")
    private Date stagingEffectiveDate;

    @Column(name = "STG_LOAD_DATE")
    private Date stagingLoadDate;

    @Column(name = "STG_LOAD_ID")
    private Integer stagingLoadId;

    @Column(name = "STG_JOB_RUN_ID")
    private Integer stagingJobRunId;
}
