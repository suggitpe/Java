package org.suggs.sandbox.hibernate.basicentity;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe Date: 01/04/11 Time: 14:46
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "BASIC_RELATIONSHIP_ENTITY_OTH")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "BASIC_RELATION_OTH_ENTITY_SQ")
public class BasicRelationshipOtherEntity extends EntityBase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BasicRelationshipOtherEntity.class );

    @Column(name = "string_data", length = 64)
    private String stringData;

    public String getStringData() {
        return stringData;
    }

    public void setStringData( String aStringData ) {
        stringData = aStringData;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        BasicRelationshipOtherEntity that = ( BasicRelationshipOtherEntity ) o;

        if ( stringData != null ? !stringData.equals( that.stringData ) : that.stringData != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stringData != null ? stringData.hashCode() : 0;
    }
}
