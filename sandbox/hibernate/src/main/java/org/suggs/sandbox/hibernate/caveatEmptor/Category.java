/*
 * Category.java created on 21 Mar 2007 17:43:41 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Class to represent a category
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Entity
@Table(name = "CE_CATEGORY")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_CATEGORY_SEQ")
public class Category extends AbstractPersistentBaseClass
{

    private String mName_;
    private Category mParentCat_;
    private Set<Category> mChildCats_ = new HashSet<Category>();
    private Set<Item> mItems_ = new HashSet<Item>();

    /**
     * Constructs a new instance.
     */
    public Category()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aName
     */
    public Category( String aName )
    {
        super();
        mName_ = aName;
    }

    /**
     * getter for name
     * 
     * @return the category name
     */
    @Column(name = "CATEGORY_NAME", nullable = false, length = 64)
    public String getName()
    {
        return mName_;
    }

    /**
     * setter for the name
     * 
     * @param aName
     *            the name to set
     */
    public void setName( String aName )
    {
        mName_ = aName;
    }

    /**
     * getter for the items relationship: many-to-many with item
     * 
     * @return all items in this category
     */
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "categories", targetEntity = org.suggs.sandbox.hibernate.caveatEmptor.Item.class, fetch = FetchType.LAZY)
    public Set<Item> getItems()
    {
        return mItems_;
    }

    /**
     * setter for the list of items in the category
     * 
     * @param aItems
     *            the set of items that are in this category
     */
    public void setItems( Set<Item> aItems )
    {
        mItems_ = aItems;
    }

    /**
     * getter for the child categories
     * 
     * @return all child categories of this category
     */
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    public Set<Category> getChildCategories()
    {
        return mChildCats_;
    }

    /**
     * setter for the set of child categories
     * 
     * @param aCategories
     *            the set of child categories
     */
    public void setChildCategories( Set<Category> aCategories )
    {
        mChildCats_ = aCategories;
    }

    /**
     * accessor method allowing you to add a new child category
     * 
     * @param aCategory
     *            the category to set
     */
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

    /**
     * getter for the parent category
     * 
     * @return the parent category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_PARENT")
    public Category getParentCategory()
    {
        return mParentCat_;
    }

    /**
     * setter for the parent category
     * 
     * @param aCategory
     *            the parent category
     */
    public void setParentCategory( Category aCategory )
    {
        mParentCat_ = aCategory;
    }

}
