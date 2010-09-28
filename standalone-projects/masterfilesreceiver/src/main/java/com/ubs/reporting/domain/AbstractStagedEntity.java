/*
 * AbstractStagedEntity.java created on 28 Sep 2010 19:22:53 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for AbstractStagedEntity
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@MappedSuperclass
public class AbstractStagedEntity {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( AbstractStagedEntity.class );

    @Column(name = "STG_EFFECTIVE_DATE")
    private Date stagingEffectiveDate;

    @Column(name = "STG_LOAD_DATE")
    private Date stagingLoadDate;

    @Column(name = "STG_LOAD_ID")
    private Integer stagingLoadId;

    @Column(name = "STG_JOB_RUN_ID")
    private Integer stagingJobRunId;
}
