/*
 * LegalEntity.java created on 28 Sep 2010 17:34:57 by suggitpe for project masterfiles-receiver
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
 * Legal Entity Entity
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_LEGAL_ENTITY")
public class LegalEntity extends AbstractLegalEntity {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LegalEntity.class );

    @Column(name = "COUNTRY_DOMICILE")
    private String countryDomicile;

    @Column(name = "COUNTRY_INCORPORATION")
    private String countryIncorporation;

    @Column(name = "INDUSTRY_CLASS_CODE")
    private String classCode;

    @Column(name = "INDUSTRY_CLASS_TYPE")
    private String classType;

    @Column(name = "LE_STATUS_UPDT_DATE")
    private Date legalEntityStatusUpdateDate;

    @Column(name = "LE_STATUS")
    private String legalEntityStatus;

    @Column(name = "CLIENT_CLASS")
    private String clientClass;

    @Column(name = "HASCOLLATERAL")
    private String hasCollateral;

    @Column(name = "COUNTRY_OF_RISK")
    private String countryOfRisk;

    @Column(name = "IS_CONFIDENTIAL")
    private String isConfidential;

    @Column(name = "INTL_RATING_CODE")
    private String internationalRatingCode;

    @Column(name = "INTL_RATING_REVIEW_DATE")
    private Date internationalRatingReviewDate;

    @Column(name = "PORTFOLIO_SEGMENT")
    private String portfolioSegment;

    @Column(name = "RATING_APPROACH")
    private String ratingApproach;

    @Column(name = "BOE_CODE")
    private String boeCode;

    @Column(name = "BOE_TYPE")
    private String boeType;

    @Column(name = "CA_CODE")
    private String caCode;

    @Column(name = "CA_TYPE")
    private String caType;

    @Column(name = "CT_CODE")
    private String ctCode;

    @Column(name = "CT_TYPE")
    private String ctType;

    @Column(name = "LEGACY_ID")
    private String legacyId;

}
