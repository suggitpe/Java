package org.suggs.sandbox.hibernate.basicentity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "BASIC_RELATIONSHIP_ENTITY_OTH")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "BASIC_RELATION_OTH_ENTITY_SQ")
@SuppressWarnings("unused")
public class BasicRelationshipOtherEntity extends EntityBase {

    @Column(name = "STRING_DATA", length = 64)
    private String stringData;

    public BasicRelationshipOtherEntity() {
    }

    public BasicRelationshipOtherEntity(String aString) {
        stringData = aString;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String aStringData) {
        stringData = aStringData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicRelationshipOtherEntity that = (BasicRelationshipOtherEntity) o;

        if (stringData != null ? !stringData.equals(that.stringData) : that.stringData != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stringData != null ? stringData.hashCode() : 0;
    }

    @Override
    public String toString() {
        return super.toString() + ", BasicRelationshipOtherEntity{" +
                "stringData='" + stringData + '\'' +
                '}';
    }
}
