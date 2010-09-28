/*
 * LegalEntityCrossReference.java created on 28 Sep 2010 17:34:26 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Legal Entity Cross Reference Entity
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_LE_CROSS_REF")
public class LegalEntityCrossReference extends AbstractLegalEntity {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LegalEntityCrossReference.class );

    @Column(name = "ALT_IDENTIFIER_ID")
    private String altIdId;

    @Column(name = "ALT_IDENTIFIER_DOMAIN")
    private String altIdDomain;

    @Column(name = "ALT_DOMAIN_CODE")
    private String altIdDomainCode;

    @Column(name = "ALT_IDENTIFIER_STATUS_DATE")
    private Date altIdStatusDate;

    @Column(name = "ALT_IDENTIFIER_STATUS")
    private String altIdStatus;

}
