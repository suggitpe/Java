/*
 * Category.java created on 21 Mar 2007 17:43:41 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Category extends AbstractPersistentBaseClass
{

    private static final Log LOG = LogFactory.getLog( Category.class );

    private String mName_;
    private Category mParentCat_;
    private Set<Category> mChildCats_ = new HashSet<Category>();
    private Set<Item> mItems_ = new HashSet<Item>();

    public Category()
    {
        super();
    }
    
    public Category( String aName )
    {
        super();
        mName_ = aName;
    }

    public String getName()
    {
        return mName_;
    }

    public void setName( String aName )
    {
        mName_ = aName;
    }

    public Set<Item> getItems()
    {
        return mItems_;
    }

    public void setItems( Set<Item> aItems )
    {
        mItems_ = aItems;
    }

    public Set<Category> getChildCategories()
    {
        return mChildCats_;
    }

    public void setChildCategories( Set<Category> aCategories )
    {
        mChildCats_ = aCategories;
    }

    public void addChildCategory( Category aCategory )
    {
        if ( aCategory == null )
        {
            throw new IllegalArgumentException( "Null category passed in" );
        }
        if ( aCategory.getParentCategory() != null )
        {
            aCategory.getParentCategory().getChildCategories().remove( aCategory );
        }

        aCategory.setParentCategory( this );
        mChildCats_.add( aCategory );
    }

    public Category getParentCategory()
    {
        return mParentCat_;
    }

    public void setParentCategory( Category aCategory )
    {
        mParentCat_ = aCategory;
    }

}
