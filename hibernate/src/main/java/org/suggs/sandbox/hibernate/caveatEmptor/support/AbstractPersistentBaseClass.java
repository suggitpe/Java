/*
 * AbstractPersistentBaseClass.java created on 22 Mar 2007 17:42:27 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor.support;

import javax.persistence.*;

@MappedSuperclass
@SuppressWarnings("unused")
public abstract class AbstractPersistentBaseClass {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CE_SEQ_STR")
    private long id;

    @Version
    @Column(name = "OPT_LOCK_VER")
    private long version;

    public AbstractPersistentBaseClass() {
    }

    public long getId() {
        return id;
    }

    protected void setId(long aId) {
        id = aId;
    }

    public long getVersion() {
        return version;
    }

    protected void setVersion(long aVersion) {
        version = aVersion;
    }

}
