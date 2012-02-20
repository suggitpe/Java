/*
 * AbstractPersistentBaseClass.java created on 22 Mar 2007 17:42:27 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor.support;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Abstract base class for all persistent hibernate objecs to use. This class enforces the use of the correct
 * database identity logic.
 * 
 * @author suggitpe
 * @version 1.0 12 Apr 2007
 */
@MappedSuperclass
public abstract class AbstractPersistentBaseClass {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CE_SEQ_STR")
    private long id;

    @Version
    @Column(name = "OPT_LOCK_VER")
    private long version;

    /**
     * Constructs a new instance.
     */
    public AbstractPersistentBaseClass() {}

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    protected void setId( long aId ) {
        id = aId;
    }

    /**
     * Getter for the version
     * 
     * @return the version of the object (database version for locking)
     */
    public long getVersion() {
        return version;
    }

    /**
     * Setter fror the version attribute
     * 
     * @param aVersion
     *            teh version to set
     */
    protected void setVersion( long aVersion ) {
        version = aVersion;
    }

}
