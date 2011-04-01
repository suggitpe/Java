package org.suggs.sandbox.hibernate.basicentity;

import org.suggs.sandbox.hibernate.support.EntityBase;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

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
@Table(name = "BASIC_RELATIONSHIP_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "BASIC_RELATION_ENTITY_SQ")
public class BasicRelationshipEntity extends EntityBase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BasicRelationshipEntity.class );


    @Column(name = "string_data", length = 64)
    private String stringData;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Set<BasicRelationshipOtherEntity> others = new HashSet<BasicRelationshipOtherEntity>();

    public String getStringData() {
        return stringData;
    }

    public void setStringData( String aStringData ) {
        stringData = aStringData;
    }

    public Set<BasicRelationshipOtherEntity> getOthers() {
        return others;
    }

    public void setOthers( Set<BasicRelationshipOtherEntity> aOthers ) {
        others = aOthers;
    }

    public void addOther( BasicRelationshipOtherEntity aOther ) {
        others.add( aOther );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;

        BasicRelationshipEntity that = ( BasicRelationshipEntity ) o;

        if ( others != null ? !others.equals( that.others ) : that.others != null ) return false;
        if ( stringData != null ? !stringData.equals( that.stringData ) : that.stringData != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ( stringData != null ? stringData.hashCode() : 0 );
        result = 31 * result + ( others != null ? others.hashCode() : 0 );
        return result;
    }
}
