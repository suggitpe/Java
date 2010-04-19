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
public class Category extends AbstractPersistentBaseClass {

    @Column(name = "CATEGORY_NAME", nullable = false, length = 64)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_PARENT")
    private Category parentCat;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private Set<Category> childCats = new HashSet<Category>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "categories", targetEntity = org.suggs.sandbox.hibernate.caveatEmptor.Item.class, fetch = FetchType.LAZY)
    private Set<Item> items = new HashSet<Item>();

    /**
     * Constructs a new instance.
     */
    public Category() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aName
     */
    public Category( String aName ) {
        super();
        name = aName;
    }

    /**
     * getter for name
     * 
     * @return the category name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the name
     * 
     * @param aName
     *            the name to set
     */
    public void setName( String aName ) {
        name = aName;
    }

    /**
     * getter for the items relationship: many-to-many with item
     * 
     * @return all items in this category
     */
    public Set<Item> getItems() {
        return items;
    }

    /**
     * setter for the list of items in the category
     * 
     * @param aItems
     *            the set of items that are in this category
     */
    public void setItems( Set<Item> aItems ) {
        items = aItems;
    }

    /**
     * getter for the child categories
     * 
     * @return all child categories of this category
     */
    public Set<Category> getChildCategories() {
        return childCats;
    }

    /**
     * setter for the set of child categories
     * 
     * @param aCategories
     *            the set of child categories
     */
    public void setChildCategories( Set<Category> aCategories ) {
        childCats = aCategories;
    }

    /**
     * accessor method allowing you to add a new child category
     * 
     * @param aCategory
     *            the category to set
     */
    public void addChildCategory( Category aCategory ) {
        if ( aCategory == null ) {
            throw new IllegalArgumentException( "Null category passed in" );
        }
        if ( aCategory.getParentCategory() != null ) {
            aCategory.getParentCategory().getChildCategories().remove( aCategory );
        }

        aCategory.setParentCategory( this );
        childCats.add( aCategory );
    }

    /**
     * getter for the parent category
     * 
     * @return the parent category
     */
    public Category getParentCategory() {
        return parentCat;
    }

    /**
     * setter for the parent category
     * 
     * @param aCategory
     *            the parent category
     */
    public void setParentCategory( Category aCategory ) {
        parentCat = aCategory;
    }

}
