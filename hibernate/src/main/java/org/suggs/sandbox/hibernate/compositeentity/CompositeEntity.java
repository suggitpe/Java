/*
 * CompositeEntity.java created on 22 Sep 2010 19:48:10 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositeentity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("unused")
public class CompositeEntity {

    @Column(name = "COMP_TEXT", nullable = false, length = 255)
    private String compositeText;

    @Column(name = "COMP_INT", nullable = false)
    private Integer compositeInteger;

    public CompositeEntity() {
    }

    public CompositeEntity(String aCompositeText, Integer aCompositeInteger) {
        compositeText = aCompositeText;
        compositeInteger = aCompositeInteger;
    }

    public String getCompositeText() {
        return compositeText;
    }

    public void setCompositeText(String aCompositeText) {
        compositeText = aCompositeText;
    }

    public Integer getCompositeInteger() {
        return compositeInteger;
    }

    public void setCompositeInteger(Integer aCompositeInteger) {
        compositeInteger = aCompositeInteger;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((compositeInteger == null) ? 0 : compositeInteger.hashCode());
        result = prime * result + ((compositeText == null) ? 0 : compositeText.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CompositeEntity other = (CompositeEntity) obj;
        if (compositeInteger == null) {
            if (other.compositeInteger != null)
                return false;
        } else if (!compositeInteger.equals(other.compositeInteger))
            return false;
        if (compositeText == null) {
            if (other.compositeText != null)
                return false;
        } else if (!compositeText.equals(other.compositeText))
            return false;
        return true;
    }

}
