/*
 * AbstractLegalEntity.java created on 28 Sep 2010 19:24:27 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for AbstractLegalEntity
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@MappedSuperclass
public class AbstractLegalEntity extends AbstractStagedEntity {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( AbstractLegalEntity.class );

    @Column(name = "LE_DOMAIN")
    private String legalEntityDomain;

    @Column(name = "LE_VERSION")
    private Integer legalEntityVersion;

    @Column(name = "LE_ID")
    private Integer legalEntityId;

    @Column(name = "LEGAL_NAME")
    private String legalName;
}
