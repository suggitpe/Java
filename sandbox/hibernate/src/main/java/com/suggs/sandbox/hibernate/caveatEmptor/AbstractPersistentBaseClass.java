/*
 * AbstractPersistentBaseClass.java created on 22 Mar 2007 17:42:27 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

/**
 * Abstract base class for all persistent hibernate objecs to use.
 * This class enforces the use of the correct database identity logic.
 * 
 * @author suggitpe
 * @version 1.0 12 Apr 2007
 */
public abstract class AbstractPersistentBaseClass
{

    private Long mId_;

    public AbstractPersistentBaseClass()
    {
    }

    /**
     * @hibernate-id generator-class="native" column="ID"
     * @return the id
     */
    public Long getId()
    {
        return mId_;
    }

    private void setId( Long aId )
    {
        mId_ = aId;
    }

}
