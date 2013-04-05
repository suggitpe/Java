/*
 * TimestampAuditInfo.java created on 31 Mar 2010 07:29:17 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
public class TimestampAuditInfo {

    @Column(name = "create_date", updatable = false)
    private Timestamp createDate;

    @Column(name = "update_date")
    private Timestamp updateDate;

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp aCreateDate) {
        createDate = aCreateDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp aUpdateDate) {
        updateDate = aUpdateDate;
    }

    @Override
    public String toString() {
        return "TimestampAuditInfo [createDate=" + createDate + ", updateDate=" + updateDate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimestampAuditInfo other = (TimestampAuditInfo) obj;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (updateDate == null) {
            if (other.updateDate != null)
                return false;
        } else if (!updateDate.equals(other.updateDate))
            return false;
        return true;
    }

}
