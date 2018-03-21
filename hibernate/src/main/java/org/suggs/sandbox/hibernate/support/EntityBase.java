/*
 * EntityBase.java created on 26 Mar 2010 19:09:39 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@SuppressWarnings("unused")
public class EntityBase implements TimestampAuditable, Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ENTITYBASE_SEQ_STR")
    private Long id;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Embedded
    private TimestampAuditInfo timestampAuditInfo = new TimestampAuditInfo();

    public Long getId() {
        return id;
    }

    public void setId(Long aId) {
        id = aId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer aVersion) {
        version = aVersion;
    }

    @Override
    public TimestampAuditInfo getTimestampAuditInfo() {
        return timestampAuditInfo;
    }

    public void setTimestampAuditInfo(TimestampAuditInfo aTimestampAuditInfo) {
        timestampAuditInfo = aTimestampAuditInfo;
    }

    @Override
    public String toString() {
        return "EntityBase [id=" + id + ", version=" + version + ", timestampAuditInfo=" + timestampAuditInfo
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((timestampAuditInfo == null) ? 0 : timestampAuditInfo.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        EntityBase other = (EntityBase) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (timestampAuditInfo == null) {
            if (other.timestampAuditInfo != null)
                return false;
        } else if (!timestampAuditInfo.equals(other.timestampAuditInfo))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
