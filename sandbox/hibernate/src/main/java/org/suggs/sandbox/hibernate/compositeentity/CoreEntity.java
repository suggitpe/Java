/*
 * CoreEntity.java created on 22 Sep 2010 19:47:58 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositeentity;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.*;

@Entity
@Table(name = "COMPOSITE_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "COMPOSITE_ENTITY_SQ")
@SuppressWarnings("unused")
public class CoreEntity extends EntityBase {

    private static final long serialVersionUID = 4104349939216946228L;

    @Column(name = "SOME_TEXT", nullable = false, length = 255)
    private String someText;

    @Embedded
    private CompositeEntity compositeEntity;

    public CoreEntity() {
    }

    public CoreEntity(String aTextString, CompositeEntity aCompositeEntity) {
        someText = aTextString;
        compositeEntity = aCompositeEntity;
    }

    public String getSomeText() {
        return someText;
    }

    public void setSomeText(String aSomeText) {
        someText = aSomeText;
    }

    public CompositeEntity getCompositeEntity() {
        return compositeEntity;
    }

    public void setCompositeEntity(CompositeEntity aCompositeEntity) {
        compositeEntity = aCompositeEntity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((compositeEntity == null) ? 0 : compositeEntity.hashCode());
        result = prime * result + ((someText == null) ? 0 : someText.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        CoreEntity other = (CoreEntity) obj;
        if (compositeEntity == null) {
            if (other.compositeEntity != null)
                return false;
        } else if (!compositeEntity.equals(other.compositeEntity))
            return false;
        if (someText == null) {
            if (other.someText != null)
                return false;
        } else if (!someText.equals(other.someText))
            return false;
        return true;
    }

}
