/*
 * LegalEntityContraEntity.java created on 28 Sep 2010 17:36:45 by suggitpe for project masterfiles-receiver
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
 * Persistable domain class for a Legal Entity Contra Entity.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_LE_CONTR_ENTITY")
public class LegalEntityContraEntity extends AbstractLegalEntity {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LegalEntityContraEntity.class );
    private static final long serialVersionUID = -8628034557666992483L;

    @Column(name = "CONTR_ENTITY_ID")
    private Integer contraEntityId;

    @Column(name = "CONTR_ENTITY_DOMAIN")
    private String contraEntityDomain;

    @Column(name = "CONTR_ENTITY_NAME")
    private String contraEntityName;

    @Column(name = "CONTR_ENTITY_UPDT_DATE")
    private Date contraEntityUpdateDate;

    @Column(name = "CONTR_ENTITY_STATUS")
    private String contraEntityStatus;

}
